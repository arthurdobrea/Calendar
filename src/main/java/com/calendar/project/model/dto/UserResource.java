package com.calendar.project.model.dto;

import com.calendar.project.model.Event;
import com.calendar.project.model.Notification;
import com.calendar.project.model.Role;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by mhristiniuc on 11/18/2017.
 */
public class UserResource {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String confirmPassword;
    private String position;
    private MultipartFile multipartFile;
    private String subscriptionByEventType;
    private String subscriptionByTagType;
    private List<Event> events = new ArrayList<>();
    private List<Event> eventsOfAuthor = new ArrayList<>();
    private List<Notification> notifications;

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

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Event> getEventsOfAuthor() {
        return eventsOfAuthor;
    }

    public void setEventsOfAuthor(List<Event> eventsOfAuthor) {
        this.eventsOfAuthor = eventsOfAuthor;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    private Set<Role> roles;

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
