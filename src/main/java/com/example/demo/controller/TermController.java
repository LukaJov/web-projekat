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

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TermDTO>> getTerms(@RequestParam(required = false) String trainingName,  @RequestParam(required = false) String trainingDesc,
                           @RequestParam(required = false) String trainingType, @RequestParam(required = false) Double price,
                           @RequestParam(required = false) Date date, @RequestParam(required = false, defaultValue ="id,asc") String[] sort)
    {
       List<Order> orders = new ArrayList<Order>();

       if (sort[0].contains(",")) {
        // will sort more than 2 columns
         for (String sortOrder : sort) {
          // sortOrder="column, direction"
           String[] _sort = sortOrder.split(",");
           orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
         }
       } else {
        // sort=[column, direction]
         orders.add(new Order(getSortDirection(sort[1]), sort[0]));
       }

        List<Term> terms = new ArrayList<>();

        if(!(trainingName==null))
        {
            terms = this.termService.findByTrainingName(trainingName, Sort.by(orders));
        }
        else if(!(trainingDesc==null))
        {
           terms = this.termService.findByTrainingDesc(trainingDesc, Sort.by(orders));
        }
        //else if(!(trainingType==null))
        else if(!(trainingType==null))
        {
            terms = this.termService.findByTrainingTrainingType(trainingType, Sort.by(orders));
        }
        else if(!(price==null))
        {
            terms = this.termService.findByPrice(price, Sort.by(orders));
        }
        else if(!(date==null))
        {
            terms = this.termService.findByDate(date, Sort.by(orders));
        }
        else {
            terms = this.termService.findAll(Sort.by(orders));
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