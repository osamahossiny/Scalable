package com.example.demo.Model;
import jakarta.persistence.*;

@Entity
@Table
public class FlightReservation {

    //should be changed in the future to be dynamic based on the airline
    @Transient
    int seatChargeablePrice = 50;
    @Transient
    int extraBaggagePrice = 30;
    @Transient
    int withInsurancePrice = 20;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public FlightReservation() {
    }

    public FlightReservation(Long userId, FlightPackage flightPackage, PlaneSeat planeSeat, boolean seatChargeable, boolean extraBaggage, boolean withInsurance, PaymentMethod paymentMethod) {
        this.userId = userId;
        this.flightPackage = flightPackage;
        this.planeSeat = planeSeat;
        this.seatChargeable = seatChargeable;
        this.extraBaggage = extraBaggage;
        this.withInsurance = withInsurance;
        this.totalPrice = planeSeat.getPrice() +
                flightPackage.getPrice() +
                (seatChargeable ? seatChargeablePrice : 0) +
                (extraBaggage ? extraBaggagePrice : 0) +
                (withInsurance ? withInsurancePrice : 0);
        this.paymentMethod = paymentMethod;
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

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getSeatChargeablePrice() {
        return seatChargeablePrice;
    }

    public int getExtraBaggagePrice() {
        return extraBaggagePrice;
    }

    public int getWithInsurancePrice() {
        return withInsurancePrice;
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
//    @ManyToOne
//    @JoinColumn(
//            name = "user_id",
//            referencedColumnName = "id",
//            foreignKey = @ForeignKey(
//                    name = "user_id_fk"
//            )
//    )
    private Long userId;
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
    private float totalPrice;


    public enum PaymentMethod {
        PAYPAL,
        CASH,
        VISA
    }

    @Column
    private PaymentMethod paymentMethod;
}
