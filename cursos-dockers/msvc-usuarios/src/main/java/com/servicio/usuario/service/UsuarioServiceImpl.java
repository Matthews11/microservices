package com.servicio.usuario.service;

import com.servicio.usuario.models.entity.Usuario;
import com.servicio.usuario.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repo;


    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) repo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Long id) {
        return Optional.ofNullable(repo.findById(id)).orElse(null);
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        return repo.save(usuario);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findByMail(String mail) {
        return Optional.ofNullable(repo.findByMail(mail)).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByMail(String mail) {
        return repo.existsByMail(mail);
    }
}
