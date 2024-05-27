package com.example.demo.Model;
import jakarta.persistence.*;

@Entity
@Table(
        name = "plane",
        uniqueConstraints = {
                @UniqueConstraint(name = "plane_name_unique", columnNames = "name")
        }
)
public class Plane {
    @Id
    @SequenceGenerator(
            name = "plane_id_sequence",
            sequenceName = "plane_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "plane_id_sequence"
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            updatable = true,
            columnDefinition = "TEXT"
    )
    private String name;

    @ManyToOne


    private Airline airline;



    @Column(
            name = "type",
            nullable = false,
            updatable = true,
            columnDefinition = "TEXT"
    )
    private String type;

    public Plane(String name, Airline airline, String type) {
        this.name = name;
        this.airline = airline;
        this.type = type;
    }

    public Plane(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Plane() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", airline=" + airline +
                ", type='" + type + '\'' +
                '}';
    }
}
