package com.calendar.project.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by mhristiniuc on 11/18/2017.
 */
public class UserResource {

    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String confirmPassword;
    private String subscriptionByEventType;
    private String subscriptionByTagType;
    private Set<RoleResource> roles;
    private List<EventResource> events = new ArrayList<>();
    private List<EventResource> eventsOfAuthor = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName(){return firstname + " " + lastname;}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getSubscriptionByEventType() {
        return subscriptionByEventType;
    }

    public void setSubscriptionByEventType(String subscriptionByEventType) {
        this.subscriptionByEventType = subscriptionByEventType;
    }


    public String getSubscriptionByTagType() {
        return subscriptionByTagType;
    }

    public void setSubscriptionByTagType(String subscriptionByTagType) {
        this.subscriptionByTagType = subscriptionByTagType;
    }

    public Set<RoleResource> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleResource> roles) {
        this.roles = roles;
    }

    public List<EventResource> getEvents() {
        return events;
    }

    public void setEvents(List<EventResource> events) {
        this.events = events;
    }

    public List<EventResource> getEventsOfAuthor() {
        return eventsOfAuthor;
    }

    public void setEventsOfAuthor(List<EventResource> eventsOfAuthor) {
        this.eventsOfAuthor = eventsOfAuthor;
    }


}
