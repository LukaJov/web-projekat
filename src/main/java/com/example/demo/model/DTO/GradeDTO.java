package com.example.demo.model.DTO;


import com.example.demo.model.Grade;
import lombok.*;
import java.util.Date;

@Getter
@Setter
public class GradeDTO {
    Long id;
    Double grade;

    public GradeDTO()
    {}

    public GradeDTO(Long id, Double grade)
    {
        this.id = id;
        this.grade = grade;
    }
}
