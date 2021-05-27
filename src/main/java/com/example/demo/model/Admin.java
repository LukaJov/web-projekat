package com.example.demo.model;
import lombok.*;
import java.util.*;
import javax.persistence.*;
import java.io.*;


@Entity
@Setter
@Getter
public class Admin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String phoneNumber;

    @Column
    private String emailAddress;

    @Column
    private Date birthday;

    @Column
    @Enumerated(EnumType.STRING)
    private userType userType;

    @Column
    private boolean active;

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", birthday=" + birthday +
                ", userType=" + userType +
                ", active=" + active +
                '}';
    }
}