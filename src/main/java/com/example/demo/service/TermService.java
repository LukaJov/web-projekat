package com.example.demo.service;

import java.util.Date;
import java.util.List;

import com.example.demo.model.Term;
import com.example.demo.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TermService {
    @Autowired
    private TermRepository termRepository;

    public List<Term> findAll()
    {
        return this.termRepository.findAll();
    }

    public List<Term> findByTrainingName(String name)
    {
        return this.termRepository.findByTrainingName(name);
    }

    public List<Term> findByTrainingDesc(String desc)
    {
        return this.termRepository.findByTrainingDesc(desc);
    }
    //findbytrainingtype probaj

    public List<Term> findByDate(Date date)
    {
        return this.termRepository.findByDate(date);
    }

    public List<Term> findByPrice(Double price)
    {
        return this.termRepository.findByPrice(price);
    }

    public List<Term> findAllByOrderByPriceAsc(){return this.termRepository.findAllByOrderByPriceAsc();};

    public List<Term> findAllByOrderByPriceDesc(){return this.termRepository.findAllByOrderByPriceDesc();};

    public List<Term> findAllByOrderByDateAsc(){return this.termRepository.findAllByOrderByDateAsc();};

    public List<Term> findAllByOrderByDateDesc(){return this.termRepository.findAllByOrderByDateDesc();};


    //pitaj za sortiranje
    //kako logovanje omoguciti
    //dodaj zahtev, pregled zahteva i odobravanje
    //nova html stranica za dodavanje fitnes centra
    //prikazi sve fitnes centre
    //registracija svih

}
