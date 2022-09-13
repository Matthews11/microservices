package com.servicio.usuario.repositories;

import com.servicio.usuario.models.entity.Usuario;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface UsuarioRepository extends CrudRepository<Usuario,Long> {
}
