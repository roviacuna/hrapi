package com.hr.hrapi.response;

public class EmployeeSkillResponseRest extends ResponseRest {

    private EmployeeSkillResponse employeeSkillResponse = new EmployeeSkillResponse();

    public EmployeeSkillResponse getEmployeeSkillResponse() {
        return employeeSkillResponse;
    }

    public void setEmployeeSkillResponse(EmployeeSkillResponse employeeSkillResponse) {
        this.employeeSkillResponse = employeeSkillResponse;
    }
}
