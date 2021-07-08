package com.example.demo.model;
import java.io.*;
import java.util.*;
import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class Grade implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double grd;

    @ManyToOne(fetch =FetchType.EAGER)
    private Term term;

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", grd=" + grd +
                ", term=" + term +
                ", givenBy=" + givenBy +
                '}';
    }

    @ManyToOne(fetch =FetchType.EAGER,cascade = CascadeType.ALL)
    private User givenBy;
}
