package com.servicio.usuario.services;


import com.servicio.usuario.models.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);

    List<User> findAll();

    User save(User role);



    void delete(Long id);
}
