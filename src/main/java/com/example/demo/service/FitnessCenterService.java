package com.example.demo.service;
import java.util.Date;
import java.util.List;

import com.example.demo.model.FitnessCenter;
import com.example.demo.model.Term;
import com.example.demo.repository.FitnessCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FitnessCenterService {

    @Autowired
    private FitnessCenterRepository fitnessCenterRepository;

    public FitnessCenter save(FitnessCenter fitnessCenter) {
        return this.fitnessCenterRepository.save(fitnessCenter);
    }
    public void delete(Long id){this.fitnessCenterRepository.deleteById(id);}

    public List<FitnessCenter> findAll() {
        return this.fitnessCenterRepository.findAll();
    }


};