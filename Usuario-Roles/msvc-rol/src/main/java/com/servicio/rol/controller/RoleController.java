package com.servicio.rol.controller;

import com.servicio.rol.models.entity.Role;
import com.servicio.rol.services.RoleService;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService service;

    private Map<String, Object> response = new HashMap<>();

    private List<String> errors = new ArrayList<>();

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        response.clear();
        List<Role> roles = service.findAll();
        if (roles.isEmpty()) {
            response.put("response", "list role is empty");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Role>>(roles, HttpStatus.ACCEPTED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        response.clear();
        Optional<Role> optionalRole = service.findById(id);
        if (optionalRole.isEmpty()) {
            response.put("response", "role is empty id: " + id);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Role>(optionalRole.get(), HttpStatus.ACCEPTED);

    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid Role role, BindingResult result) {
        Role newRole = null;
        response.clear();
        if (result.hasErrors()) {
            errors.clear();
            for (FieldError fieldError : result.getFieldErrors()) {
                errors.add("Field '" + fieldError.getField() + "' " + fieldError.getDefaultMessage());
                response.put("erors",errors);
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            }
        }
        try {
            newRole = service.save(role);
        } catch (DataAccessException ex) {
            response.put("response", "faile");
            response.put("error: ", ex.getStackTrace() + "" + ex.getCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Role>(newRole, HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        response.clear();
        Optional<Role> findRole=service.findById(id);
        if (findRole.isPresent()) {
            service.delete(id);
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }else {
            response.put("response", "role is empty id: " + id);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
