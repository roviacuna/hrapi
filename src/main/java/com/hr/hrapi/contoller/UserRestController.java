package com.hr.hrapi.contoller;

import com.hr.hrapi.response.UserReponseRest;
import com.hr.hrapi.response.UserResponse;
import com.hr.hrapi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    public IUserService userService;

    @GetMapping("/users")
    public ResponseEntity<UserReponseRest> userGetAll(){
        ResponseEntity<UserReponseRest> userReponseRest = userService.findUsers();
        return userReponseRest;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserReponseRest> userGetById(@PathVariable Long id){
        ResponseEntity<UserReponseRest> userResponseRest = userService.findUserById(id);
        return userResponseRest;
    }

}
