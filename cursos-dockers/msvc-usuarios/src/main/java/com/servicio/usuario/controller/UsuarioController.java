package com.servicio.usuario.controller;

import com.servicio.usuario.models.entity.Usuario;
import com.servicio.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;


    private List<String> errores = new ArrayList<>();


    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
          Map<String,Object> mensajes= new HashMap<>();
        List<Usuario> usuarioList = service.findAll();

        if(usuarioList.isEmpty()){
            mensajes.put("respuesta","no hay registro de usuario");
            return new ResponseEntity<Map<String,Object>>(mensajes, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<Usuario>>(usuarioList,HttpStatus.ACCEPTED);
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<?> obtener(@PathVariable(name = "id")Long id){
          Map<String,Object> mensajes= new HashMap<>();
        Optional<Usuario> obtenerUsuario = service.findById(id);
        if (!obtenerUsuario.isPresent()){
            mensajes.put("respuesta","No existe usuario con ese id"+id);
            return new ResponseEntity<Map<String,Object>>(mensajes,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Usuario>(obtenerUsuario.get(),HttpStatus.ACCEPTED);
    }

    @PostMapping("/crear")
    public ResponseEntity< ?> crear(@RequestBody @Valid Usuario usuario, BindingResult validacion){

          Map<String,Object> mensajes= new HashMap<>();
        errores.clear();
        if (validacion.hasErrors()){

            for (FieldError errorCampos: validacion.getFieldErrors()){
                errores.add("El campo '"+errorCampos.getField()+"' "+errorCampos.getDefaultMessage());
            }

            mensajes.put("error",errores);

            return  new ResponseEntity<Map<String,Object>>(mensajes,HttpStatus.INTERNAL_SERVER_ERROR);

        }else {
            return new ResponseEntity<Usuario>(service.save(usuario),HttpStatus.CREATED);
        }
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity< ?> editar(@RequestBody @Valid Usuario usuario, BindingResult validacion,@PathVariable(name = "id") Long id){
          Map<String,Object> mensajes= new HashMap<>();
        errores.clear();
        Optional<Usuario> obtenerUsuario =service.findById(id);

        if (validacion.hasErrors()){
            for (FieldError errorCampos: validacion.getFieldErrors()) {
                errores.add("El campo '"+errorCampos.getField()+"' "+errorCampos.getDefaultMessage());
            }
            mensajes.put("error", errores);
            return  new ResponseEntity<Map<String,Object>>(mensajes,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (obtenerUsuario.isPresent()){
            Usuario usuario1 = obtenerUsuario.get();
            usuario1.setNombre(usuario.getNombre());
            usuario1.setMail(usuario.getMail());
            usuario1.setPassword(usuario.getPassword());
            return new ResponseEntity<Usuario>(service.save(usuario1),HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable(name = "id")Long id){
          Map<String,Object> mensajes= new HashMap<>();
        Optional<Usuario> obtenerUsuario =service.findById(id);
        if (obtenerUsuario.isPresent()){
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            mensajes.put("respuesta","no se encontro el usuario con el id "+id);
            return  new ResponseEntity<Map<String,Object>>(mensajes,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
