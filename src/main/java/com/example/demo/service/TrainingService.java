package com.example.demo.service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.Training;
import com.example.demo.model.Trainer;
import com.example.demo.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TrainingService {
    @Autowired
    private TrainingRepository trainingRepository;

    public Optional<Training> findById(Long id)
    {
        return this.trainingRepository.findById(id);
    }
}
