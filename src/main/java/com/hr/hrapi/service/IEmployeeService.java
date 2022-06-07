package com.hr.hrapi.service;

import com.hr.hrapi.model.Employee;
import com.hr.hrapi.response.EmployeeResponseRest;
import org.springframework.http.ResponseEntity;

public interface IEmployeeService {

    public ResponseEntity<EmployeeResponseRest> findEmployees();
    public ResponseEntity<EmployeeResponseRest> findEmployeeById(long id);
    public ResponseEntity<EmployeeResponseRest> createEmployee(Employee employee);
    public ResponseEntity<EmployeeResponseRest> updateEmployee(Employee employee, Long id);
    public ResponseEntity<EmployeeResponseRest> deleteEmployee(Long id);

}
