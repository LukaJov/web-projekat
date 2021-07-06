package com.example.demo.model.DTO;

import com.example.demo.model.Training;
import lombok.*;
import java.util.Date;

@Getter
@Setter
public class TermDTO {

    private TrainingDTO trainingDTO;
    private Date date;
    private Double price;
    private int numberOfUsers;
    private RoomDTO roomDTO;


    public TermDTO()
    {

    }

    public TermDTO(TrainingDTO trainingDTO, Date date, Double price, int numberOfUsers,RoomDTO roomDTO)
    {
        this.numberOfUsers = numberOfUsers;
        this.trainingDTO = trainingDTO;
        this.date = date;
        this.price = price;
    }
}
