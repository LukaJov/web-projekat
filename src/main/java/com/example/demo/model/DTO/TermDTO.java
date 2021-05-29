package com.example.demo.model.DTO;

import com.example.demo.model.Training;
import lombok.*;
import java.util.Date;

@Getter
@Setter
public class TermDTO {

    private Long id;
    private Training training;
    private Date date;
    private Double price;
}
