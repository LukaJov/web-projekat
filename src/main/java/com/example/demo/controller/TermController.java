package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.model.DTO.RoomDTO;
import com.example.demo.model.DTO.TrainerDTO;
import com.example.demo.service.UserService;
import org.hibernate.usertype.UserType;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import com.example.demo.model.DTO.TermDTO;
import com.example.demo.model.DTO.TrainingDTO;
import com.example.demo.service.TermService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/terms")
public class TermController {

    @Autowired
    private TermService termService;
    //dodaj pageable
    @Autowired
    private UserService userService;

    @PostMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TermDTO> addTerm(@RequestBody TermDTO termDTO)
    {
        Training training = new Training(termDTO.getTrainingDTO().getName(), termDTO.getTrainingDTO().getDesc(),
                termDTO.getTrainingDTO().getTrainingType(), termDTO.getTrainingDTO().getDuration());
        Room room = new Room(termDTO.getRoomDTO().getCapacity(), termDTO.getRoomDTO().getLabel());
        Term term = new Term(termDTO.getDate(), termDTO.getPrice(), termDTO.getNumberOfUsers(), room, training);


    }

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
            RoomDTO roomDTO = new RoomDTO(term.getRoom().getCapacity(), term.getRoom().getLabel());
            TermDTO termDTO = new TermDTO(trainingDTO, term.getDate(), term.getPrice(), term.getNumberOfUsers(), roomDTO);

