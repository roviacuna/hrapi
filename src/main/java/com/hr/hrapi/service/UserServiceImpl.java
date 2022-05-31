package com.hr.hrapi.service;

import com.hr.hrapi.model.User;
import com.hr.hrapi.model.dao.IUserDao;
import com.hr.hrapi.response.UserReponseRest;
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

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<UserReponseRest> findUserById(long id) {
        log.info("Buscando usuario por ID");
        UserReponseRest userReponseRest = new UserReponseRest();
        List<User> users = new ArrayList<>();

        try {
            Optional<User> user = iUserDao.findById(id);
            if(user.isPresent()){
                users.add(user.get());
                userReponseRest.getUserResponse().setUser(users);
            }
            else {
                log.error("Error al consultar Usuario");
                userReponseRest.setMetadata("Respuesta NO-OK", "-1", "Usuario no encontrado");
                return new ResponseEntity<UserReponseRest>(userReponseRest, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al consultar Usuario");
            userReponseRest.setMetadata("Respuesta NO-OK", "-1", "Error al consultar usuario");
            return new ResponseEntity<UserReponseRest>(userReponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userReponseRest.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
        return new ResponseEntity<UserReponseRest>(userReponseRest, HttpStatus.OK);
    }
}
