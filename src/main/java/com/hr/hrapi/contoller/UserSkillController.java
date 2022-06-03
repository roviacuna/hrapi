package com.hr.hrapi.contoller;

import com.hr.hrapi.response.SkillResponseRest;
import com.hr.hrapi.response.UserSkillResponseRest;
import com.hr.hrapi.service.IUserSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userkill")
public class UserSkillController {

    @Autowired
    public IUserSkillService iUserSkillService;

    @GetMapping("/userkills")
    public ResponseEntity<UserSkillResponseRest> getAllUserSkills(){
        ResponseEntity<UserSkillResponseRest> userSkillResponseRestResponseEntity = iUserSkillService.findUserSkill();
        return userSkillResponseRestResponseEntity;
    }

}
