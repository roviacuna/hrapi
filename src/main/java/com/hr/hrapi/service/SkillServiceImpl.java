package com.hr.hrapi.service;

import com.hr.hrapi.model.Skill;
import com.hr.hrapi.model.dao.ISkillDao;
import com.hr.hrapi.response.SkillResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SkillServiceImpl implements ISkillService{
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private ISkillDao iSkillDao;

    @Override
    @Transactional(readOnly = true)
    public SkillResponseRest findSkills() {
        log.info("Inicio de método buscar");
        SkillResponseRest skillResponseRest = new SkillResponseRest();

        try {
            List<Skill> skill = (List<Skill>) iSkillDao.findAll();
            skillResponseRest.getSkillResponse().setSkill(skill);
            skillResponseRest.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
        }catch (Exception e){
            skillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Respuesta NO exitosa");
            log.error("Error al consultar skills", e.getMessage());
            e.getStackTrace();
        }
        return skillResponseRest;
    }
}
