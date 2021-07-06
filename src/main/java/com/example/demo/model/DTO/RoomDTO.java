package com.example.demo.model.DTO;

import com.example.demo.model.Room;
import lombok.*;
import java.util.Date;

@Getter
@Setter
public class RoomDTO {

    private Long id;
    private int capacity;
    private String label;


    public RoomDTO(Long id, int capacity, String label) {
        this.id = id;
        this.capacity = capacity;
        this.label = label;
    }

    public RoomDTO(int capacity, String label) {
        this.capacity = capacity;
        this.label = label;
    }

    public RoomDTO()
    {

    }
}