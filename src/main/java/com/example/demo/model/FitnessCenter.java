package com.example.demo.model;

import lombok.*;
import java.io.Serializable;
import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
public class FitnessCenter implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String phoneNumber;

    @Column
    private String emailAddress;

    @OneToMany(mappedBy ="fitCenter",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private Set<Trainer> trainers = new HashSet<>();

    @OneToMany(mappedBy = "fitCenter",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private Set<Room> rooms = new HashSet<>();

    @OneToMany(mappedBy = "fitCenter",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private Set<Term> terms = new HashSet<>();

    public FitnessCenter(String name, String address, String phoneNumber, String emailAddress) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public FitnessCenter()
    {

    }

    @Override
    public String toString() {
        return "FitnessCenter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", trainers=" + trainers +
                ", rooms=" + rooms +
                ", terms=" + terms +
                '}';
    }

}
