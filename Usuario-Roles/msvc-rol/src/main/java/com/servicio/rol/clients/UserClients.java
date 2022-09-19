package com.servicio.rol.clients;

import com.servicio.rol.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;


@FeignClient(name = "msvc-usuario", url = "http://localhost:6666/user")
public interface UserClients {

    @GetMapping("/find/{id}")
    Optional<User> findAll(@PathVariable Long id);

    @PostMapping("/save")
    User save(User role);

}
