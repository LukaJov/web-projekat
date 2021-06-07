package com.example.demo.service;

import java.util.Date;
import java.util.List;

import com.example.demo.model.Term;
import com.example.demo.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TermService {
    @Autowired
    private TermRepository termRepository;

    public List<Term> findAll(Sort sort)
    {
        return this.termRepository.findAll();
    }

    public List<Term> findByTrainingName(String name, Sort sort)
    {
        return this.termRepository.findByTrainingNameContaining(name, sort);
    }

    public List<Term> findByTrainingDesc(String desc, Sort sort)
    {
        return this.termRepository.findByTrainingDescContaining(desc, sort);
    }
    //findbytrainingtype probaj

    public List<Term> findByTrainingTrainingType(String trainingType, Sort sort)
    {
        return this.termRepository.findByTrainingTrainingTypeContaining(trainingType, sort);
    }

    public List<Term> findByDate(Date date, Sort sort)
    {
        return this.termRepository.findByDateIsBefore(date, sort);
    }

    public List<Term> findByPrice(Double price, Sort sort)
    {
        return this.termRepository.findByPriceIsLessThanEqual(price, sort);
    }


    //pitaj za sortiranje
    //kako logovanje omoguciti
    //dodaj zahtev, pregled zahteva i odobravanje
    //nova html stranica za dodavanje fitnes centra
    //prikazi sve fitnes centre
    //registracija svih

}
