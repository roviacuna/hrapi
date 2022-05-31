package com.hr.hrapi.service;

import com.hr.hrapi.response.UserReponseRest;
import org.springframework.http.ResponseEntity;

public interface IUserService {

    public ResponseEntity<UserReponseRest> findUsers();
    public ResponseEntity<UserReponseRest> findUserById(long id);

}
