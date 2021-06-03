package com.example.demo.model.DTO;



import lombok.*;
import java.util.Date;

@Setter
@Getter
public class UserDTO {

    private String username;
    private String password;
    private String name;
    private String surname;
    private String phoneNumber;
    private String emailAddress;
    private Date birthday;
    private String userType;

    public UserDTO()
    {

    }

    public UserDTO(String username, String password, String name, String surname,
               String phoneNumber, String emailAddress, Date birthday, String userType)
    {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.birthday = birthday;
        this.userType = userType;
    }

    public UserDTO(String name, String surname,
                      String phoneNumber, String emailAddress, Date birthday, String userType)
    {

        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.birthday = birthday;
        this.userType = userType;
    }
}
