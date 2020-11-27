package org.johnpc.boot.controller;

import org.johnpc.boot.UserDaoService;
import org.johnpc.boot.exception.UserNotFoundException;
import org.johnpc.boot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URL;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDaoService userDaoService;

    @GetMapping("/users")
    public List<User> users(){
        return this.userDaoService.getAll();
    }

    @GetMapping("/user/{id}")
    public User findById(@PathVariable int id){
        User user = this.userDaoService.findUser(id);
        if(user == null) {
            throw  new UserNotFoundException("id " + id);
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> saveUser(@RequestBody User user) {
        this.userDaoService.saveUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(this.userDaoService.getAll());
    }
}
