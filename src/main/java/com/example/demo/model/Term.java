package com.example.demo.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import java.util.*;
import javax.persistence.*;


@Entity
@Setter
@Getter
public class Term implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public String toString() {
        return "Term{" +
                "id=" + id +
                ", date=" + date +
                ", price=" + price +
                ", numberOfUsers=" + numberOfUsers +
                ", fitCenter=" + fitCenter +
                ", rooms=" + rooms +
                ", training=" + training +
                ", userToDo=" + userToDo +
                ", userDone=" + userDone +
                ", grades=" + grades +
                ", trainer=" + trainer +
                '}';
    }

    @Column
    private Date date;

    @Column
    private Double price;

    @Column
    private int numberOfUsers;

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private FitnessCenter fitCenter;

    @ManyToMany(mappedBy = "terms")
    private Set <Room> rooms = new HashSet<>();

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private Training training;

    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private User userToDo;

    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private User userDone;

    @OneToMany(mappedBy = "term", fetch =FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Grade> grades = new HashSet<>();

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private Trainer trainer;
}