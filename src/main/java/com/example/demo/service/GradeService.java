package com.example.demo.service;

import java.util.Date;
import java.util.List;

import com.example.demo.model.Grade;
import com.example.demo.model.Term;
import com.example.demo.model.User;
import com.example.demo.repository.GradeRepository;
import com.example.demo.repository.TermRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeService {
    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private TermRepository termRepository;

    @Autowired
    private UserRepository userRepository;

    public Grade saveGrade(Grade grade, User user, Term term)
    {
        grade.setGivenBy(user);
        grade.setTerm(term);
        user.getGrades().add(grade);
        term.getGrades().add(grade);
        this.termRepository.save(term);
        this.userRepository.save(user);

        return this.gradeRepository.save(grade);
    }
}