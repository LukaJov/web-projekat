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

    public TermDTO()
    {

    }

    public TermDTO(TrainingDTO trainingDTO, Date date, Double price)
    {
        this.trainingDTO = trainingDTO;
        this.date = date;
        this.price = price;
    }
}
