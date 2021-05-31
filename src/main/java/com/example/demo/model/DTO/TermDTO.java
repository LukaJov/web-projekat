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

    public TermDTO()
    {

    }

    public TermDTO(Long id, TrainingDTO trainingDTO, Date date, Double price)
    {
        this.id = id;
       /* this.trainingDTO.setName(trainingDTO.getName());
        this.trainingDTO.setDesc(trainingDTO.getDesc());
        this.trainingDTO.setTrainingType(trainingDTO.getTrainingType());
        this.trainingDTO.setDuration(trainingDTO.getDuration());*/
        this.trainingDTO = trainingDTO;
        this.date = date;
        this.price = price;
    }
}
