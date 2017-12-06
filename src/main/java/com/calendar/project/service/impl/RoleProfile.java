package com.calendar.project.service.impl;

import java.io.Serializable;

public enum RoleProfile implements Serializable {
    GUEST("GUEST"),
    USER("USER"),
    DBA("DBA"),
    ADMIN("ADMIN"),
    SUPREME_ADMIN("SUPREME_ADMIN");

    String roleProfile;

    RoleProfile(String roleProfile) {
        this.roleProfile = roleProfile;
    }

    public String getRoleProfile() {
        return roleProfile;
    }
}