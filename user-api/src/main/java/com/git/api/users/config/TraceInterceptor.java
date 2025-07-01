package com.git.api.users.config;


import io.opentelemetry.api.trace.SpanContext;
import io.opentelemetry.api.trace.Tracer;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
public class TraceInterceptor implements ContainerResponseFilter {

    private static final String TRACE_ID = "requestId";

    private final Tracer tracer;

    public TraceInterceptor(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public void filter(
            ContainerRequestContext containerRequestContext,
            ContainerResponseContext responseContext
    ) throws IOException {
        final SpanContext spanContext = tracer.spanBuilder(TRACE_ID).startSpan().getSpanContext();

        if (spanContext != null) {
            responseContext.getHeaders().add(TRACE_ID, spanContext.getTraceId());
        }
    }
}
