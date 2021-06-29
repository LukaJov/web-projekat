package com.example.demo.service;
import java.util.Date;
import java.util.List;

import com.example.demo.model.FitnessCenter;
import com.example.demo.model.Room;
import com.example.demo.model.Term;
import com.example.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room save(Room room) {
        return this.roomRepository.save(room);
    }
    public void delete(Long id){this.roomRepository.deleteById(id);}




}
