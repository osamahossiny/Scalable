package com.example.demo.Model;

import jakarta.persistence.*;

@Entity
@Table

public class FlightReservation {
    @Id
    @SequenceGenerator(
            name = "flight_reservation_id_sequence",
            sequenceName = "flight_reservation_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "flight_reservation_id_sequence"
    )
    Long id;
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "user_id_fk"
            )
    )
    private AppUser appUser;
    @ManyToOne
    @JoinColumn(
            name = "package_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "package_id_fk"
            )
    )
    private FlightPackage Package;
 /*   @ManyToOne
    @JoinColumn(
            name = "plane_seat_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "plane_seat_id_fk"
            )
    )
    private PlaneSeat planeSeat;*/
    @Column
    private boolean seatChargeable;
    @Column
    private boolean extraBaggage;
    @Column
    private boolean withInsurance;
    @Column
    private int totalPrice;
    public FlightReservation(AppUser appUser, FlightPackage flightPackage, boolean seatChargeable, boolean extraBaggage, boolean withInsurance, int totalPrice) {
        this.appUser = appUser;
        this.Package = flightPackage;
        this.seatChargeable = seatChargeable;
        this.extraBaggage = extraBaggage;
        this.withInsurance = withInsurance;
        this.totalPrice = totalPrice;
    }
}
