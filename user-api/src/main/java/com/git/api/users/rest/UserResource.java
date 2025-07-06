package com.git.api.users.rest;


import com.git.api.users.persistence.entities.User;
import com.git.api.users.persistence.entities.UserDto;
import com.git.api.users.services.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    public Response getAllUsers() {
        return Response.ok(userService.findAll()).build();
    }

    @GET
    @Path("/email/{email}")
    public Response getUsersByEmail(@PathParam("email") String email) {
        return Response.ok(userService.findByEmail(email)).build();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") String id) {
        return Response.ok(userService.findById(id)).build();
    }

    @POST
    public Response createUser(UserDto user) {
        User userSaved = new User(
                user.getName(), user.getEmail(), user.getPassword(), user.getRole()
        );
        return Response.status(Response.Status.CREATED)
                .entity(userService.createUser(userSaved))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") String id, User user) {
        return Response.ok(userService.updateUser(id, user)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") String id) {
        userService.deleteUser(id);
        return Response.noContent().build();
    }

}
