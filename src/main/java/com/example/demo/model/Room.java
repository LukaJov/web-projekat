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


    @ManyToOne(fetch =FetchType.EAGER)
    private FitnessCenter fitCenter;

    //@ManyToMany
    //@JoinTable(name="Schedule",
          //  joinColumns = @JoinColumn(name="room_id", referencedColumnName = "id"),
           // inverseJoinColumns = @JoinColumn(name ="term_id", referencedColumnName = "id")
   // )
    @OneToMany(mappedBy = "room", fetch =FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Term> terms = new HashSet<>();

    public Room(int capacity, String label) {
        this.capacity = capacity;
        this.label = label;
    }

    public Room()
    {

    }
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

}
