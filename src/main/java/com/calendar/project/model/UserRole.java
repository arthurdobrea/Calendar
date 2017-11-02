package com.calendar.project.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by icebotari on 11/1/2017.
 */
@Entity
@Table(name = "USER_ROLE")
public class UserRole {
    @Id
    @Column(name = "USER_ID")
    private Long user_id;

    @Column(name = "ROLE_ID")
    private Long role_id;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }
}
