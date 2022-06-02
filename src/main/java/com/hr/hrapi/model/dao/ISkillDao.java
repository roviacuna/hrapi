package com.hr.hrapi.model.dao;

import com.hr.hrapi.model.Skill;
import org.springframework.data.repository.CrudRepository;

public interface ISkillDao extends CrudRepository<Skill, Long> {
}
