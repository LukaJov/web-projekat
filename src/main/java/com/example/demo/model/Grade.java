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

    @ManyToOne(fetch =FetchType.EAGER, cascade = CascadeType.ALL)
    private Term term;

    @ManyToOne(fetch =FetchType.EAGER, cascade = CascadeType.ALL)
    private User givenBy;
}
