package com.hr.hrapi.service;

import com.hr.hrapi.model.Skill;
import com.hr.hrapi.response.SkillResponseRest;
import org.springframework.http.ResponseEntity;

public interface ISkillService {
    public ResponseEntity<SkillResponseRest> findSkills();
    public ResponseEntity<SkillResponseRest> findSkillById(long id);
    public ResponseEntity<SkillResponseRest> createSkill(Skill skill);
    public ResponseEntity<SkillResponseRest> updateSkill(Skill skill, Long id);
    public ResponseEntity<SkillResponseRest> deleteSkill(Long id);
}
