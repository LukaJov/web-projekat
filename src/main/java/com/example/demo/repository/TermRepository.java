package com.example.demo.repository;

import com.example.demo.model.Term;
import com.example.demo.model.Trainer;
import com.example.demo.model.Training;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TermRepository extends JpaRepository<Term, Long> {
    public Optional<Term> findById(Long id);
    List<Term> findByTrainingNameContaining(String name, Sort sort);

    List<Term> findByTrainingDescContaining(String desc, Sort sort);

    List<Term> findByTrainingTrainingTypeContaining(String trainingType, Sort sort);

    List<Term> findByDateIsBefore(Date date, Sort sort);

    List<Term> findByPriceIsLessThanEqual(Double price, Sort sort);

    //List<Term> findByUserId(Long id);



};

