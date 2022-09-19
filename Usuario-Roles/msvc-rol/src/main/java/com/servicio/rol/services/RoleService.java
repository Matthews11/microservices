package com.servicio.rol.services;

import com.servicio.rol.models.User;
import com.servicio.rol.models.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Optional<Role> findById(Long id);

    List<Role> findAll();

    Role save(Role role);

    List<User> findAllUser();
    void delete(Long id);

}
