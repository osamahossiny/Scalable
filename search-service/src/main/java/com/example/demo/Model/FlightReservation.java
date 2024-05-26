package com.example.demo.Model;
import jakarta.persistence.*;

@Entity
@Table
public class FlightReservation {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAppUser() {
        return appUser;
    }

    public void setAppUser(User appUser) {
        this.appUser = appUser;
    }

    public FlightReservation() {
    }

    public FlightReservation(User appUser, FlightPackage flightPackage, PlaneSeat planeSeat, boolean seatChargeable, boolean extraBaggage, boolean withInsurance, int totalPrice) {
        this.appUser = appUser;
        this.flightPackage = flightPackage;
        this.planeSeat = planeSeat;
        this.seatChargeable = seatChargeable;
        this.extraBaggage = extraBaggage;
        this.withInsurance = withInsurance;
        this.totalPrice = totalPrice;
    }

    public FlightPackage getFlightPackage() {
        return flightPackage;
    }

    public void setFlightPackage(FlightPackage flightPackage) {
        this.flightPackage = flightPackage;
    }

    public PlaneSeat getPlaneSeat() {
        return planeSeat;
    }

    public void setPlaneSeat(PlaneSeat planeSeat) {
        this.planeSeat = planeSeat;
    }

    public boolean isSeatChargeable() {
        return seatChargeable;
    }

    public void setSeatChargeable(boolean seatChargeable) {
        this.seatChargeable = seatChargeable;
    }

    public boolean isExtraBaggage() {
        return extraBaggage;
    }

    public void setExtraBaggage(boolean extraBaggage) {
        this.extraBaggage = extraBaggage;
    }

    public boolean isWithInsurance() {
        return withInsurance;
    }

    public void setWithInsurance(boolean withInsurance) {
        this.withInsurance = withInsurance;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

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
    private User appUser;
    @ManyToOne
    @JoinColumn(
            name = "package_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "package_id_fk"
            )
    )
    private FlightPackage flightPackage;
    @ManyToOne
    @JoinColumn(
            name = "plane_seat_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "plane_seat_id_fk"
            )
    )
    private PlaneSeat planeSeat;
    @Column
    private boolean seatChargeable;
    @Column
    private boolean extraBaggage;
    @Column
    private boolean withInsurance;
    @Column
    private int totalPrice;
}
