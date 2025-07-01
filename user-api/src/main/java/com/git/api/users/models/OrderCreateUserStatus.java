package com.git.api.users.models;

import com.git.api.users.enums.NotificationStatus;

public class OrderCreateUserStatus {

    private String idUser;
    private NotificationStatus status;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }
}
