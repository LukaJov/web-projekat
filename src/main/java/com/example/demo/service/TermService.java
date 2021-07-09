package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.*;
import com.example.demo.model.DTO.TermDTO;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;



@Service
public class TermService {
    @Autowired
    private TermRepository termRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FitnessCenterRepository fitnessCenterRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    private Sort.Order getSorting(String sort) {
        if (sort.equals("price,asc")) {
            List<Sort.Order> orders = new ArrayList<>();
            Sort.Order order = new Sort.Order(Sort.Direction.ASC, "price");
            orders.add(order);
            return order;
        } else if (sort.equals("price,desc")) {
            List<Sort.Order> orders = new ArrayList<>();
            Sort.Order order = new Sort.Order(Sort.Direction.DESC, "price");
            orders.add(order);
            return order;
        } else if (sort.equals("date,asc")) {
            List<Sort.Order> orders = new ArrayList<>();
            Sort.Order order = new Sort.Order(Sort.Direction.ASC, "date");
            orders.add(order);
            return order;
        } else {
            List<Sort.Order> orders = new ArrayList<>();
            Sort.Order order = new Sort.Order(Sort.Direction.DESC, "date");
            orders.add(order);
            return order;
        }

    }

    public List<Term> findAll(String sort) {
        Sort.Order order = getSorting(sort);
        return this.termRepository.findAll(Sort.by(order));
    }
    public List<Term> findByTrainerId(Long id)
    {
        return this.termRepository.findByTrainerId(id);
    }
    public Optional<Term> findById(Long id) {
        return this.termRepository.findById(id);
    }

    public List<Term> findByTrainingName(String name, String sort) {
        Sort.Order order = getSorting(sort);
        return this.termRepository.findByTrainingNameContaining(name, Sort.by(order));
    }

    public List<Term> findByTrainingDesc(String desc, String sort) {
        Sort.Order order = getSorting(sort);
        return this.termRepository.findByTrainingDescContaining(desc, Sort.by(order));
    }
    //findbytrainingtype probaj

    public List<Term> findByTrainingTrainingType(String trainingType, String sort) {
        Sort.Order order = getSorting(sort);
        return this.termRepository.findByTrainingTrainingTypeContaining(trainingType, Sort.by(order));
    }

    public List<Term> findByDate(Date date, String sort) {
        Sort.Order order = getSorting(sort);
        return this.termRepository.findByDateIsBefore(date, Sort.by(order));
    }

    public List<Term> findByPrice(Double price, String sort) {
        Sort.Order order = getSorting(sort);
        return this.termRepository.findByPriceIsLessThanEqual(price, Sort.by(order));
    }

    /*public List<Term> findByUserId(Long id)
    {
        return this.termRepository.findByUserId(id);
    }*/

    public Term signup(Term term, User user) {
        term.getUserToDo().add(user);
        user.getToDo().add(term);
        term.setNumberOfUsers(term.getNumberOfUsers() + 1);
        this.userRepository.save(user);
        Term savedTerm = this.termRepository.save(term);
        return savedTerm;
    }

    public Term signout(Term term, User user) {
        term.getUserToDo().remove(user);
        user.getToDo().remove(term);
        term.setNumberOfUsers(term.getNumberOfUsers() - 1);
        this.userRepository.save(user);
        Term savedTerm = this.termRepository.save(term);
        return savedTerm;
    }

    public Term save(TermDTO termDTO, FitnessCenter fitnessCenter, Room room, Trainer trainer, Training training)
    {
        Term term = new Term();

        term.setNumberOfUsers(0);
        term.setDate(termDTO.getDate());
        term.setPrice(termDTO.getPrice());
        term.setFitCenter(fitnessCenter);
        term.setRoom(room);
        term.setTrainer(trainer);
        term.setTraining(training);
        /*fitnessCenter.getTerms().add(term);
        room.getTerms().add(term);
        trainer.getTerms().add(term);*/
        this.fitnessCenterRepository.save(fitnessCenter);
        this.roomRepository.save(room);
        this.trainerRepository.save(trainer);

        return this.termRepository.save(term);
    }

}
