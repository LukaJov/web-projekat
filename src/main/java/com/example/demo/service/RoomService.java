package com.example.demo.service;
import java.util.Date;
import java.util.List;

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

    public Room save(Room room, FitnessCenter fitCenter)
    {
        room.setFitCenter(fitCenter);
        fitCenter.getRooms().add(room);
        this.fitnessCenterRepository.save(fitCenter);
        return this.roomRepository.save(room);
    }
    public void delete(Long id, FitnessCenter fitCenter)
    {
        for(Room room:fitCenter.getRooms())
        {
            if(room.getId()==id)
            {
                fitCenter.getRooms().remove(room);
            }
        }
        this.fitnessCenterRepository.save(fitCenter);
        this.roomRepository.deleteById(id);}
}
