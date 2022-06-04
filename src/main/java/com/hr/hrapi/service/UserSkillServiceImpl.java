package com.hr.hrapi.service;

import com.hr.hrapi.model.Skill;
import com.hr.hrapi.model.User;
import com.hr.hrapi.model.UserSkill;
import com.hr.hrapi.model.dao.IUserDao;
import com.hr.hrapi.model.dao.IUserSkillDao;
import com.hr.hrapi.response.SkillResponseRest;
import com.hr.hrapi.response.UserResponseRest;
import com.hr.hrapi.response.UserSkillResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserSkillServiceImpl implements IUserSkillService{

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private IUserSkillDao iUserSkillDao;


    @Override
    public ResponseEntity<UserSkillResponseRest> findUserSkill() {
        log.info("inicio de método buscar");
        UserSkillResponseRest userSkillResponseRest = new UserSkillResponseRest();

        try {
            List<UserSkill> userSkills = (List<UserSkill>) iUserSkillDao.findAll();
            userSkillResponseRest.getUserSkillResponse().setUserSkill(userSkills);
            userSkillResponseRest.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
        }catch (Exception e){
            userSkillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Error al consultar los usuarios");
            log.error("Error al consultar usuarios", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<UserSkillResponseRest>(userSkillResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<UserSkillResponseRest>(userSkillResponseRest, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserSkillResponseRest> findUserSkillById(Long id) {
        log.info("Buscando usuario-skill por ID");
        UserSkillResponseRest userSkillResponseRest = new UserSkillResponseRest();
        List<UserSkill> userSkills = new ArrayList<>();

        try {
            Optional<UserSkill> userSkill = iUserSkillDao.findById(id);
            if(userSkill.isPresent()){
                userSkills.add(userSkill.get());
                userSkillResponseRest.getUserSkillResponse().setUserSkill(userSkills);
            }
            else {
                log.error("Error al consultar usuario-skill");
                userSkillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Usuario-Skill no encontrado");
                return new ResponseEntity<UserSkillResponseRest>(userSkillResponseRest, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al consultar usuario-skill");
            userSkillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Error al consultar usuario-skill");
            return new ResponseEntity<UserSkillResponseRest>(userSkillResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userSkillResponseRest.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
        return new ResponseEntity<UserSkillResponseRest>(userSkillResponseRest, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserSkillResponseRest> createUserSkill(UserSkill userSkill) {
        log.info("Agregando usuario-skill");
        UserSkillResponseRest userSkillResponseRest = new UserSkillResponseRest();
        List<UserSkill> userSkills = new ArrayList<>();

        try {
            UserSkill userSkillAdded = iUserSkillDao.save(userSkill);

            if(userSkillAdded != null){
                userSkills.add(userSkillAdded);
                userSkillResponseRest.getUserSkillResponse().setUserSkill(userSkills);
            }
            else {
                log.error("Error al crear usuario-skill");
                userSkillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Usuario-skill no creado");
                return new ResponseEntity<UserSkillResponseRest>(userSkillResponseRest, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            log.error("Error al crear el usuario-skill");
            userSkillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Error al crear usuario-skill");
            return new ResponseEntity<UserSkillResponseRest>(userSkillResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        userSkillResponseRest.setMetadata("Respuesta OK", "00", "Usuario-skill creado exitosamente");
        return new ResponseEntity<UserSkillResponseRest>(userSkillResponseRest, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserSkillResponseRest> updateUserSkill(UserSkill userSkill, Long id) {
        log.info("Iniciando el método actualizar User-Skill");
        UserSkillResponseRest userSkillResponseRest = new UserSkillResponseRest();
        List<UserSkill> userSkills = new ArrayList<>();

        try {
            Optional<UserSkill> userSkillDaoById = iUserSkillDao.findById(id);
            if(userSkillDaoById.isPresent()){
                userSkillDaoById.get().setSkill(userSkill.getSkill());
                userSkillDaoById.get().setUser(userSkill.getUser());
                userSkillDaoById.get().setYearExperience(userSkill.getYearExperience());

                UserSkill userSkillToUpdate = iUserSkillDao.save(userSkillDaoById.get());

                if (userSkillToUpdate != null){
                    userSkillResponseRest.setMetadata("Respuesta OK", "00", "Usuario-Skill actualizado exitosamente");
                    userSkills.add(userSkillToUpdate);
                    userSkillResponseRest.getUserSkillResponse().setUserSkill(userSkills);
                }else {
                    log.error("Error al actualizar usuario-skill");
                    userSkillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Usuario-Skill no creado");
                    return new ResponseEntity<UserSkillResponseRest>(userSkillResponseRest, HttpStatus.BAD_REQUEST);
                }

            }
            else {
                log.error("Error al actulizar Usuario-Skill");
                userSkillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Usuari-Skill no actualizado");
                return new ResponseEntity<UserSkillResponseRest>(userSkillResponseRest, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al actualizar Usuario-Skill", e.getMessage());
            e.getStackTrace();
            userSkillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Error al actualizar usuario-skill");
            return new ResponseEntity<UserSkillResponseRest>(userSkillResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<UserSkillResponseRest>(userSkillResponseRest, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserSkillResponseRest> deleteUserSkill(Long id) {
        log.info("Iniciando el método eliminar User-Skill");
        UserSkillResponseRest userSkillResponseRest = new UserSkillResponseRest();
        List<UserSkill> userSkills = new ArrayList<>();

        try{
            Optional<UserSkill> userSkill = iUserSkillDao.findById(id);
            if(userSkill.isPresent()){
                userSkills.add(userSkill.get());
                userSkillResponseRest.getUserSkillResponse().setUserSkill(userSkills);
                iUserSkillDao.deleteById(id);
                userSkillResponseRest.setMetadata("Respuesta OK", "00", "User-Skill eliminado exitosamente");
            }
            else {
                log.error("Error al consultar User-Skill");
                userSkillResponseRest.setMetadata("Respuesta NO-OK", "-1", "User-Skill no encontrado");
                return new ResponseEntity<UserSkillResponseRest>(userSkillResponseRest, HttpStatus.NOT_FOUND);
            }


        }catch (Exception e){
            log.error("Error al eliminar User-Skill", e.getMessage());
            e.getStackTrace();
            userSkillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Error al eliminar user-Skill");
            return new ResponseEntity<UserSkillResponseRest>(userSkillResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<UserSkillResponseRest>(userSkillResponseRest, HttpStatus.OK);
    }
    }

