package com.example.demo.repository;

import com.example.demo.model.Term;
import com.example.demo.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TermRepository extends JpaRepository<Term, Long> {

    List<Term> findByTrainingName(String name);

    List<Term> findByTrainingDesc(String desc);

    //List<Term> findByTrainingTrainingType(tType trainingType);

    List<Term> findByDate(Date date);

    List<Term> findByPrice(Double price);


}

