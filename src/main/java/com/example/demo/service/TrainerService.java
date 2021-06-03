package com.example.demo.service;

import java.util.Date;
import java.util.List;

import com.example.demo.model.Trainer;
import com.example.demo.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerService {
    @Autowired
    private TrainerRepository trainerRepository;

    public Trainer save(Trainer trainer)
    {
        return this.trainerRepository.save(trainer);
    }

    public List<Trainer> findByActive(boolean active)
    {
        return this.trainerRepository.findByActive(active);
    }

}