package com.servicio.rol.repositories;

import com.servicio.rol.models.User;
import com.servicio.rol.models.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {
    List<User> findAllById(Long id);

}
