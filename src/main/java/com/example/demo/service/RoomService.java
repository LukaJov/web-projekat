package com.example.demo.service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.DTO.RoomDTO;
import com.example.demo.model.FitnessCenter;
import com.example.demo.model.Room;
import com.example.demo.model.Term;
import com.example.demo.repository.FitnessCenterRepository;
import com.example.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private FitnessCenterRepository fitnessCenterRepository;

    public Room save(Room room, FitnessCenter fitCenter) {
        room.setFitCenter(fitCenter);
        this.fitnessCenterRepository.save(fitCenter);
        return this.roomRepository.save(room);
    }

    public void delete(Long id, FitnessCenter fitCenter) {

        this.roomRepository.deleteById(id);
    }

    public List<Room> findByFitCenter(Long id) {
        return this.roomRepository.findByFitCenterId(id);
    }

    public Room update(RoomDTO roomDTO, Long id) {
        Optional<Room> optToUpdate = this.roomRepository.findById(id);
        Room toUpdate = optToUpdate.get();

        toUpdate.setCapacity(roomDTO.getCapacity());
        toUpdate.setLabel(roomDTO.getLabel());

        return this.roomRepository.save(toUpdate);

    }

    public Optional<Room> findById(Long id)
    {
        return this.roomRepository.findById(id);
    }
}