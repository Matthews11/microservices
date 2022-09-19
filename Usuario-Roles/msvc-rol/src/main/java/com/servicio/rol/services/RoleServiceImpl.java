package com.servicio.rol.services;

import com.servicio.rol.clients.UserClients;
import com.servicio.rol.models.User;
import com.servicio.rol.models.entity.Role;
import com.servicio.rol.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository repo;

    @Autowired
    private UserClients clients;

    @Override
    @Transactional(readOnly = true)
    public Optional<Role> findById(Long id) {
        return Optional.ofNullable(repo.findById(id)).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> findAll() {
        return (List<Role>) repo.findAll();
    }

    @Override
    @Transactional
    public Role save(Role role) {
        return repo.save(role);
    }

    @Override
    public List<User> findAllUser() {
        return null;
    }


    @Override
    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
