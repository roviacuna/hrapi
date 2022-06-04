package com.hr.hrapi.contoller;

import com.hr.hrapi.model.Skill;
import com.hr.hrapi.response.SkillResponseRest;
import com.hr.hrapi.service.ISkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/skill")
public class SkillRestController {

    @Autowired
    public ISkillService iSkillService;

    @GetMapping("/skills")
    public ResponseEntity<SkillResponseRest> getAppSkills(){
        ResponseEntity<SkillResponseRest> skillResponseRest = iSkillService.findSkills();
        return skillResponseRest;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillResponseRest> skillGetById(@PathVariable Long id){
        ResponseEntity<SkillResponseRest> skillResponseRest = iSkillService.findSkillById(id);
        return skillResponseRest;
    }

    @PostMapping("/add")
    public ResponseEntity<SkillResponseRest> createSkill(@RequestBody Skill skill){
        ResponseEntity<SkillResponseRest> skillResponseRest = iSkillService.createSkill(skill);
        return skillResponseRest;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SkillResponseRest> updateUser(@RequestBody Skill skill, @PathVariable Long id){
        ResponseEntity<SkillResponseRest> skillResponseRestResponseEntity = iSkillService.updateSkill(skill, id);
        return skillResponseRestResponseEntity;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SkillResponseRest> deleteSkill(@PathVariable Long id){
        ResponseEntity<SkillResponseRest> skillResponseRestResponseEntity = iSkillService.deleteSkill(id);
        return skillResponseRestResponseEntity;
    }

}
