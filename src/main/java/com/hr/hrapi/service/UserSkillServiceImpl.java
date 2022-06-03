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

import java.util.List;

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
    public ResponseEntity<UserSkillResponseRest> findUserSkillById() {
        return null;
    }

    @Override
    public ResponseEntity<UserSkillResponseRest> createUserSkill() {
        return null;
    }

    @Override
    public ResponseEntity<UserSkillResponseRest> updateUserSkill() {
        return null;
    }

    @Override
    public ResponseEntity<UserSkillResponseRest> deleteUserSkill() {
        return null;
    }
}
