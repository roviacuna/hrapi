package com.hr.hrapi.service;

import com.hr.hrapi.model.Skill;
import com.hr.hrapi.model.User;
import com.hr.hrapi.model.dao.ISkillDao;
import com.hr.hrapi.response.SkillResponseRest;
import com.hr.hrapi.response.UserResponseRest;
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
public class SkillServiceImpl implements ISkillService{
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private ISkillDao iSkillDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<SkillResponseRest> findSkills() {
        log.info("Inicio de método buscar");
        SkillResponseRest skillResponseRest = new SkillResponseRest();

        try {
            List<Skill> skill = (List<Skill>) iSkillDao.findAll();
            skillResponseRest.getSkillResponse().setSkill(skill);
            skillResponseRest.setMetadata("Respuesta OK", "00", "Respuesta exitosa al consultar los skills");
        }catch (Exception e){
            skillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Respuesta NO exitosa");
            log.error("Error al consultar skills", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<SkillResponseRest>(skillResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<SkillResponseRest>(skillResponseRest, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<SkillResponseRest> findSkillById(long id) {
        log.info("Buscando usuario por ID");
        SkillResponseRest skillResponseRest = new SkillResponseRest();
        List<Skill> skills = new ArrayList<>();

        try {
            Optional<Skill> skill = iSkillDao.findById(id);
            if(skill.isPresent()){
                skills.add(skill.get());
                skillResponseRest.getSkillResponse().setSkill(skills);
            }
            else {
                log.error("Error al consultar Skills");
                skillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Skill no encontrado");
                return new ResponseEntity<SkillResponseRest>(skillResponseRest, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al consultar Skill");
            skillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Error al consultar skill");
            return new ResponseEntity<SkillResponseRest>(skillResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        skillResponseRest.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
        return new ResponseEntity<SkillResponseRest>(skillResponseRest, HttpStatus.OK);
    }


    @Override
    @Transactional
    public ResponseEntity<SkillResponseRest> createSkill(Skill skill) {
        log.info("Agregando skills");
        SkillResponseRest skillResponseRest = new SkillResponseRest();
        List<Skill> skills = new ArrayList<>();

        try {
            Skill skillAdded = iSkillDao.save(skill);

            if(skillAdded != null){
                skills.add(skillAdded);
                skillResponseRest.getSkillResponse().setSkill(skills);
            }
            else {
                log.error("Error al crear Skill");
                skillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Skill no creado");
                return new ResponseEntity<SkillResponseRest>(skillResponseRest, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            log.error("Error al crear el usuario");
            skillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Error al crear usuario");
            return new ResponseEntity<SkillResponseRest>(skillResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        skillResponseRest.setMetadata("Respuesta OK", "00", "Usuario creado exitosamente");
        return new ResponseEntity<SkillResponseRest>(skillResponseRest, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<SkillResponseRest> updateSkill(Skill skill, Long id) {
        log.info("Iniciando el método actualizar skill");
        SkillResponseRest skillResponseRest = new SkillResponseRest();
        List<Skill> skills = new ArrayList<>();

        try {
            Optional<Skill> skillById = iSkillDao.findById(id);
            if(skillById.isPresent()){
                skillById.get().setName(skill.getName());
                skillById.get().setDescription(skill.getDescription());

                Skill skillToUpdate = iSkillDao.save(skillById.get());

                if (skillToUpdate != null){
                    skillResponseRest.setMetadata("Respuesta OK", "00", "Skill actualizado exitosamente");
                    skills.add(skillToUpdate);
                    skillResponseRest.getSkillResponse().setSkill(skills);
                }else {
                    log.error("Error al actualizar skill");
                    skillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Usuario no creado");
                    return new ResponseEntity<SkillResponseRest>(skillResponseRest, HttpStatus.BAD_REQUEST);
                }

            }
            else {
                log.error("Error al actulizar Usuario");
                skillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Usuario no actualizado");
                return new ResponseEntity<SkillResponseRest>(skillResponseRest, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al actualizar Usuario", e.getMessage());
            e.getStackTrace();
            skillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Error al actualizar usuario");
            return new ResponseEntity<SkillResponseRest>(skillResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<SkillResponseRest>(skillResponseRest, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<SkillResponseRest> deleteSkill(Long id) {
        log.info("Iniciando el método eliminar skill");
        SkillResponseRest skillResponseRest = new SkillResponseRest();
        List<Skill> skills = new ArrayList<>();

        try{
            Optional<Skill> skill = iSkillDao.findById(id);
            if(skill.isPresent()){
                skills.add(skill.get());
                skillResponseRest.getSkillResponse().setSkill(skills);
                iSkillDao.deleteById(id);
                skillResponseRest.setMetadata("Respuesta OK", "00", "Skill eliminado exitosamente");
            }
            else {
                log.error("Error al consultar skill");
                skillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Usuario no encontrado");
                return new ResponseEntity<SkillResponseRest>(skillResponseRest, HttpStatus.NOT_FOUND);
            }


        }catch (Exception e){
            log.error("Error al eliminar skill", e.getMessage());
            e.getStackTrace();
            skillResponseRest.setMetadata("Respuesta NO-OK", "-1", "Error al eliminar usuario");
            return new ResponseEntity<SkillResponseRest>(skillResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<SkillResponseRest>(skillResponseRest, HttpStatus.OK);
    }
}
