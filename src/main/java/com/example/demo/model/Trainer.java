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

    @Column
    private String userType;

    @Column
    private boolean active;

    @Column
    private Double avgGrade;

    @ManyToOne(fetch =FetchType.EAGER, cascade = CascadeType.ALL)
    private FitnessCenter fitCenter;

    @OneToMany(mappedBy = "trainer", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private Set<Term> terms = new HashSet<>();

    @Override
    public String toString() {
        return "Trainer{" +
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
                ", avgGrade=" + avgGrade +
                ", fitCenter=" + fitCenter +
                ", terms=" + terms +
                '}';
    }


}

