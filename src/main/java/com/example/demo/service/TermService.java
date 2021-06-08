package com.example.demo.service;

import java.util.ArrayList;
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

    private Sort.Order getSorting(String sort)
    {
        if(sort.equals("price,asc"))
        {
            List<Sort.Order> orders = new ArrayList<>();
            Sort.Order order = new Sort.Order(Sort.Direction.ASC, "price");
            orders.add(order);
            return order;
        }
        else if(sort.equals("price,desc"))
        {
            List<Sort.Order> orders = new ArrayList<>();
            Sort.Order order = new Sort.Order(Sort.Direction.DESC, "price");
            orders.add(order);
            return order;
        }
        else if(sort.equals("date,asc"))
        {
            List<Sort.Order> orders = new ArrayList<>();
            Sort.Order order = new Sort.Order(Sort.Direction.ASC, "date");
            orders.add(order);
            return order;
        }
        else
        {
            List<Sort.Order> orders = new ArrayList<>();
            Sort.Order order = new Sort.Order(Sort.Direction.DESC, "date");
            orders.add(order);
            return order;
        }

    }

    public List<Term> findAll(String sort)
    {
        Sort.Order order = getSorting(sort);
        return this.termRepository.findAll(Sort.by(order));
    }

    public List<Term> findByTrainingName(String name, String sort)
    {
        Sort.Order order = getSorting(sort);
        return this.termRepository.findByTrainingNameContaining(name,Sort.by(order) );
    }

    public List<Term> findByTrainingDesc(String desc, String sort)
    {
        Sort.Order order = getSorting(sort);
        return this.termRepository.findByTrainingDescContaining(desc,Sort.by(order) );
    }
    //findbytrainingtype probaj

    public List<Term> findByTrainingTrainingType(String trainingType, String sort)
    {
        Sort.Order order = getSorting(sort);
        return this.termRepository.findByTrainingTrainingTypeContaining(trainingType,Sort.by(order));
    }

    public List<Term> findByDate(Date date, String sort)
    {
        Sort.Order order = getSorting(sort);
        return this.termRepository.findByDateIsBefore(date, Sort.by(order));
    }

    public List<Term> findByPrice(Double price, String sort)
    {
        Sort.Order order = getSorting(sort);
        return this.termRepository.findByPriceIsLessThanEqual(price, Sort.by(order));
    }

}
