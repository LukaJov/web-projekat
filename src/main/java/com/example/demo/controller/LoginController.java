package com.example.demo.controller;


import com.example.demo.model.DTO.TermDTO;
import com.example.demo.service.TrainerService;
import com.example.demo.model.Trainer;
import com.example.demo.model.DTO.TrainerDTO;
import com.example.demo.service.UserService;
import com.example.demo.model.User;
import com.example.demo.model.DTO.UserDTO;
import com.example.demo.service.AdminService;
import com.example.demo.model.Admin;
import com.example.demo.model.DTO.AdminDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private AdminService adminService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> login(@RequestParam String username, @RequestParam String password, @RequestParam String userType) throws Exception
    {
        if(userType.equals("Member"))
        {
            User user = this.userService.findByUsername(username);
            if(user==null)
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            if(!(user.getPassword().equals(password)))
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if(!(user.isActive()))
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getSurname(),
                    user.getPhoneNumber(), user.getEmailAddress(), user.getBirthday(),
                    user.getUserType());

            return new ResponseEntity<>(userDTO, HttpStatus.OK);

        }

        else if(userType.equals("Trainer"))
        {
            Trainer trainer = this.trainerService.findByUsername(username);
            if(trainer==null)
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            if(!(trainer.getPassword().equals(password)))
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if(!(trainer.isActive()))
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            UserDTO userDTO = new UserDTO(trainer.getId(), trainer.getName(), trainer.getSurname(),
                    trainer.getPhoneNumber(), trainer.getEmailAddress(), trainer.getBirthday(),
                    trainer.getUserType());

            return new ResponseEntity<>(userDTO, HttpStatus.OK);

        }
        else {
            Admin admin = this.adminService.findByUsername(username);
            if(admin==null)
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            if(!(admin.getPassword().equals(password)))
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if(!(admin.isActive()))
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            UserDTO userDTO = new UserDTO(admin.getId(), admin.getName(), admin.getSurname(),
                    admin.getPhoneNumber(), admin.getEmailAddress(), admin.getBirthday(),
                    admin.getUserType());

            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }

    }
}
