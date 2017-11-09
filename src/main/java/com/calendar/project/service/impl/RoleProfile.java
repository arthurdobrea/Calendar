package com.calendar.project.service.impl;

import java.io.Serializable;

/**
 * Created by icebotari on 10/31/2017.
 */

    public enum RoleProfile implements Serializable {
        GUEST("GUEST"),
        USER("USER"),
        DBA("DBA"),
        ADMIN("ADMIN"),
        SUPREME_ADMIN("SUPREME_ADMIN");

    String roleProfile;

    private RoleProfile(String roleProfile) {
        this.roleProfile = roleProfile;
    }

    public String getRoleProfile() {
        return roleProfile;
    }

}

