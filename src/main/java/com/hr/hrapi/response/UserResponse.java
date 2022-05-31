package com.hr.hrapi.response;

import com.hr.hrapi.model.User;

import java.util.List;

public class UserResponse {

    private List<User> user;

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
