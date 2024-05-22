package com.example.demo.Model;

import jakarta.persistence.*;

public class PlaneSeat {
    @Id
    @SequenceGenerator(
            name = "plane_seat_id_sequence",
            sequenceName = "plane_seat_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "plane_seat_id_sequence"
    )
    private Long id;
    @Column
    private int seatNumber;
    @Enumerated(EnumType.STRING)
    private SeatCategory seatCategory;
    @ManyToOne
    @JoinColumn(
            name = "plane_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "plane_id_fk"
            )
    )
    private Plane plane;
    @Column
    private int price;
}
