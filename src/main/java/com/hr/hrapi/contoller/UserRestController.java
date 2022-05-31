package com.hr.hrapi.contoller;

import com.hr.hrapi.response.UserReponseRest;
import com.hr.hrapi.response.UserResponse;
import com.hr.hrapi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    public IUserService userService;

    @GetMapping("/users")
    public ResponseEntity<UserReponseRest> userReponseRest(){
        ResponseEntity<UserReponseRest> userReponseRest = userService.findUsers();
        return userReponseRest;
    }

}
