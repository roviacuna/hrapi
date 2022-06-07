package com.hr.hrapi.contoller;

import com.hr.hrapi.model.Employee;
import com.hr.hrapi.response.EmployeeResponseRest;
import com.hr.hrapi.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {

    @Autowired
    public IEmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<EmployeeResponseRest> employeeGetAll(){
        ResponseEntity<EmployeeResponseRest> employeeResponseRest = employeeService.findEmployees();
        return employeeResponseRest;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseRest> employeeGetById(@PathVariable Long id){
        ResponseEntity<EmployeeResponseRest> employeeResponseRest = employeeService.findEmployeeById(id);
        return employeeResponseRest;
    }

    @PostMapping("/add")
    public ResponseEntity<EmployeeResponseRest> createEmployee(@RequestBody Employee employee){
        ResponseEntity<EmployeeResponseRest> employeeResponseRestResponseEntity = employeeService.createEmployee(employee);
        return employeeResponseRestResponseEntity;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeResponseRest> updateEmployee(@RequestBody Employee employee, @PathVariable Long id){
        ResponseEntity<EmployeeResponseRest> employeeResponseRestResponseEntity = employeeService.updateEmployee(employee, id);
        return employeeResponseRestResponseEntity;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EmployeeResponseRest> deleteEmployee(@PathVariable Long id){
        ResponseEntity<EmployeeResponseRest> employeeResponseRestResponseEntity = employeeService.deleteEmployee(id);
        return employeeResponseRestResponseEntity;
    }

}
