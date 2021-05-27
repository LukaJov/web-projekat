package com.example.demo.model;

import java.util.*;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int capacity;

    @Column
    private String label;

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", label='" + label + '\'' +
                ", fitCenter=" + fitCenter +
                ", terms=" + terms +
                '}';
    }

    @ManyToOne(fetch =FetchType.EAGER, cascade = CascadeType.ALL)
    private FitnessCenter fitCenter;

    @ManyToMany
    @JoinTable(name="Schedule",
            joinColumns = @JoinColumn(name="room_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name ="term_id", referencedColumnName = "id")
    )
    private Set<Term> terms = new HashSet<>();
}
