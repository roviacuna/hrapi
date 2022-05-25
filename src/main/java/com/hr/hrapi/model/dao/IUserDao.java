package com.hr.hrapi.model.dao;

import com.hr.hrapi.model.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserDao extends CrudRepository<User, Long> {
}
