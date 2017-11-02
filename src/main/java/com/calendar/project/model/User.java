package com.calendar.project.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import java.util.List;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Transient
    private String confirmPassword;

    //@ElementCollection(targetClass = String.class)
    //@Enumerated(EnumType.STRING)
    @Column(name = "labels")
    private String labels;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "events_users", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<Event> events; //events in which user participates

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private List<Event> eventsOfAuthor = new ArrayList<>(); //events where user is the author

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public List<Event> getEventsOfAuthor() {
        return eventsOfAuthor;
    }

    public void setEventsOfAuthor(List<Event> eventsOfAuthor) {
        this.eventsOfAuthor = eventsOfAuthor;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Set<EventType> getLabelsAsEnums(){
        Set <EventType> labelSet=new HashSet();
        String labelArray[] = labels.split(",");
        for (String label:labelArray)
            for (EventType eventType : EventType.values())
                if (label.equals(eventType.toString()))
                    labelSet.add(eventType);
        return labelSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (firstname != null ? !firstname.equals(user.firstname) : user.firstname != null) return false;
        if (lastname != null ? !lastname.equals(user.lastname) : user.lastname != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (confirmPassword != null ? !confirmPassword.equals(user.confirmPassword) : user.confirmPassword != null)
            return false;
        if (labels != null ? !labels.equals(user.labels) : user.labels != null) return false;
        if (roles != null ? !roles.equals(user.roles) : user.roles != null) return false;
        if (events != null ? !events.equals(user.events) : user.events != null) return false;
        return eventsOfAuthor != null ? eventsOfAuthor.equals(user.eventsOfAuthor) : user.eventsOfAuthor == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (confirmPassword != null ? confirmPassword.hashCode() : 0);
        result = 31 * result + (labels != null ? labels.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        result = 31 * result + (events != null ? events.hashCode() : 0);
        result = 31 * result + (eventsOfAuthor != null ? eventsOfAuthor.hashCode() : 0);
        return result;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                //", roles=" + roles +
                //", events=" + events +
                //", eventsOfAuthor=" + eventsOfAuthor +
                '}';
    }
}
