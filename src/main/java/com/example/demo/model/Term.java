package com.example.demo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Column
    //@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date date;

    @Column
    private Double price;

    @Column
    private int numberOfUsers;

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private FitnessCenter fitCenter;

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private Room room;

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private Training training;

    @ManyToMany
    @JoinTable(name = "to_do",
            joinColumns = @JoinColumn(name = "term_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> userToDo = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "done",
            joinColumns = @JoinColumn(name = "term_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> userDone = new HashSet<>();

    @OneToMany(mappedBy = "term", fetch =FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Grade> grades = new HashSet<>();

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private Trainer trainer;

    @Override
    public String toString() {
        return "Term{" +
                "id=" + id +
                ", date=" + date +
                ", price=" + price +
                ", numberOfUsers=" + numberOfUsers +
                ", fitCenter=" + fitCenter +
                ", room=" + room +
                ", training=" + training +
                ", userToDo=" + userToDo +
                ", userDone=" + userDone +
                ", grades=" + grades +
                ", trainer=" + trainer +
                '}';
    }

}