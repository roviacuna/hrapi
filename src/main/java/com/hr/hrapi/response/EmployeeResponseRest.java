package com.hr.hrapi.response;

public class EmployeeResponseRest extends ResponseRest{

    private EmployeeResponse employeeResponse = new EmployeeResponse();

    public EmployeeResponse getEmployeeResponse() {
        return employeeResponse;
    }

    public void setEmployeeResponse(EmployeeResponse employeeResponse) {
        this.employeeResponse = employeeResponse;
    }
}
