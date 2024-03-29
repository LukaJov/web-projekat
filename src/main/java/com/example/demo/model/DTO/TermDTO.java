package com.example.demo.model.DTO;

import com.example.demo.model.Training;
import lombok.*;
import java.util.Date;

@Getter
@Setter
public class TermDTO {
    private Long id;
    private TrainingDTO trainingDTO;
    private Date date;
    private Double price;
    private Double grade;
    private int emptySpots;

    public TermDTO()
    {

    }

    public TermDTO(Long id, TrainingDTO trainingDTO, Date date, Double price)
    {
        this.id = id;
        this.trainingDTO = trainingDTO;
        this.date = date;
        this.price = price;
    }

    public TermDTO(Date date, Double price)
    {
        this.date = date;
        this.price = price;
    }

    public TermDTO(Long id, TrainingDTO trainingDTO, Date date, Double price, Double grade)
    {
        this.id = id;
        this.trainingDTO = trainingDTO;
        this.date = date;
        this.price = price;
        this.grade = grade;
    }
    public TermDTO(Long id, TrainingDTO trainingDTO, Date date, Double price, int emptySpots)
    {
        this.id = id;
        this.trainingDTO = trainingDTO;
        this.date = date;
        this.price = price;
        this.emptySpots = emptySpots;
    }
}