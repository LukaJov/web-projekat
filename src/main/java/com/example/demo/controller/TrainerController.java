package com.example.demo.controller;

import com.example.demo.model.DTO.TermDTO;
import com.example.demo.model.DTO.TrainingDTO;
import com.example.demo.model.Term;
import com.example.demo.service.TrainerService;
import com.example.demo.model.Trainer;
import com.example.demo.model.DTO.TrainerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/trainers")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    //dodaj dto bez sifre!!!
   @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrainerDTO> createTrainer(@RequestBody TrainerDTO trainerDTO) throws Exception {

        Trainer trainer = new Trainer(trainerDTO.getUsername(), trainerDTO.getPassword(),
        trainerDTO.getName(), trainerDTO.getSurname(), trainerDTO.getPhoneNumber(), trainerDTO.getEmailAddress(),
        trainerDTO.getBirthday());


        Trainer newTrainer = this.trainerService.save(trainer);


        TrainerDTO newTrainerDTO = new TrainerDTO(newTrainer.getName(), newTrainer.getSurname(),
        newTrainer.getPhoneNumber(), newTrainer.getEmailAddress(), newTrainer.getBirthday(),
        newTrainer.getUserType());

        return new ResponseEntity<>(newTrainerDTO, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TrainerDTO>> getTrainerRequests(@RequestParam boolean active)
    {
        List<Trainer> trainers = new ArrayList<>();

        trainers = this.trainerService.findByActive(active);

        List<TrainerDTO> trainerDTOS = new ArrayList<>();

        for (Trainer trainer : trainers) {

            TrainerDTO trainerDTO = new TrainerDTO(trainer.getName()
                    , trainer.getSurname(), trainer.getPhoneNumber(),
                    trainer.getEmailAddress(), trainer.getBirthday(), trainer.getUserType());


            trainerDTOS.add(trainerDTO);

        }
        return new ResponseEntity<>(trainerDTOS, HttpStatus.OK);

    }
}