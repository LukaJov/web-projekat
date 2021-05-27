package com.example.demo.controller;

import com.example.demo.model.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.TermService;

import java.util.Date;
import java.util.List;


@RestController
public class TermController {

    @Autowired
    private TermService termService;

    //dodaj request params!!!!!!!

    @GetMapping("/terms")
    public String getTerms()
    {
        List<Term> terms = this.termService.findAll();

        //promeni da vraca konkretne
        return "terms";
    }

    @GetMapping("/terms")
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
    }




}