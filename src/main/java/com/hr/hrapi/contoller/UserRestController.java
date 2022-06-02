package com.hr.hrapi.contoller;

import com.hr.hrapi.model.User;
import com.hr.hrapi.response.UserResponseRest;
import com.hr.hrapi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    public IUserService userService;

    @GetMapping("/users")
    public ResponseEntity<UserResponseRest> userGetAll(){
        ResponseEntity<UserResponseRest> userResponseRest = userService.findUsers();
        return userResponseRest;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseRest> userGetById(@PathVariable Long id){
        ResponseEntity<UserResponseRest> userResponseRest = userService.findUserById(id);
        return userResponseRest;
    }

    @PostMapping("/add")
    public ResponseEntity<UserResponseRest> createUser(@RequestBody User user){
        ResponseEntity<UserResponseRest> userResponseRestResponseEntity = userService.createUser(user);
        return userResponseRestResponseEntity;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseRest> updateUser(@RequestBody User user, @PathVariable Long id){
        ResponseEntity<UserResponseRest> userResponseRestResponseEntity = userService.updateUser(user, id);
        return userResponseRestResponseEntity;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserResponseRest> deleteUser(@PathVariable Long id){
        ResponseEntity<UserResponseRest> userResponseRestResponseEntity = userService.deleteUser(id);
        return userResponseRestResponseEntity;
    }

}
