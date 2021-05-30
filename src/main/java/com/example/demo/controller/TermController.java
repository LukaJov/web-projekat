package com.example.demo.controller;

import com.example.demo.model.DTO.TermDTO;
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

    //dodaj request params!!!!!!!

    @GetMapping()
    public String getTerms(@RequestParam(required = false, defaultValue = "all") String getBy)
    {
        if(getBy.equals("all")) {
            List<Term> terms = this.termService.findAll();
        }
       /* else if(getBy.equals("trainingName"))
        {
            List<Term> terms = this.termService.findByTrainingName();
        }
        else if(getBy.equals("trainingDesc"))
        {
            List<Term> terms = this.termService.findByTrainingDesc();
        }*/

        //promeni da vraca konkretne
        return "terms";
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