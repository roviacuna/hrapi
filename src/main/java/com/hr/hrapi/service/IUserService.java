package com.hr.hrapi.service;

import com.hr.hrapi.model.User;
import com.hr.hrapi.response.UserResponseRest;
import org.springframework.http.ResponseEntity;

public interface IUserService {

    public ResponseEntity<UserResponseRest> findUsers();
    public ResponseEntity<UserResponseRest> findUserById(long id);
    public ResponseEntity<UserResponseRest> createUser(User user);
    public ResponseEntity<UserResponseRest> updateUser(User user, Long id);

}
