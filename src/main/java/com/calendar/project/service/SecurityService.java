package com.calendar.project.service;

import com.calendar.project.model.User;

public interface SecurityService {

    User findLoggedInUsername();

    void autoLogin(String username, String password);

}
