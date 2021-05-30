package com.example.demo.repository;

import com.example.demo.model.Term;
import com.example.demo.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TermRepository extends JpaRepository<Term, Long> {

    List<Term> findByTrainingName(String name);

    List<Term> findByTrainingDesc(String desc);

    List<Term> findByTrainingTrainingType(String trainingType);

    List<Term> findByDate(Date date);

    List<Term> findByPrice(Double price);

    List<Term> findAllByOrderByPriceAsc();

    List<Term> findAllByOrderByPriceDesc();

    List<Term> findAllByOrderByDateAsc();

    List<Term> findAllByOrderByDateDesc();


}

