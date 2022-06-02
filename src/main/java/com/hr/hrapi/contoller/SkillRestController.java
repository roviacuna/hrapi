package com.hr.hrapi.contoller;

import com.hr.hrapi.response.SkillResponseRest;
import com.hr.hrapi.service.ISkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skill")
public class SkillRestController {

    @Autowired
    public ISkillService iSkillService;

    @GetMapping("/skills")
    public SkillResponseRest skillResponseRest(){
        SkillResponseRest skillResponseRest = iSkillService.findSkills();
        return skillResponseRest;
    }

}
