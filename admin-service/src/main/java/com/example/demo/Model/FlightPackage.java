package com.example.demo.Model;

import jakarta.persistence.*;

@Entity
@Table
public class FlightPackage {
    @Id
    @SequenceGenerator(
            name = "flight_package_id_sequence",
            sequenceName = "flight_package_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "flight_package_id_sequence"
    )
    private Long id;
    @ManyToOne
    private Flight flight;
    @Column
    private int weightCapin;
    @Column
    private int weightCheckIn;
    @Column
    private int cancellationFee;
    @Column
    private int dateChangeFee;
    @Column
    private String mealInfo;
    @Column
    private boolean expressCheckIn;
    @Column
    private int price;


}
