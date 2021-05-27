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

    //pitaj za sortiranje


}
