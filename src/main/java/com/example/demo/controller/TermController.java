package com.example.demo.controller;

import com.example.demo.model.DTO.*;
import com.example.demo.model.Grade;
import com.example.demo.model.Trainer;
import com.example.demo.model.User;
import com.example.demo.service.GradeService;
import com.example.demo.service.UserService;
import org.hibernate.usertype.UserType;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import com.example.demo.service.TermService;
import com.example.demo.model.Term;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/{centerId}/terms")
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
    public ResponseEntity<List<TermDTO>> getTerms(@PathVariable Long centerId, @RequestParam(required = false) Long id, @RequestParam(required = false) Long userType, @RequestParam(required = false) String trainingName,  @RequestParam(required = false) String trainingDesc,
                                                                                                            @RequestParam(required = false) String trainingType, @RequestParam(required = false) Double price,
                                                                                                            @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date date, @RequestParam(required = false, defaultValue ="price,asc") String sort)
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

        if(userType!=null && id !=null)
        {
        if(userType==1) {

            for(Term term: terms)
            {
                for(User user: term.getUserDone()) {
                    if(user.getId()==  id) {
                       term.setId((long)0);
                    }
                }
            }
            for(Term term: terms)
            {
                for(User user: term.getUserToDo()) {
                    if(user.getId()== id) {
                        term.setId((long)0);
                    }
                }
            }
            for (Term term : terms) {
                if(term.getId()!=0) {
                    if(term.getFitCenter().getId()==centerId)
                    {
                        TrainingDTO trainingDTO = new TrainingDTO(term.getTraining().getName()
                                , term.getTraining().getDesc(), term.getTraining().getTrainingType(), term.getTraining().getDuration());

                        TermDTO termDTO = new TermDTO(term.getId(), trainingDTO, term.getDate(), term.getPrice());

                        termDTOS.add(termDTO);
                    }
                }
            }

        }

        }
        else {
        for (Term term : terms) {
            if(term.getFitCenter().getId()==centerId) {
                TrainingDTO trainingDTO = new TrainingDTO(term.getTraining().getName()
                        , term.getTraining().getDesc(), term.getTraining().getTrainingType(), term.getTraining().getDuration());

                TermDTO termDTO = new TermDTO(term.getId(), trainingDTO, term.getDate(), term.getPrice());

                termDTOS.add(termDTO);
            }
        }}



        return new ResponseEntity<>(termDTOS, HttpStatus.OK);

    }

    @GetMapping(value="/multi", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TermDTO>> getTermsMulti(@PathVariable Long centerId, @RequestParam(required = false) Long id, @RequestParam(required = false) Long userType, @RequestParam(required = false) String trainingName, @RequestParam(required = false) String trainingDesc,
                                                       @RequestParam(required = false) String trainingType, @RequestParam(required = false) Double price,
                                                       @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date date, @RequestParam(required = false, defaultValue ="price,asc") String sort)
    {
        List<Term> terms = new ArrayList<>();
        List<Term> trName = new ArrayList<>();
        List<Term> trDesc = new ArrayList<>();
        List<Term> trType = new ArrayList<>();
        List<Term> trPrice = new ArrayList<>();
        List<Term> trDate = new ArrayList<>();

        terms = this.termService.findAll(sort);

        if(!(trainingName==null))
        {
            trName = this.termService.findByTrainingName(trainingName, sort);
        }
        else {
            trName = this.termService.findAll(sort);
        }
        terms.retainAll(trName);

        if(!(trainingDesc==null))
        {
            trDesc = this.termService.findByTrainingDesc(trainingDesc, sort);
        }
        else {
            trDesc = this.termService.findAll(sort);
        }
        terms.retainAll(trDesc);

        if(!(trainingType==null))
        {
            trType = this.termService.findByTrainingTrainingType(trainingType, sort);
        }
        else{
            trType = this.termService.findAll(sort);
        }
        terms.retainAll(trType);
        if(!(price==null))
        {
            trPrice = this.termService.findByPrice(price, sort);
        }
        else
        {
            trPrice = this.termService.findAll(sort);
        }
        terms.retainAll(trPrice);
        if(!(date==null))
        {
            trDate = this.termService.findByDate(date, sort);
        }
        else {
            trDate = this.termService.findAll(sort);
        }
        terms.retainAll(trDate);


        List<TermDTO> termDTOS = new ArrayList<>();

        if(userType!=null && id !=null)
        {
            if(userType==1) {

                for(Term term: terms)
                {
                    for(User user: term.getUserDone()) {
                        if(user.getId()==  id) {
                            term.setId((long)0);
                        }
                    }
                }
                for(Term term: terms)
                {
                    for(User user: term.getUserToDo()) {
                        if(user.getId()== id) {
                            term.setId((long)0);
                        }
                    }
                }
                for (Term term : terms) {
                    if(term.getId()!=0) {
                        if(term.getFitCenter().getId()==centerId)
                        {
                            TrainingDTO trainingDTO = new TrainingDTO(term.getTraining().getName()
                                    , term.getTraining().getDesc(), term.getTraining().getTrainingType(), term.getTraining().getDuration());

                            TermDTO termDTO = new TermDTO(term.getId(), trainingDTO, term.getDate(), term.getPrice());

                            termDTOS.add(termDTO);
                        }
                    }
                }

            }

        }
        else {
            for (Term term : terms) {
                if(term.getFitCenter().getId()==centerId) {
                    TrainingDTO trainingDTO = new TrainingDTO(term.getTraining().getName()
                            , term.getTraining().getDesc(), term.getTraining().getTrainingType(), term.getTraining().getDuration());

                    TermDTO termDTO = new TermDTO(term.getId(), trainingDTO, term.getDate(), term.getPrice());

                    termDTOS.add(termDTO);
                }
            }}



        return new ResponseEntity<>(termDTOS, HttpStatus.OK);

    }


    @PostMapping(consumes =MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE, value = "/todo")
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

                    TermDTO termDTO = new TermDTO(term.getId(), trainingDTO, term.getDate(), term.getPrice());

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

                    TermDTO termDTO = new TermDTO(term.getId(), trainingDTO, term.getDate(), term.getPrice());

                    termDTOS.add(termDTO);
                }
            }
        }

        return new ResponseEntity<>(termDTOS, HttpStatus.OK);
    }

    @PostMapping (consumes =MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE, value = "/doneungraded")
    public ResponseEntity<List<TermDTO>> getUngradedTerms(@RequestBody TypeDTO typeDTO)
    {
        List<Term> terms = new ArrayList<>();

        List<TermDTO> termDTOS = new ArrayList<>();


        terms = this.termService.findAll("price,asc");
        long idd = typeDTO.getId();
        Optional<User> optUser = this.userService.findById(idd);
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

                TermDTO termDTO = new TermDTO(term.getId(), trainingDTO, term.getDate(), term.getPrice());

                termDTOS.add(termDTO);
            }

        }


        return new ResponseEntity<>(termDTOS, HttpStatus.OK);
    }

    @PostMapping(consumes =MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE, value = "/donegraded")
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
                Double grd = grade.getGrd();
                TrainingDTO trainingDTO = new TrainingDTO(term.getTraining().getName()
                        , term.getTraining().getDesc(), term.getTraining().getTrainingType(), term.getTraining().getDuration());

                TermDTO termDTO = new TermDTO(term.getId(), trainingDTO, term.getDate(), term.getPrice(), grd);

                termDTOS.add(termDTO);
            }
        }

        return new ResponseEntity<>(termDTOS, HttpStatus.OK);
    }

    //prijava za trening
    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TermDTO> signUpOrOut(/*@RequestBody TypeDTO typeDTO,*/ @PathVariable Long id, @RequestParam String upOrOut, @RequestParam Long userId, @RequestParam Long userType) throws Exception {

        boolean upout;

        if(upOrOut.equals("true"))
        {
            upout = true;
        }
        else {
            upout = false;
        }
        if(userType==1) {
            Optional<Term> optTerm = this.termService.findById(id);
            Term term = optTerm.get();
            Term updatedTerm = new Term();

            Optional<User> optUser = this.userService.findById(/*typeDTO.getId()*/userId);
            User user = optUser.get();

            if(upout) {
                if(term.getRoom().getCapacity()<=term.getNumberOfUsers())
                {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                updatedTerm = this.termService.signup(term, user);
            }
            else{
                updatedTerm = this.termService.signout(term, user);
            }

            TrainingDTO trainingDTO = new TrainingDTO(updatedTerm.getTraining().getName()
                    , updatedTerm.getTraining().getDesc(), updatedTerm.getTraining().getTrainingType(), updatedTerm.getTraining().getDuration());

            TermDTO termDTO = new TermDTO(term.getId(), trainingDTO, updatedTerm.getDate(), updatedTerm.getPrice());
            return new ResponseEntity<>(termDTO, HttpStatus.OK);
        }

        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TermDTO> getTerm(@PathVariable Long id, @RequestParam Long userType)
    {
        if(userType!=1)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Term> optTerm = this.termService.findById(id);
        Term term = optTerm.get();
        int numUsers = term.getNumberOfUsers();
        int capacity = term.getRoom().getCapacity();
        int noOfSpots = capacity-numUsers;

        TrainingDTO trainingDTO = new TrainingDTO(term.getTraining().getName()
                , term.getTraining().getDesc(), term.getTraining().getTrainingType(), term.getTraining().getDuration());

        TermDTO termDTO = new TermDTO(term.getId(), trainingDTO, term.getDate(), term.getPrice(), noOfSpots);

        return new ResponseEntity<>(termDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/grades/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GradeDTO> giveGrade(@PathVariable Long id, @RequestParam Long userId, @RequestParam Long userType, @RequestBody GradeDTO gradeDTO) throws Exception {

        if(gradeDTO.getGrade()< 0.0 || gradeDTO.getGrade()>5.0)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(userType==1) {
            Optional<Term> optTerm = this.termService.findById(id);
            Term term = optTerm.get();

            Optional<User> optUser = this.userService.findById(userId);
            User user = optUser.get();
            Trainer trainer = term.getTrainer();
            trainer.setAvgGrade(((trainer.getAvgGrade()* trainer.getNumOfGrades())+ gradeDTO.getGrade())/ (trainer.getNumOfGrades()+1));
            Grade newGrade = this.gradeService.saveGrade(gradeDTO, user, term);

            GradeDTO newGradeDTO = new GradeDTO(newGrade.getId(), newGrade.getGrd());
            return new ResponseEntity<>(newGradeDTO, HttpStatus.OK);
        }

        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



}