package com.calendar.project.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;



@Entity
@Table(name = "APP_USER")
public class User implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FIRST_NAME")
    private String firstname;

    @Column(name = "LAST_NAME")
    private String lastname;

    @Column(name = "EMAIL")
    private String email;



    @NotEmpty
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH,
   CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable(name = "USER_ROLE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    private Set<Role> roles = new HashSet<Role>();

    @Transient //https://stackoverflow.com/questions/2154622/why-does-jpa-have-a-transient-annotation
    private String confirmPassword;

//    public User(){};
//
//    public User(String username) {
//        this.username = username;
//    }

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

    public void setRoles(Set<Role> roleses) {
        this.roles = roleses;
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
        return roles.equals(user.roles);
    }

//    @Override
//    public int hashCode() {
//        int result = id.hashCode();
//        result = 31 * result + username.hashCode();
//        result = 31 * result + firstname.hashCode();
//        result = 31 * result + lastname.hashCode();
//        result = 31 * result + email.hashCode();
//        result = 31 * result + password.hashCode();
//        result = 31 * result + roleses.hashCode();
//        return result;
//    }
}
