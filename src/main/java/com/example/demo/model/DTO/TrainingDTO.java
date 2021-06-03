package com.example.demo.model.DTO;

import lombok.*;
import javax.persistence.Column;

@Getter
@Setter
public class TrainingDTO {
    private String name;
    private String desc;
    private String trainingType;
    private Long duration;

    public TrainingDTO()
    {

    }

    public TrainingDTO(String name, String desc, String trainingType, Long duration)
    {
        this.name = name;
        this.desc = desc;
        this.trainingType = trainingType;
        this.duration = duration;
    }

}
