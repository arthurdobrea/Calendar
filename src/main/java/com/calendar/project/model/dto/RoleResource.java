package com.calendar.project.model.dto;

import java.util.Set;

public class RoleResource {

    private Long id;

    private String name;

    private Set<UserResource> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserResource> getUsers() {
        return users;
    }

    public void setUsers(Set<UserResource> users) {
        this.users = users;
    }
}