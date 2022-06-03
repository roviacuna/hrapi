package com.hr.hrapi.model.dao;

import com.hr.hrapi.model.UserSkill;
import org.springframework.data.repository.CrudRepository;

public interface IUserSkillDao extends CrudRepository<UserSkill, Long> {
}
