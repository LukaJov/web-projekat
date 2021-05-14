package com.example.demo.model;
import lombok.*;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

enum userType{Member, Trainer, Admin}
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
    @Enumerated(EnumType.STRING)
    private userType userType;

    @Column
    private boolean active;

    @OneToMany
    private Set<Term> toDo = new HashSet<>();

    @OneToMany
    private Set<Term> done = new HashSet<>();

   //dodaj listu ocena
    @OneToMany(mappedBy = "givenBy", fetch =FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Grade> grades = new HashSet<>();
}
