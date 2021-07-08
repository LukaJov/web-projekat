package com.example.demo.controller;

import com.example.demo.model.DTO.UserDTO;

import com.example.demo.model.Trainer;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.UserService;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) throws Exception {

        User user = new User(userDTO.getUsername(), userDTO.getPassword(),
                userDTO.getName(), userDTO.getSurname(), userDTO.getPhoneNumber(), userDTO.getEmailAddress(),
                userDTO.getBirthday());


        User newUser = this.userService.save(user);


        UserDTO newUserDTO = new UserDTO(newUser.getId(), newUser.getName(), newUser.getSurname(),
                newUser.getPhoneNumber(), newUser.getEmailAddress(), newUser.getBirthday(),
                newUser.getUserType());

        return new ResponseEntity<>(newUserDTO, HttpStatus.CREATED);
    }

    @GetMapping(value= "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id, @RequestParam Long userType)
    {
        if(userType!=1)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<User> optUser = this.userService.findById(id);
        User user = optUser.get();

        UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getSurname(),
                user.getPhoneNumber(), user.getEmailAddress(), user.getBirthday(),
                user.getUserType());

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }


}
