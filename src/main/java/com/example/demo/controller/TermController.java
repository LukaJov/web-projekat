package com.example.demo.controller;

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

@RestController
@RequestMapping(value = "/api/terms")
public class TermController {

    @Autowired
    private TermService termService;
    //dodaj pageable
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TermDTO>> getTerms(@RequestParam(required = false) String trainingName,  @RequestParam(required = false) String trainingDesc,
                           @RequestParam(required = false) String trainingType, @RequestParam(required = false) Double price,
                           @RequestParam(required = false) Date date
    )
    {
        List<Term> terms = new ArrayList<>();

        if(!(trainingName==null))
        {
            terms = this.termService.findByTrainingName(trainingName);
        }
        else if(!(trainingDesc==null))
        {
           terms = this.termService.findByTrainingDesc(trainingDesc);
        }
        //else if(!(trainingType==null))
        else if(!(price==null))
        {
            terms = this.termService.findByPrice(price);
        }

        else if(!(date==null))
        {
            terms = this.termService.findByDate(date);
        }

        else {
            terms = this.termService.findAll();
        }

        List<TermDTO> termDTOS = new ArrayList<>();

        for (Term term : terms) {

            TrainingDTO trainingDTO = new TrainingDTO(term.getTraining().getId(), term.getTraining().getName()
            , term.getTraining().getDesc(), term.getTraining().getTrainingType(), term.getTraining().getDuration());



            TermDTO termDTO = new TermDTO(term.getId(), trainingDTO, term.getDate(), term.getPrice());

            termDTOS.add(termDTO);
        }


        return new ResponseEntity<>(termDTOS, HttpStatus.OK);

    }

   /* @GetMapping("/terms")
    public String getTermsByTrainingName(String trName)
    {
        List<Term> terms = this.termService.findByTrainingName(trName);

        //promeni da vraca konkretne
        return "terms";
    }

    @GetMapping("/terms")
    public String getTermsByDate(Date date)
    {
        List<Term> terms = this.termService.findByDate(date);

        //promeni da vraca konkretne
        return "terms";
    }

    @GetMapping("/terms")
    public String getTermsByPrice(Double price)
    {
        List<Term> terms = this.termService.findByPrice(price);

        //promeni da vraca konkretne
        return "terms";
    }

    @GetMapping("/terms")
    public String getTermsByDesc(String desc)
    {
        List<Term> terms = this.termService.findByTrainingDesc(desc);

        //promeni da vraca konkretne
        return "terms";
    }*/




}