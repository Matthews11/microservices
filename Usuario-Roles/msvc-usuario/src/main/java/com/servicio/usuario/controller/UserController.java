package com.servicio.usuario.controller;

import com.servicio.usuario.models.entity.User;
import com.servicio.usuario.services.UserService;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.runtime.ObjectMethods;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    private HashMap<String, Object> response = new HashMap<>();

    private List<String> errors = new ArrayList<>();

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        response.clear();
        List<User> users = service.findAll();

        if (users.isEmpty()){
            response.put("response","list users is empty");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.ACCEPTED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        response.clear();
        Optional<User> findUser = service.findById(id);
        if (findUser.isEmpty()){
            response.put("response","users is empty id: "+id);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<User>(findUser.get(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid User user, BindingResult result) {
        User newUser = null;
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
            newUser = service.save(user);
        } catch (DataAccessException ex) {
            response.put("response", "faile");
            response.put("error: ", ex.getStackTrace() + "" + ex.getCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        response.clear();
        Optional<User> findUser= service.findById(id);
        if (findUser.isEmpty()){
            response.put("response","users is empty id: "+id);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            service.delete(id);
            response.put("response","remove successful");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NO_CONTENT);
        }
    }

}
