package com.example.demo.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import java.util.*;
import javax.persistence.*;


enum tType{Cardio, Bodybuilding, CrossFit}

@Entity
@Setter
@Getter
public class Training implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String desc;

    @Column
    @Enumerated(EnumType.STRING)
    private tType trainingType;

    @Column
    private Long duration;

    @OneToMany(mappedBy="training", fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    private Set<Term> terms = new HashSet<>();

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", trainingType=" + trainingType +
                ", duration=" + duration +
                ", terms=" + terms +
                '}';
    }
}
