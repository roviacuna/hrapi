package com.hr.hrapi.service;

import com.hr.hrapi.model.Employee;
import com.hr.hrapi.model.dao.IEmployeeDao;
import com.hr.hrapi.response.EmployeeResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private IEmployeeDao iEmployeeDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<EmployeeResponseRest> findEmployees() {
        log.info("inicio de método buscar");
        EmployeeResponseRest employeeResponseRest = new EmployeeResponseRest();

        try {
            List<Employee> employee = (List<Employee>) iEmployeeDao.findAll();
            employeeResponseRest.getEmployeeResponse().setEmployee(employee);
            employeeResponseRest.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
        }catch (Exception e){
            employeeResponseRest.setMetadata("Respuesta NO-OK", "-1", "Error al consultar los usuarios");
            log.error("Error al consultar usuarios", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<EmployeeResponseRest>(employeeResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<EmployeeResponseRest>(employeeResponseRest, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<EmployeeResponseRest> findEmployeeById(long id) {
        log.info("Buscando usuario por ID");
        EmployeeResponseRest employeeResponseRest = new EmployeeResponseRest();
        List<Employee> employees = new ArrayList<>();

        try {
            Optional<Employee> employee = iEmployeeDao.findById(id);
            if(employee.isPresent()){
                employees.add(employee.get());
                employeeResponseRest.getEmployeeResponse().setEmployee(employees);
            }
            else {
                log.error("Error al consultar Usuario");
                employeeResponseRest.setMetadata("Respuesta NO-OK", "-1", "Usuario no encontrado");
                return new ResponseEntity<EmployeeResponseRest>(employeeResponseRest, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al consultar Usuario");
            employeeResponseRest.setMetadata("Respuesta NO-OK", "-1", "Error al consultar usuario");
            return new ResponseEntity<EmployeeResponseRest>(employeeResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        employeeResponseRest.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
        return new ResponseEntity<EmployeeResponseRest>(employeeResponseRest, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<EmployeeResponseRest> createEmployee(Employee employee) {
        log.info("Agregando usuario");
        EmployeeResponseRest employeeResponseRest = new EmployeeResponseRest();
        List<Employee> employees = new ArrayList<>();

        try {
            Employee employeeAdded = iEmployeeDao.save(employee);

            if(employeeAdded != null){
                employees.add(employeeAdded);
                employeeResponseRest.getEmployeeResponse().setEmployee(employees);
            }
            else {
                log.error("Error al crear ssuario");
                employeeResponseRest.setMetadata("Respuesta NO-OK", "-1", "Usuario no creado");
                return new ResponseEntity<EmployeeResponseRest>(employeeResponseRest, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            log.error("Error al crear el usuario");
            employeeResponseRest.setMetadata("Respuesta NO-OK", "-1", "Error al crear usuario");
            return new ResponseEntity<EmployeeResponseRest>(employeeResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        employeeResponseRest.setMetadata("Respuesta OK", "00", "Usuario creado exitosamente");
        return new ResponseEntity<EmployeeResponseRest>(employeeResponseRest, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<EmployeeResponseRest> updateEmployee(Employee employee, Long id) {
        log.info("Iniciando el método actualizar usuario");
        EmployeeResponseRest employeeResponseRest = new EmployeeResponseRest();
        List<Employee> employees = new ArrayList<>();

        try {
            Optional<Employee> employeeById = iEmployeeDao.findById(id);
            if(employeeById.isPresent()){
                employeeById.get().setName(employee.getName());
                employeeById.get().setLastname(employee.getLastname());
                employeeById.get().setPhone(employee.getPhone());
                employeeById.get().setEmail(employee.getEmail());

                Employee employeeToUpdate = iEmployeeDao.save(employeeById.get());

                if (employeeToUpdate != null){
                    employeeResponseRest.setMetadata("Respuesta OK", "00", "Usuario actualizado exitosamente");
                    employees.add(employeeToUpdate);
                    employeeResponseRest.getEmployeeResponse().setEmployee(employees);
                }else {
                    log.error("Error al actualizar usuario");
                    employeeResponseRest.setMetadata("Respuesta NO-OK", "-1", "Usuario no creado");
                    return new ResponseEntity<EmployeeResponseRest>(employeeResponseRest, HttpStatus.BAD_REQUEST);
                }

            }
            else {
                log.error("Error al actulizar Usuario");
                employeeResponseRest.setMetadata("Respuesta NO-OK", "-1", "Usuario no actualizado");
                return new ResponseEntity<EmployeeResponseRest>(employeeResponseRest, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al actualizar Usuario", e.getMessage());
            e.getStackTrace();
            employeeResponseRest.setMetadata("Respuesta NO-OK", "-1", "Error al actualizar usuario");
            return new ResponseEntity<EmployeeResponseRest>(employeeResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<EmployeeResponseRest>(employeeResponseRest, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<EmployeeResponseRest> deleteEmployee(Long id) {
        log.info("Iniciando el método eliminar usuario");
        EmployeeResponseRest employeeResponseRest = new EmployeeResponseRest();

        try{
            iEmployeeDao.deleteById(id);
            employeeResponseRest.setMetadata("Respuesta OK", "00", "Usuario eliminado exitosamente");

        }catch (Exception e){
            log.error("Error al eliminar usuario", e.getMessage());
            e.getStackTrace();
            employeeResponseRest.setMetadata("Respuesta NO-OK", "-1", "Error al eliminar usuario");
            return new ResponseEntity<EmployeeResponseRest>(employeeResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<EmployeeResponseRest>(employeeResponseRest, HttpStatus.OK);
    }


}
