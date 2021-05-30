package com.example.demo.model.DTO;

import com.example.demo.model.FitnessCenter;
import lombok.*;
import java.util.Date;

@Getter
@Setter
public class FitnessCenterDTO {

    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String emailAddress;

    public FitnessCenterDTO(Long id, String name, String address, String phoneNumber, String emailAddress) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public FitnessCenterDTO()
    {

    }
}
