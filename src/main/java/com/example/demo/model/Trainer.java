package com.example.demo.model;
import lombok.*;
import java.util.*;
import javax.persistence.*;
import java.io.*;


@Entity
@Setter
@Getter
public class Trainer implements Serializable
{
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

    @Enumerated(EnumType.STRING)
    @Column
    private userType userType;

    @Column
    private boolean active;

    @Column
    private Double avgGrade;

    @ManyToOne(fetch =FetchType.EAGER, cascade = CascadeType.ALL)
    private FitnessCenter fitCenter;

    @OneToMany(mappedBy = "trainer", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private Set<Term> terms = new HashSet<>();


}

