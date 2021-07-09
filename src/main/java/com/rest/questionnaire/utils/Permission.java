package com.rest.questionnaire.utils;

import lombok.Getter;

@Getter
public enum Permission {

    ADMIN_READ("admin:read"), ADMIN_WRITE("admin:write");

    private final String permission;

    Permission(String permission){
        this.permission = permission;
    }
}
