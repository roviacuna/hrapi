package com.hr.hrapi.service;

import com.hr.hrapi.model.EmployeeSkill;
import com.hr.hrapi.response.EmployeeSkillResponseRest;
import org.springframework.http.ResponseEntity;

public interface IEmployeeSkillService {

    public ResponseEntity<EmployeeSkillResponseRest> findEmployeeSkill();
    public ResponseEntity<EmployeeSkillResponseRest> findEmployeeSkillById(Long id);
    public ResponseEntity<EmployeeSkillResponseRest> createEmployeeSkill(EmployeeSkill employeeSkill);
    public ResponseEntity<EmployeeSkillResponseRest> updateEmployeeSkill(EmployeeSkill employeeSkill, Long id);
    public ResponseEntity<EmployeeSkillResponseRest> deleteEmployeeSkill(Long id);


}
