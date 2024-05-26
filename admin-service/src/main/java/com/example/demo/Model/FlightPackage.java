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
    public FlightPackage(Flight flight,  int weightCheckIn, int cancellationFee, int dateChangeFee, String mealInfo, boolean expressCheckIn, int price) {
        this.flight = flight;

        this.weightCheckIn = weightCheckIn;
        this.cancellationFee = cancellationFee;
        this.dateChangeFee = dateChangeFee;
        this.mealInfo = mealInfo;
        this.expressCheckIn = expressCheckIn;
        this.price = price;
    }

}
