package com.hr.hrapi.contoller;

import com.hr.hrapi.model.Skill;
import com.hr.hrapi.model.UserSkill;
import com.hr.hrapi.response.SkillResponseRest;
import com.hr.hrapi.response.UserSkillResponseRest;
import com.hr.hrapi.service.IUserSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userskill")
public class UserSkillController {

    @Autowired
    public IUserSkillService iUserSkillService;

    @GetMapping("/userskills")
    public ResponseEntity<UserSkillResponseRest> getAllUserSkills(){
        ResponseEntity<UserSkillResponseRest> userSkillResponseRestResponseEntity = iUserSkillService.findUserSkill();
        return userSkillResponseRestResponseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSkillResponseRest> uSerSkillGetById(@PathVariable Long id){
        ResponseEntity<UserSkillResponseRest> userSkillResponseRestResponseEntity = iUserSkillService.findUserSkillById(id);
        return userSkillResponseRestResponseEntity;
    }

    @PostMapping("/add")
    public ResponseEntity<UserSkillResponseRest> createUserSkill(@RequestBody UserSkill userSkill){
        ResponseEntity<UserSkillResponseRest> userSkillResponseRestResponseEntity = iUserSkillService.createUserSkill(userSkill);
        return userSkillResponseRestResponseEntity;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserSkillResponseRest> updateUserSkill(@RequestBody UserSkill userSkill, @PathVariable Long id){
        ResponseEntity<UserSkillResponseRest> userSkillResponseRestResponseEntity = iUserSkillService.updateUserSkill(userSkill, id);
        return userSkillResponseRestResponseEntity;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserSkillResponseRest> deleteUserSkill(@PathVariable Long id){
        ResponseEntity<UserSkillResponseRest> userSkillResponseRestResponseEntity = iUserSkillService.deleteUserSkill(id);
        return userSkillResponseRestResponseEntity;
    }

}
