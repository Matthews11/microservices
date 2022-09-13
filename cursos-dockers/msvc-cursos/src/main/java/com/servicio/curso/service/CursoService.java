package com.servicio.curso.service;

import com.servicio.curso.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    Optional<Curso> findById(Long id);

    List<Curso> findAll();

    Curso save(Curso curso);

    void delete(Long id);
}
