package com.hr.hrapi.service;

import com.hr.hrapi.model.User;
import com.hr.hrapi.model.dao.IUserDao;
import com.hr.hrapi.response.UserResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
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
    public ResponseEntity<UserResponseRest> findUsers() {
        log.info("inicio de método buscar");
        UserResponseRest userResponseRest = new UserResponseRest();

        try {
            List<User> user = (List<User>) iUserDao.findAll();
            userResponseRest.getUserResponse().setUser(user);
            userResponseRest.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
        }catch (Exception e){
            userResponseRest.setMetadata("Respuesta NO-OK", "-1", "Error al consultar los usuarios");
            log.error("Error al consultar usuarios", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<UserResponseRest>(userResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<UserResponseRest>(userResponseRest, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<UserResponseRest> findUserById(long id) {
        log.info("Buscando usuario por ID");
        UserResponseRest userResponseRest = new UserResponseRest();
        List<User> users = new ArrayList<>();

        try {
            Optional<User> user = iUserDao.findById(id);
            if(user.isPresent()){
                users.add(user.get());
                userResponseRest.getUserResponse().setUser(users);
            }
            else {
                log.error("Error al consultar Usuario");
                userResponseRest.setMetadata("Respuesta NO-OK", "-1", "Usuario no encontrado");
                return new ResponseEntity<UserResponseRest>(userResponseRest, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al consultar Usuario");
            userResponseRest.setMetadata("Respuesta NO-OK", "-1", "Error al consultar usuario");
            return new ResponseEntity<UserResponseRest>(userResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userResponseRest.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
        return new ResponseEntity<UserResponseRest>(userResponseRest, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<UserResponseRest> createUser(User user) {
        log.info("Agregando usuario");
        UserResponseRest userResponseRest = new UserResponseRest();
        List<User> users = new ArrayList<>();

        try {
            User userAdded = iUserDao.save(user);

            if(userAdded != null){
                users.add(userAdded);
                userResponseRest.getUserResponse().setUser(users);
            }
            else {
                log.error("Error al crear ssuario");
                userResponseRest.setMetadata("Respuesta NO-OK", "-1", "Usuario no creado");
                return new ResponseEntity<UserResponseRest>(userResponseRest, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            log.error("Error al crear el usuario");
            userResponseRest.setMetadata("Respuesta NO-OK", "-1", "Error al crear usuario");
            return new ResponseEntity<UserResponseRest>(userResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        userResponseRest.setMetadata("Respuesta OK", "00", "Usuario creado exitosamente");
        return new ResponseEntity<UserResponseRest>(userResponseRest, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<UserResponseRest> updateUser(User user, Long id) {
        log.info("Iniciando el método actualizar usuario");
        UserResponseRest userResponseRest = new UserResponseRest();
        List<User> users = new ArrayList<>();

        try {
            Optional<User> userById = iUserDao.findById(id);
            if(userById.isPresent()){
                userById.get().setName(user.getName());
                userById.get().setLastname(user.getLastname());
                userById.get().setPhone(user.getPhone());
                userById.get().setEmail(user.getEmail());

                User userToUpdate = iUserDao.save(userById.get());

                if (userToUpdate != null){
                    userResponseRest.setMetadata("Respuesta OK", "00", "Usuario actualizado exitosamente");
                    users.add(userToUpdate);
                    userResponseRest.getUserResponse().setUser(users);
                }else {
                    log.error("Error al actualizar usuario");
                    userResponseRest.setMetadata("Respuesta NO-OK", "-1", "Usuario no creado");
                    return new ResponseEntity<UserResponseRest>(userResponseRest, HttpStatus.BAD_REQUEST);
                }

            }
            else {
                log.error("Error al actulizar Usuario");
                userResponseRest.setMetadata("Respuesta NO-OK", "-1", "Usuario no actualizado");
                return new ResponseEntity<UserResponseRest>(userResponseRest, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al actualizar Usuario", e.getMessage());
            e.getStackTrace();
            userResponseRest.setMetadata("Respuesta NO-OK", "-1", "Error al actualizar usuario");
            return new ResponseEntity<UserResponseRest>(userResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<UserResponseRest>(userResponseRest, HttpStatus.OK);
    }


}
