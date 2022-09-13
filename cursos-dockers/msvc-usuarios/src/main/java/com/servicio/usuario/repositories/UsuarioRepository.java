package com.servicio.usuario.repositories;

import com.servicio.usuario.models.entity.Usuario;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Primary
public interface UsuarioRepository extends CrudRepository<Usuario,Long> {
    @Query("select u from Usuario u where u.mail=?1")
    Optional<Usuario> findByMail(String mail);

    boolean existsByMail(String mail);

}
