package com.example.demo.repository;

import com.example.demo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Repository
public interface RoomRepository extends  JpaRepository<Room, Long> {
    public List<Room> findByFitCenterId(Long id);
}