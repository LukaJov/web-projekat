package com.example.demo.controller;

import com.example.demo.model.DTO.FitnessCenterDTO;
import com.example.demo.model.DTO.RoomDTO;
import com.example.demo.model.DTO.TrainerDTO;
import com.example.demo.model.FitnessCenter;
import com.example.demo.model.Room;
import com.example.demo.model.Trainer;
import com.example.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.FitnessCenterService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/fitnesscenters/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private FitnessCenterService fitnessCenterService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Room> createRoom(@RequestBody Room room, @RequestParam Long centerId) throws Exception {

        Optional<FitnessCenter> optCenter = this.fitnessCenterService.findById(centerId);
        FitnessCenter fitCenter = optCenter.get();

        Room newRoom = roomService.save(room, fitCenter);


        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {

        this.roomService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}