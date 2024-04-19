package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table
public class Flight {
    @Id
    @SequenceGenerator(
            name = "flight_id_sequence",
            sequenceName = "flight_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "flight_id_sequence"
    )
    Long id;
}