            termDTOS.add(termDTO);
        }

        return new ResponseEntity<>(termDTOS, HttpStatus.OK);

    }


    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE, value = "/todo")
    public ResponseEntity<List<TermDTO>> getOwnTerms(@RequestParam Long id, @RequestParam String userType)
    {
        List<Term> terms = new ArrayList<>();

        List<TermDTO> termDTOS = new ArrayList<>();

        terms = this.termService.findAll("price,asc");

        /*Optional<User> optUser = this.userService.findById(id);
        User user = optUser.get();*/


       for(Term term: terms)
        {
            for(User user: term.getUserToDo()) {
                if(user.getId()==id) {
                    TrainingDTO trainingDTO = new TrainingDTO(term.getTraining().getName()
                            , term.getTraining().getDesc(), term.getTraining().getTrainingType(), term.getTraining().getDuration());
                    RoomDTO roomDTO = new RoomDTO(term.getRoom().getCapacity(), term.getRoom().getLabel());
                    TermDTO termDTO = new TermDTO(trainingDTO, term.getDate(), term.getPrice(), term.getNumberOfUsers());

                    termDTOS.add(termDTO);
                }
            }
        }

        return new ResponseEntity<>(termDTOS, HttpStatus.OK);
    }

    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE, value = "/done")
    public ResponseEntity<List<TermDTO>> getDoneTerms(@RequestParam Long id, @RequestParam String userType)
    {
        List<Term> terms = new ArrayList<>();

        List<TermDTO> termDTOS = new ArrayList<>();

        terms = this.termService.findAll("price,asc");

        /*Optional<User> optUser = this.userService.findById(id);
        User user = optUser.get();*/


        for(Term term: terms)
        {
            for(User user: term.getUserDone()) {
                if(user.getId()==id) {
                    TrainingDTO trainingDTO = new TrainingDTO(term.getTraining().getName()
                            , term.getTraining().getDesc(), term.getTraining().getTrainingType(), term.getTraining().getDuration());

                    RoomDTO roomDTO = new RoomDTO(term.getRoom().getCapacity(), term.getRoom().getLabel());
                    TermDTO termDTO = new TermDTO(trainingDTO, term.getDate(), term.getPrice(),term.getNumberOfUsers(), roomDTO);

                    termDTOS.add(termDTO);
                }
            }
        }

        return new ResponseEntity<>(termDTOS, HttpStatus.OK);
    }

    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE, value = "/donegraded")
    public ResponseEntity<List<TermDTO>> getUngradedTerms(@RequestParam Long id, @RequestParam String userType)
    {
        List<Term> terms = new ArrayList<>();

        List<TermDTO> termDTOS = new ArrayList<>();


        terms = this.termService.findAll("price,asc");

        Optional<User> optUser = this.userService.findById(id);
        User user = optUser.get();


        for(Term term: user.getDone())
        {

            Boolean ungraded = true;
            for(Grade grade: user.getGrades())
            {
                Term trm = grade.getTerm();
                if(trm.getId()== term.getId())
                {
                    ungraded = false;
                }
            }

            if(ungraded)
            {
                TrainingDTO trainingDTO = new TrainingDTO(term.getTraining().getName()
                        , term.getTraining().getDesc(), term.getTraining().getTrainingType(), term.getTraining().getDuration());

                RoomDTO roomDTO = new RoomDTO(term.getRoom().getCapacity(), term.getRoom().getLabel());
                TermDTO termDTO = new TermDTO(trainingDTO, term.getDate(), term.getPrice(), term.getNumberOfUsers(), roomDTO);


                termDTOS.add(termDTO);
            }

        }


        return new ResponseEntity<>(termDTOS, HttpStatus.OK);
    }

    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE, value = "/doneungraded")
    public ResponseEntity<List<TermDTO>> getGradedTerms(@RequestParam Long id, @RequestParam String userType)
    {
        List<Term> terms = new ArrayList<>();

        List<TermDTO> termDTOS = new ArrayList<>();

        /*terms = this.termService.findAll("price,asc");*/

        Optional<User> optUser = this.userService.findById(id);
        User user = optUser.get();


        /*for(Term term: terms)
        {
            for(User user: term.getUserDone()) {
                if (user.getId() == id)
                for(Grade grade: term.getGrades()){
                    {
                        TrainingDTO trainingDTO = new TrainingDTO(term.getTraining().getName()
                                , term.getTraining().getDesc(), term.getTraining().getTrainingType(), term.getTraining().getDuration());

                        TermDTO termDTO = new TermDTO(trainingDTO, term.getDate(), term.getPrice());

                        termDTOS.add(termDTO);
                    }
                }
            }
            }
        }*/

        for(Grade grade: user.getGrades())
        {
            if(grade!=null) {
                Term term = grade.getTerm();

                TrainingDTO trainingDTO = new TrainingDTO(term.getTraining().getName()
                        , term.getTraining().getDesc(), term.getTraining().getTrainingType(), term.getTraining().getDuration());

                RoomDTO roomDTO = new RoomDTO(term.getRoom().getCapacity(), term.getRoom().getLabel());
                TermDTO termDTO = new TermDTO(trainingDTO, term.getDate(), term.getPrice(), term.getNumberOfUsers(), roomDTO);


                termDTOS.add(termDTO);
            }
        }

        return new ResponseEntity<>(termDTOS, HttpStatus.OK);
    }

    //prijava za trening
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TermDTO> signUpOrOut(@PathVariable Long id, @RequestParam Long userId, @RequestParam String userType, @RequestParam Boolean upOrOut) throws Exception {

        if(userType.equals("Member")) {
            Optional<Term> optTerm = this.termService.findById(id);
            Term term = optTerm.get();
            Term updatedTerm = new Term();

            Optional<User> optUser = this.userService.findById(id);
            User user = optUser.get();

            if(upOrOut) {
                updatedTerm = this.termService.signup(term, user);
            }
            else{
                updatedTerm = this.termService.signout(term, user);
            }

            TrainingDTO trainingDTO = new TrainingDTO(updatedTerm.getTraining().getName()
                    , updatedTerm.getTraining().getDesc(), updatedTerm.getTraining().getTrainingType(), updatedTerm.getTraining().getDuration());

            RoomDTO roomDTO = new RoomDTO(term.getRoom().getCapacity(), term.getRoom().getLabel());
            TermDTO termDTO = new TermDTO(trainingDTO, term.getDate(), term.getPrice(), term.getNumberOfUsers(), roomDTO);

            return new ResponseEntity<>(termDTO, HttpStatus.OK);
        }

        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TermDTO> getTerm(@PathVariable Long id)
    {
        Optional<Term> optTerm = this.termService.findById(id);
        Term term = optTerm.get();

        TrainingDTO trainingDTO = new TrainingDTO(term.getTraining().getName()
                , term.getTraining().getDesc(), term.getTraining().getTrainingType(), term.getTraining().getDuration());

        RoomDTO roomDTO = new RoomDTO(term.getRoom().getCapacity(), term.getRoom().getLabel());
        TermDTO termDTO = new TermDTO(trainingDTO, term.getDate(), term.getPrice(), term.getNumberOfUsers(), roomDTO);


        return new ResponseEntity<>(termDTO, HttpStatus.OK);
    }
// davanje ocena promeni sutra
    @PutMapping(value = "/done/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TermDTO> giveGrade(@PathVariable Long id, @RequestParam Long userId, @RequestParam String userType, @RequestParam Double grade) throws Exception {

        if(userType.equals("Member")) {
            Optional<Term> optTerm = this.termService.findById(id);
            Term term = optTerm.get();
            Term updatedTerm = new Term();

            Optional<User> optUser = this.userService.findById(id);
            User user = optUser.get();


            TrainingDTO trainingDTO = new TrainingDTO(updatedTerm.getTraining().getName()
                    , updatedTerm.getTraining().getDesc(), updatedTerm.getTraining().getTrainingType(), updatedTerm.getTraining().getDuration());

            RoomDTO roomDTO = new RoomDTO(term.getRoom().getCapacity(), term.getRoom().getLabel());
            TermDTO termDTO = new TermDTO(trainingDTO, term.getDate(), term.getPrice(), term.getNumberOfUsers(), roomDTO);

            return new ResponseEntity<>(termDTO, HttpStatus.OK);
        }

        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



}