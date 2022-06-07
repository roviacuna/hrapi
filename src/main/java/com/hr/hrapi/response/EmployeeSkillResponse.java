package com.hr.hrapi.response;

import com.hr.hrapi.model.EmployeeSkill;

import java.util.List;

public class EmployeeSkillResponse {

    private List<EmployeeSkill> employeeSkill;

    public List<EmployeeSkill> getEmployeeSkill() {
        return employeeSkill;
    }

    public void setEmployeeSkill(List<EmployeeSkill> employeeSkill) {
        this.employeeSkill = employeeSkill;
    }
}
