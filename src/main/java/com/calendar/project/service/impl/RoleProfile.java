package com.calendar.project.service.impl;

import java.io.Serializable;

public enum RoleProfile implements Serializable {
    USER("USER"),
    DBA("DBA"),
    ADMIN("ADMIN");

    String roleProfile;

    private RoleProfile(String roleProfile) {
        this.roleProfile = roleProfile;
    }

    public String getRoleProfile() {
        return roleProfile;
    }

}

