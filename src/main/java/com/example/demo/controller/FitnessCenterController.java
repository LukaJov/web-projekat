package com.example.demo.controller;

import com.example.demo.model.DTO.FitnessCenterDTO;
import com.example.demo.model.DTO.TrainerDTO;
import com.example.demo.model.FitnessCenter;
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
    public ResponseEntity<Void> deleteFitnessCenter(@PathVariable Long id) {

        this.fitnessCenterService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
