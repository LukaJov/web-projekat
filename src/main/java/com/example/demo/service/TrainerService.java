package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.Admin;
import com.example.demo.model.Trainer;
import com.example.demo.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerService {
    @Autowired
    private TrainerRepository trainerRepository;

    public Trainer findByUsername(String username){
        return this.trainerRepository.findByUsername(username);
    }

    public Trainer save(Trainer trainer)
    {
        return this.trainerRepository.save(trainer);
    }
    public Trainer saveAsAdmin(Trainer trainer){
        trainer.setActive(true);
        return this.trainerRepository.save(trainer);
    }
    public List<Trainer> findByActive(boolean active)
    {
        return this.trainerRepository.findByActive(active);
    }

    public Optional<Trainer> findById(Long id){return this.trainerRepository.findById(id);}

    public Trainer update(Trainer trainerToUpdate)
    {
        trainerToUpdate.setActive(true);

        Trainer savedTr = this.trainerRepository.save(trainerToUpdate);
        return savedTr;
    }

    public void delete(Long id){this.trainerRepository.deleteById(id);}


}