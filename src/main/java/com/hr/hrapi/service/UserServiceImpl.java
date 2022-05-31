package com.hr.hrapi.service;

import com.hr.hrapi.model.User;
import com.hr.hrapi.model.dao.IUserDao;
import com.hr.hrapi.response.UserReponseRest;
import com.hr.hrapi.response.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private IUserDao iUserDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<UserReponseRest> findUsers() {
        log.info("inicio de método buscar");
        UserReponseRest userReponseRest = new UserReponseRest();

        try {
            List<User> user = (List<User>) iUserDao.findAll();
            userReponseRest.getUserResponse().setUser(user);
            userReponseRest.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
        }catch (Exception e){
            userReponseRest.setMetadata("Respuesta NO-OK", "-1", "Error al consultar los usuarios");
            log.error("Error al consultar usuarios", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<UserReponseRest>(userReponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<UserReponseRest>(userReponseRest, HttpStatus.OK);
    }
}
