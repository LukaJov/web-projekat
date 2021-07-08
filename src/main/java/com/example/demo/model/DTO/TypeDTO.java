package com.example.demo.model.DTO;

import lombok.*;
import java.util.Date;

@Getter
@Setter
public class TypeDTO
{
    private Long id;
    private Long userType;

    public TypeDTO(){}
    public TypeDTO(Long id, Long userType){
        this.id = id;
        this.userType = userType;
    }
}