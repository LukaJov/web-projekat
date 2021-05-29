package com.example.demo.model.DTO;



import lombok.*;
import java.util.Date;

@Setter
@Getter
public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String phoneNumber;
    private String emailAddress;
    private Date birthday;
    private String userType;

}
