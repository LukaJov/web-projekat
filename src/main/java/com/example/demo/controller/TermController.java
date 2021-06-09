package com.example.demo.controller;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import com.example.demo.model.DTO.TermDTO;
import com.example.demo.model.DTO.TrainingDTO;
import com.example.demo.service.TermService;
import com.example.demo.model.Term;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/terms")
public class TermController {

    @Autowired
    private TermService termService;
    //dodaj pageable



    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TermDTO>> getTerms(/*@RequestParam Long id, @RequestParam String userType,*/ @RequestParam(required = false) String trainingName,  @RequestParam(required = false) String trainingDesc,
                           @RequestParam(required = false) String trainingType, @RequestParam(required = false) Double price,
                           @RequestParam(required = false) Date date, @RequestParam(required = false, defaultValue ="price,asc") String sort)
    {





        List<Term> terms = new ArrayList<>();

        if(!(trainingName==null))
        {
            terms = this.termService.findByTrainingName(trainingName, sort);
        }
        else if(!(trainingDesc==null))
        {
           terms = this.termService.findByTrainingDesc(trainingDesc, sort);
        }

        else if(!(trainingType==null))
        {
            terms = this.termService.findByTrainingTrainingType(trainingType, sort);
        }
        else if(!(price==null))
        {
            terms = this.termService.findByPrice(price, sort);
        }
        else if(!(date==null))
        {
            terms = this.termService.findByDate(date, sort);
        }
        else {
            terms = this.termService.findAll(sort);
        }

        List<TermDTO> termDTOS = new ArrayList<>();

        for (Term term : terms) {

            TrainingDTO trainingDTO = new TrainingDTO(term.getTraining().getName()
            , term.getTraining().getDesc(), term.getTraining().getTrainingType(), term.getTraining().getDuration());

            TermDTO termDTO = new TermDTO(trainingDTO, term.getDate(), term.getPrice());

            termDTOS.add(termDTO);
        }

        return new ResponseEntity<>(termDTOS, HttpStatus.OK);

    }

   //pitaj da li prikaz clanova i trenera





}