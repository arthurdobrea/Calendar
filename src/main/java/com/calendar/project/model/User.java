package com.calendar.project.model;

import com.calendar.project.model.enums.EventType;
import com.calendar.project.model.enums.TagType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


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

    @JsonIgnore
    @Transient
    private String confirmPassword;

    @JsonIgnore
    @Column(name="image")
    private byte[] image;

    @Column(name="position")
    private String position;

//    @JsonIgnore
//    @Transient
//    private MultipartFile multipartFile;

    @Column(name = "subscription_by_event_type")
    private String subscriptionByEventType;

    @Column(name = "subscription_by_tag_type")
    private String subscriptionByTagType;


    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @ManyToMany(mappedBy = "participants", fetch = FetchType.LAZY)
    private List<Event> events = new ArrayList<>(); //events in which user participates



    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private List<Event> eventsOfAuthor = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;

    public User() { }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public String getPosition() { return position; }

    public void setPosition(String position) { this.position = position; }

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

    public byte[] getImage() { return image; }

    public void setImage(byte[] image) { this.image = image; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

//    public MultipartFile getMultipartFile() {
//        return multipartFile;
//    }

//    public void setMultipartFile(MultipartFile multipartFile) {
//        this.multipartFile = multipartFile;
//    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getSubscriptionByEventType() {
        return subscriptionByEventType;
    }

    public void setSubscriptionByEventType(String subscriptionByEventType) { this.subscriptionByEventType = subscriptionByEventType; }

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

    public String getFullName() {
        return getFirstname() + " " + getLastname();
    }

    public Set<EventType>getSubscriptionByEventTypeAsEnums(){
        Set <EventType> subscriptionByEventTypeSet;
        subscriptionByEventTypeSet=new HashSet();

        String labelArray[] = null;
        try{
            labelArray = subscriptionByEventType.split(",");
        } catch (Exception e){
            return subscriptionByEventTypeSet;
        }
        for (String label:labelArray)
            for (EventType eventType : EventType.values())
                if (label.equals(eventType.toString()))
                    subscriptionByEventTypeSet.add(eventType);
        return subscriptionByEventTypeSet;
    }

    public String getSubscriptionByTagType() {
        return subscriptionByTagType;
    }

    public void setSubscriptionByTagType(String subscriptionByTagType) {
        this.subscriptionByTagType = subscriptionByTagType;
    }

    public Set<TagType> getSubscriptionByTagTypeAsEnums(){
        Set <TagType> subscriptionByTagTypeSet=new HashSet();
        String tagArray[];
        try{
            tagArray = subscriptionByTagType.split(",");
        } catch (Exception e){
            return subscriptionByTagTypeSet;
        }
        for (String tag:tagArray)
            for (TagType tagType : TagType.values())
                if (tag.equals(tagType.toString()))
                    subscriptionByTagTypeSet.add(tagType);
        return subscriptionByTagTypeSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (!username.equals(user.username)) return false;
        if (!firstname.equals(user.firstname)) return false;
        if (!lastname.equals(user.lastname)) return false;
        if (!email.equals(user.email)) return false;
        if (!password.equals(user.password)) return false;
        if (!confirmPassword.equals(user.confirmPassword)) return false;
        if (!roles.equals(user.roles)) return false;
      //  if (!image.equals(user.image)) return false;
        if (!events.equals(user.events)) return false;
        return eventsOfAuthor.equals(user.eventsOfAuthor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, firstname, lastname, email, password, confirmPassword, roles, events, eventsOfAuthor);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
