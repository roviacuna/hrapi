package com.hr.hrapi.response;

import com.hr.hrapi.model.Employee;

import java.util.List;

public class EmployeeResponse {

    private List<Employee> employee;

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }
}
