package com.servicio.curso.controller;

import com.servicio.curso.entity.Curso;
import com.servicio.curso.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CursoService servicio;

    private List<String> errores = new ArrayList<String>();

    @GetMapping("/listar")
    public ResponseEntity<?> findAll(){
        Map<String,Object> response = new HashMap<>();
        List<Curso> cursoList = servicio.findAll();
        if (cursoList.isEmpty()){
            response.put("respuesta","No hay cursos ");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }else {
            return new ResponseEntity<List<Curso>>(cursoList, HttpStatus.OK);
        }
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id")Long id){
        Map<String,Object> response = new HashMap<>();
        Optional<Curso> cursoOptional = servicio.findById(id);
        if (cursoOptional.isPresent()){
            return new ResponseEntity<Curso>(cursoOptional.get(),HttpStatus.ACCEPTED);
        }else {
            response.put("respuesta","No hay curso con el id "+id);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody @Valid Curso curso, BindingResult validar){
        Map<String,Object> response= new HashMap<>();
        if (validar.hasErrors()){
            return validacion(validar, response);
        }else {
            return new ResponseEntity<Curso>(servicio.save(curso),HttpStatus.CREATED);
        }
    }



    @PutMapping("/modificar/{id}")
    public ResponseEntity< ?> editar(@RequestBody @Valid Curso curso, BindingResult validar,
                                     @PathVariable(name = "id") Long id){
        Map<String,Object> response= new HashMap<>();
        Optional<Curso> obtener =servicio.findById(id);
        if (validar.hasErrors()){
            return validacion(validar, response);
        }

        if (obtener.isPresent()){
            Curso curso1 = obtener.get();
            curso1.setNombre(curso.getNombre());
            return new ResponseEntity<Curso>(servicio.save(curso1),HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable(name = "id")Long id){
        Optional<Curso> obtener =servicio.findById(id);
        Map<String,Object> response= new HashMap<>();
        errores.clear();
        if (obtener.isPresent()){
            servicio.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            response.put("respuesta","no se encontro curso con el id "+id);
            return  new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
        }

    }
    private ResponseEntity<Map<String, Object>> validacion(BindingResult validar, Map<String, Object> response) {
        errores.clear();
        for (FieldError errorCampos: validar.getFieldErrors()) {
            errores.add("El campo '"+errorCampos.getField()+"' "+errorCampos.getDefaultMessage());
        }
        response.put("error",errores);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }
}
