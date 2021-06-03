package com.example.demo.model;
import lombok.*;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;


@Entity
@Setter
@Getter
public class User implements Serializable
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
    private String userType;

    @Column
    private boolean active;

    @OneToMany
    private Set<Term> toDo = new HashSet<>();

    @OneToMany
    private Set<Term> done = new HashSet<>();

   //dodaj listu ocena
    @OneToMany(mappedBy = "givenBy", fetch =FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Grade> grades = new HashSet<>();

    public User(String username, String password, String name, String surname, String phoneNumber,
                   String emailAddress, Date birthday)
    {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.birthday = birthday;
        this.userType = "Member";
        this.active = false;

    }

    public User()
    {}


    @Override
    public String toString() {
        return "User{" +
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
                ", toDo=" + toDo +
                ", done=" + done +
                ", grades=" + grades +
                '}';
    }
}
