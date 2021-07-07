package com.example.demo.controller;

import com.example.demo.model.DTO.TrainerDTO;
import com.example.demo.model.DTO.TypeDTO;
import com.example.demo.model.Grade;
import com.example.demo.model.Trainer;
import com.example.demo.model.User;
import com.example.demo.service.GradeService;
import com.example.demo.service.UserService;
import org.hibernate.usertype.UserType;
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
    @Autowired
    private GradeService gradeService;
    /*@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrainerDTO> createTerm(@RequestBody TermDTO termDTO) throws Exception {

        Term term = new Term()


        Term newTerm = this.termService.save(term);


        return new ResponseEntity<>(newTermDTO, HttpStatus.CREATED);
    }*/
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


    @GetMapping(consumes =MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE, value = "/todo")
    public ResponseEntity<List<TermDTO>> getOwnTerms(@RequestBody TypeDTO typeDTO)
    {
        List<Term> terms = new ArrayList<>();

        List<TermDTO> termDTOS = new ArrayList<>();

        terms = this.termService.findAll("price,asc");

        /*Optional<User> optUser = this.userService.findById(id);
        User user = optUser.get();*/


        for(Term term: terms)
        {
            for(User user: term.getUserToDo()) {
                if(user.getId()== typeDTO.getId()) {
                    TrainingDTO trainingDTO = new TrainingDTO(term.getTraining().getName()
                            , term.getTraining().getDesc(), term.getTraining().getTrainingType(), term.getTraining().getDuration());

                    TermDTO termDTO = new TermDTO(trainingDTO, term.getDate(), term.getPrice());

                    termDTOS.add(termDTO);
                }
            }
        }

        return new ResponseEntity<>(termDTOS, HttpStatus.OK);
    }

    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE, value = "/done")
    public ResponseEntity<List<TermDTO>> getDoneTerms(@RequestBody TypeDTO typeDTO)
    {
        List<Term> terms = new ArrayList<>();

        List<TermDTO> termDTOS = new ArrayList<>();

        terms = this.termService.findAll("price,asc");

        /*Optional<User> optUser = this.userService.findById(id);
        User user = optUser.get();*/


        for(Term term: terms)
        {
            for(User user: term.getUserDone()) {
                if(user.getId()== typeDTO.getId()) {
                    TrainingDTO trainingDTO = new TrainingDTO(term.getTraining().getName()
                            , term.getTraining().getDesc(), term.getTraining().getTrainingType(), term.getTraining().getDuration());

                    TermDTO termDTO = new TermDTO(trainingDTO, term.getDate(), term.getPrice());

                    termDTOS.add(termDTO);
                }
            }
        }

        return new ResponseEntity<>(termDTOS, HttpStatus.OK);
    }

    @GetMapping (consumes =MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE, value = "/doneungraded")
    public ResponseEntity<List<TermDTO>> getUngradedTerms(@RequestBody TypeDTO typeDTO)
    {
        List<Term> terms = new ArrayList<>();

        List<TermDTO> termDTOS = new ArrayList<>();


        terms = this.termService.findAll("price,asc");

        Optional<User> optUser = this.userService.findById(typeDTO.getId());
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

                TermDTO termDTO = new TermDTO(trainingDTO, term.getDate(), term.getPrice());

                termDTOS.add(termDTO);
            }

        }


        return new ResponseEntity<>(termDTOS, HttpStatus.OK);
    }

    @GetMapping(consumes =MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE, value = "/donegraded")
    public ResponseEntity<List<TermDTO>> getGradedTerms(@RequestBody TypeDTO typeDTO)
    {
        List<Term> terms = new ArrayList<>();

        List<TermDTO> termDTOS = new ArrayList<>();

        /*terms = this.termService.findAll("price,asc");*/

        Optional<User> optUser = this.userService.findById(typeDTO.getId());
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

                TermDTO termDTO = new TermDTO(trainingDTO, term.getDate(), term.getPrice());

                termDTOS.add(termDTO);
            }
        }

        return new ResponseEntity<>(termDTOS, HttpStatus.OK);
    }

    //prijava za trening
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TermDTO> signUpOrOut(@RequestBody TypeDTO typeDTO, @PathVariable Long id,@RequestParam Boolean upOrOut) throws Exception {

        if(typeDTO.getUserType().equals("Member")) {
            Optional<Term> optTerm = this.termService.findById(id);
            Term term = optTerm.get();
            Term updatedTerm = new Term();

            Optional<User> optUser = this.userService.findById(typeDTO.getId());
            User user = optUser.get();

            if(upOrOut) {
                updatedTerm = this.termService.signup(term, user);
            }
            else{
                updatedTerm = this.termService.signout(term, user);
            }

            TrainingDTO trainingDTO = new TrainingDTO(updatedTerm.getTraining().getName()
                    , updatedTerm.getTraining().getDesc(), updatedTerm.getTraining().getTrainingType(), updatedTerm.getTraining().getDuration());

            TermDTO termDTO = new TermDTO(trainingDTO, updatedTerm.getDate(), updatedTerm.getPrice());
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

        TermDTO termDTO = new TermDTO(trainingDTO, term.getDate(), term.getPrice());

        return new ResponseEntity<>(termDTO, HttpStatus.OK);
    }
    // davanje ocena promeni sutra
    @PutMapping(value = "/grades", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Grade> giveGrade(@PathVariable Long id, @RequestParam Long userId, @RequestParam String userType, @RequestParam Grade grade) throws Exception {

        if(userType.equals("Member")) {
            Optional<Term> optTerm = this.termService.findById(id);
            Term term = optTerm.get();

            Optional<User> optUser = this.userService.findById(id);
            User user = optUser.get();

            Grade newGrade = this.gradeService.saveGrade(grade, user, term);

            return new ResponseEntity<>(newGrade, HttpStatus.OK);
        }

        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



}