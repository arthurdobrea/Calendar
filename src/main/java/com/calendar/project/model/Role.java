package com.calendar.project.model;


import com.calendar.project.service.impl.RoleProfile;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Set;



@Entity
@Table(name = "APP_ROLE")
public class Role {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name = RoleProfile.USER.getRoleProfile();
//    @Column(name = "name")
//    private String name;


//    @ManyToMany(mappedBy = "name", fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE} )
//    private Set<User> users;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }


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

//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
//
//    @Override
//    public String toString() {
//        return "Role{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", users=" + users +
//                '}';
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return name.equals(role.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
