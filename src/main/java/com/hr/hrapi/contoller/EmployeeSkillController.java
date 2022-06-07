package com.hr.hrapi.contoller;

import com.hr.hrapi.model.EmployeeSkill;
import com.hr.hrapi.response.EmployeeSkillResponseRest;
import com.hr.hrapi.service.IEmployeeSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employeeskill")
public class EmployeeSkillController {

    @Autowired
    public IEmployeeSkillService iEmployeeSkillService;

    @GetMapping("/employeeskills")
    public ResponseEntity<EmployeeSkillResponseRest> getAllEmployeeSkills(){
        ResponseEntity<EmployeeSkillResponseRest> employeeSkillResponseRestResponseEntity = iEmployeeSkillService.findEmployeeSkill();
        return employeeSkillResponseRestResponseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeSkillResponseRest> employeeSkillGetById(@PathVariable Long id){
        ResponseEntity<EmployeeSkillResponseRest> employeeSkillResponseRestResponseEntity = iEmployeeSkillService.findEmployeeSkillById(id);
        return employeeSkillResponseRestResponseEntity;
    }

    @PostMapping("/add")
    public ResponseEntity<EmployeeSkillResponseRest> createEmployeeSkill(@RequestBody EmployeeSkill employeeSkill){
        ResponseEntity<EmployeeSkillResponseRest> employeeSkillResponseRestResponseEntity = iEmployeeSkillService.createEmployeeSkill(employeeSkill);
        return employeeSkillResponseRestResponseEntity;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeSkillResponseRest> updateEmployeeSkill(@RequestBody EmployeeSkill employeeSkill, @PathVariable Long id){
        ResponseEntity<EmployeeSkillResponseRest> employeeSkillResponseRestResponseEntity = iEmployeeSkillService.updateEmployeeSkill(employeeSkill, id);
        return employeeSkillResponseRestResponseEntity;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EmployeeSkillResponseRest> deleteEmployeeSkill(@PathVariable Long id){
        ResponseEntity<EmployeeSkillResponseRest> employeeSkillResponseRestResponseEntity = iEmployeeSkillService.deleteEmployeeSkill(id);
        return employeeSkillResponseRestResponseEntity;
    }

}
