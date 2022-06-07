package com.hr.hrapi.model.dao;

import com.hr.hrapi.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface IEmployeeDao extends CrudRepository<Employee, Long> {
}
