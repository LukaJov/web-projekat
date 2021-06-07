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
import java.util.Optional;

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


        TrainerDTO newTrainerDTO = new TrainerDTO(newTrainer.getId(),newTrainer.getName(), newTrainer.getSurname(),
        newTrainer.getPhoneNumber(), newTrainer.getEmailAddress(), newTrainer.getBirthday(),
        newTrainer.getUserType());

        return new ResponseEntity<>(newTrainerDTO, HttpStatus.CREATED);
    }
//prikaz zahteva za trenera, izmeni posle
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TrainerDTO>> getTrainerRequests()
    {
        List<Trainer> trainers = new ArrayList<>();

        trainers = this.trainerService.findByActive(false);

        List<TrainerDTO> trainerDTOS = new ArrayList<>();

        for (Trainer trainer : trainers) {

            TrainerDTO trainerDTO = new TrainerDTO(trainer.getId(), trainer.getName()
                    , trainer.getSurname(), trainer.getPhoneNumber(),
                    trainer.getEmailAddress(), trainer.getBirthday(), trainer.getUserType());


            trainerDTOS.add(trainerDTO);

        }
        return new ResponseEntity<>(trainerDTOS, HttpStatus.OK);

    }

    //odobravanje zahteva
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrainerDTO> makeActive(@PathVariable Long id) throws Exception {


        Optional<Trainer> optTrainer = this.trainerService.findById(id);
        Trainer trainer = optTrainer.get();
        Trainer updatedTr = new Trainer();
        updatedTr = this.trainerService.update(trainer);

        TrainerDTO trainerDTO = new TrainerDTO(updatedTr.getId(), updatedTr.getName(), updatedTr.getSurname(), updatedTr.getPhoneNumber(),
                updatedTr.getEmailAddress(), updatedTr.getBirthday(), updatedTr.getUserType());

        // Vraćamo odgovor 200 OK, a kroz body odgovora šaljemo podatke o ažuriranom zaposlenom
        return new ResponseEntity<>(trainerDTO, HttpStatus.OK);
    }
}