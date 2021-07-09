package com.example.demo.controller;

import com.example.demo.model.DTO.FitnessCenterDTO;
import com.example.demo.model.DTO.RoomDTO;
import com.example.demo.model.DTO.TrainerDTO;
import com.example.demo.model.FitnessCenter;
import com.example.demo.model.Room;
import com.example.demo.model.Trainer;
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
@RequestMapping(value = "/api/fitnesscenters")
public class FitnessCenterController {

    @Autowired
    private FitnessCenterService fitnessCenterService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FitnessCenterDTO> createFitnessCenter(@RequestBody FitnessCenterDTO fitnessCenterDTO) throws Exception {

        FitnessCenter fitnessCenter = new FitnessCenter(fitnessCenterDTO.getName(), fitnessCenterDTO.getAddress(),
                fitnessCenterDTO.getPhoneNumber(), fitnessCenterDTO.getEmailAddress());


        FitnessCenter newFitnessCenter = fitnessCenterService.save(fitnessCenter);


        FitnessCenterDTO newFitnessCenterDTO = new FitnessCenterDTO(newFitnessCenter.getId(), newFitnessCenter.getName(),
                newFitnessCenter.getAddress(), newFitnessCenter.getPhoneNumber(), newFitnessCenter.getEmailAddress());

        return new ResponseEntity<>(newFitnessCenterDTO, HttpStatus.CREATED);
    }


    //fali putmapping
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FitnessCenterDTO> changeFitnessCenter( @PathVariable Long id, @RequestBody FitnessCenterDTO fitnessCenterDTO, @RequestParam Long userType)
    {
        if(userType!=3)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        FitnessCenter newFitnessCenter = this.fitnessCenterService.update(fitnessCenterDTO, id);

        FitnessCenterDTO newFitnessCenterDTO = new FitnessCenterDTO(newFitnessCenter.getId(), newFitnessCenter.getName(),
                newFitnessCenter.getAddress(), newFitnessCenter.getPhoneNumber(), newFitnessCenter.getEmailAddress());

        return new ResponseEntity<>(newFitnessCenterDTO, HttpStatus.OK);

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FitnessCenterDTO>> getFitnessCenters()
    {
        List<FitnessCenter> fitnessCenters = new ArrayList<>();

        fitnessCenters = this.fitnessCenterService.findAll();

        List<FitnessCenterDTO> fitnessCenterDTOS = new ArrayList<>();

        for (FitnessCenter fitnessCenter : fitnessCenters) {

            FitnessCenterDTO fitnessCenterDTO = new FitnessCenterDTO(fitnessCenter.getId(), fitnessCenter.getName(),
                    fitnessCenter.getAddress(), fitnessCenter.getPhoneNumber(), fitnessCenter.getEmailAddress()
                    );
            fitnessCenterDTOS.add(fitnessCenterDTO);

        }
        return new ResponseEntity<>(fitnessCenterDTOS, HttpStatus.OK);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteFitnessCenter(@PathVariable Long id, @RequestParam Long userType) {

        if(userType!=3)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.fitnessCenterService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FitnessCenterDTO> getFitnessCenter(@PathVariable Long  id)
    {
        Optional<FitnessCenter> optFitCenter = this.fitnessCenterService.findById(id);
        FitnessCenter fitCenter = optFitCenter.get();
        FitnessCenterDTO fitCenterDTO = new FitnessCenterDTO(fitCenter.getId(), fitCenter.getName(), fitCenter.getAddress(), fitCenter.getPhoneNumber(), fitCenter.getEmailAddress());

        /* this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;*/

        return new ResponseEntity<>(fitCenterDTO, HttpStatus.OK);
    }

    @GetMapping(value="/my", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FitnessCenterDTO> getMyFitnessCenter(@RequestParam Long trainerId)
    {
        List<FitnessCenter> fitnessCenters = new ArrayList<>();
        fitnessCenters = this.fitnessCenterService.findAll();
        for(FitnessCenter fitCenter: fitnessCenters) {
            for(Trainer trainer: fitCenter.getTrainers())
            {
                if(trainer.getId()==trainerId){
                FitnessCenterDTO fitCenterDTO = new FitnessCenterDTO(fitCenter.getId(), fitCenter.getName(), fitCenter.getAddress(), fitCenter.getPhoneNumber(), fitCenter.getEmailAddress());
                return new ResponseEntity<>(fitCenterDTO, HttpStatus.OK);
                }
            }

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


    }



}
