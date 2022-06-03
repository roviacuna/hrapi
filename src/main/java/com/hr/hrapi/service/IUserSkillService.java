package com.hr.hrapi.service;

import com.hr.hrapi.response.UserSkillResponseRest;
import org.springframework.http.ResponseEntity;

public interface IUserSkillService {

    public ResponseEntity<UserSkillResponseRest> findUserSkill();
    public ResponseEntity<UserSkillResponseRest> findUserSkillById(Long id);
    public ResponseEntity<UserSkillResponseRest> createUserSkill();
    public ResponseEntity<UserSkillResponseRest> updateUserSkill();
    public ResponseEntity<UserSkillResponseRest> deleteUserSkill();


}
