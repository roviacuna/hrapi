package com.hr.hrapi.service;

import com.hr.hrapi.model.EmployeeSkill;
import com.hr.hrapi.model.dao.IEmployeeSkillDao;
import com.hr.hrapi.response.EmployeeSkillResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeSkillServiceImpl implements IEmployeeSkillService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private IEmployeeSkillDao iEmployeeSkillDao;


    @Override
    public ResponseEntity<EmployeeSkillResponseRest> findEmployeeSkill() {
        log.info("inicio de método buscar");
        EmployeeSkillResponseRest employeeSkillResponseRest = new EmployeeSkillResponseRest();

        try {
            List<EmployeeSkill> employeeSkills = (List<EmployeeSkill>) iEmployeeSkillDao.findAll();
            employeeSkillResponseRest.getEmployeeSkillResponse().setEmployeeSkill(employeeSkills);
            employeeSkillResponseRest.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
        }catch (Exception e){
            employeeSkillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Error al consultar los usuarios");
            log.error("Error al consultar usuarios", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<EmployeeSkillResponseRest>(employeeSkillResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<EmployeeSkillResponseRest>(employeeSkillResponseRest, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EmployeeSkillResponseRest> findEmployeeSkillById(Long id) {
        log.info("Buscando usuario-skill por ID");
        EmployeeSkillResponseRest employeeSkillResponseRest = new EmployeeSkillResponseRest();
        List<EmployeeSkill> employeeSkills = new ArrayList<>();

        try {
            Optional<EmployeeSkill> employeeSkill = iEmployeeSkillDao.findById(id);
            if(employeeSkill.isPresent()){
                employeeSkills.add(employeeSkill.get());
                employeeSkillResponseRest.getEmployeeSkillResponse().setEmployeeSkill(employeeSkills);
            }
            else {
                log.error("Error al consultar usuario-skill");
                employeeSkillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Usuario-Skill no encontrado");
                return new ResponseEntity<EmployeeSkillResponseRest>(employeeSkillResponseRest, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al consultar usuario-skill");
            employeeSkillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Error al consultar usuario-skill");
            return new ResponseEntity<EmployeeSkillResponseRest>(employeeSkillResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        employeeSkillResponseRest.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
        return new ResponseEntity<EmployeeSkillResponseRest>(employeeSkillResponseRest, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EmployeeSkillResponseRest> createEmployeeSkill(EmployeeSkill employeeSkill) {
        log.info("Agregando usuario-skill");
        EmployeeSkillResponseRest employeeSkillResponseRest = new EmployeeSkillResponseRest();
        List<EmployeeSkill> employeeSkills = new ArrayList<>();

        try {
            EmployeeSkill employeeSkillAdded = iEmployeeSkillDao.save(employeeSkill);

            if(employeeSkillAdded != null){
                employeeSkills.add(employeeSkillAdded);
                employeeSkillResponseRest.getEmployeeSkillResponse().setEmployeeSkill(employeeSkills);
            }
            else {
                log.error("Error al crear usuario-skill");
                employeeSkillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Usuario-skill no creado");
                return new ResponseEntity<EmployeeSkillResponseRest>(employeeSkillResponseRest, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            log.error("Error al crear el usuario-skill");
            employeeSkillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Error al crear usuario-skill");
            return new ResponseEntity<EmployeeSkillResponseRest>(employeeSkillResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        employeeSkillResponseRest.setMetadata("Respuesta OK", "00", "Usuario-skill creado exitosamente");
        return new ResponseEntity<EmployeeSkillResponseRest>(employeeSkillResponseRest, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EmployeeSkillResponseRest> updateEmployeeSkill(EmployeeSkill employeeSkill, Long id) {
        log.info("Iniciando el método actualizar Employee-Skill");
        EmployeeSkillResponseRest employeeSkillResponseRest = new EmployeeSkillResponseRest();
        List<EmployeeSkill> employeeSkills = new ArrayList<>();

        try {
            Optional<EmployeeSkill> employeeSkillDaoById = iEmployeeSkillDao.findById(id);
            if(employeeSkillDaoById.isPresent()){
                employeeSkillDaoById.get().setSkill(employeeSkill.getSkill());
                employeeSkillDaoById.get().setEmployee(employeeSkill.getEmployee());
                employeeSkillDaoById.get().setYearExperience(employeeSkill.getYearExperience());

                EmployeeSkill employeeSkillToUpdate = iEmployeeSkillDao.save(employeeSkillDaoById.get());

                if (employeeSkillToUpdate != null){
                    employeeSkillResponseRest.setMetadata("Respuesta OK", "00", "Usuario-Skill actualizado exitosamente");
                    employeeSkills.add(employeeSkillToUpdate);
                    employeeSkillResponseRest.getEmployeeSkillResponse().setEmployeeSkill(employeeSkills);
                }else {
                    log.error("Error al actualizar usuario-skill");
                    employeeSkillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Usuario-Skill no creado");
                    return new ResponseEntity<EmployeeSkillResponseRest>(employeeSkillResponseRest, HttpStatus.BAD_REQUEST);
                }

            }
            else {
                log.error("Error al actulizar Usuario-Skill");
                employeeSkillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Usuari-Skill no actualizado");
                return new ResponseEntity<EmployeeSkillResponseRest>(employeeSkillResponseRest, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al actualizar Usuario-Skill", e.getMessage());
            e.getStackTrace();
            employeeSkillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Error al actualizar usuario-skill");
            return new ResponseEntity<EmployeeSkillResponseRest>(employeeSkillResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<EmployeeSkillResponseRest>(employeeSkillResponseRest, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EmployeeSkillResponseRest> deleteEmployeeSkill(Long id) {
        log.info("Iniciando el método eliminar Employee-Skill");
        EmployeeSkillResponseRest employeeSkillResponseRest = new EmployeeSkillResponseRest();
        List<EmployeeSkill> employeeSkills = new ArrayList<>();

        try{
            Optional<EmployeeSkill> employeeSkill = iEmployeeSkillDao.findById(id);
            if(employeeSkill.isPresent()){
                employeeSkills.add(employeeSkill.get());
                employeeSkillResponseRest.getEmployeeSkillResponse().setEmployeeSkill(employeeSkills);
                iEmployeeSkillDao.deleteById(id);
                employeeSkillResponseRest.setMetadata("Respuesta OK", "00", "Employee-Skill eliminado exitosamente");
            }
            else {
                log.error("Error al consultar Employee-Skill");
                employeeSkillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Employee-Skill no encontrado");
                return new ResponseEntity<EmployeeSkillResponseRest>(employeeSkillResponseRest, HttpStatus.NOT_FOUND);
            }


        }catch (Exception e){
            log.error("Error al eliminar Employee-Skill", e.getMessage());
            e.getStackTrace();
            employeeSkillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Error al eliminar employee-Skill");
            return new ResponseEntity<EmployeeSkillResponseRest>(employeeSkillResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<EmployeeSkillResponseRest>(employeeSkillResponseRest, HttpStatus.OK);
    }
    }

