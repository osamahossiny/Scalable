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



    @ManyToOne(
//                cascade = CascadeType.ALL,
                fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "flight_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "flight_id_fk"
            )
    )

    private Flight flight;


    public FlightPackage(Long id, Flight flight, int weightCabin, int weightCheckIn, int cancellationFee, int dateChangeFee, String mealInfo, boolean expressCheckIn, int price) {
        this.id = id;
        this.flight = flight;
        this.weightCabin = weightCabin;
        this.weightCheckIn = weightCheckIn;
        this.cancellationFee = cancellationFee;
        this.dateChangeFee = dateChangeFee;
        this.mealInfo = mealInfo;
        this.expressCheckIn = expressCheckIn;
        this.price = price;
    }

    public FlightPackage(Flight flight, int weightCabin, int weightCheckIn, int cancellationFee, int dateChangeFee, String mealInfo, boolean expressCheckIn, int price) {
        this.flight = flight;
        this.weightCabin = weightCabin;
        this.weightCheckIn = weightCheckIn;
        this.cancellationFee = cancellationFee;
        this.dateChangeFee = dateChangeFee;
        this.mealInfo = mealInfo;
        this.expressCheckIn = expressCheckIn;
        this.price = price;
    }

    public FlightPackage() {

    }

    public Long getId() {
        return id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setWeightCabin(int weightCabin) {
        this.weightCabin = weightCabin;
    }

    public void setWeightCheckIn(int weightCheckIn) {
        this.weightCheckIn = weightCheckIn;
    }

    public void setCancellationFee(int cancellationFee) {
        this.cancellationFee = cancellationFee;
    }

    public void setDateChangeFee(int dateChangeFee) {
        this.dateChangeFee = dateChangeFee;
    }

    public void setMealInfo(String mealInfo) {
        this.mealInfo = mealInfo;
    }

    public void setExpressCheckIn(boolean expressCheckIn) {
        this.expressCheckIn = expressCheckIn;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeightCabin() {
        return weightCabin;
    }

    public int getWeightCheckIn() {
        return weightCheckIn;
    }

    public int getCancellationFee() {
        return cancellationFee;
    }

    public int getDateChangeFee() {
        return dateChangeFee;
    }

    public String getMealInfo() {
        return mealInfo;
    }

    public boolean isExpressCheckIn() {
        return expressCheckIn;
    }

    public int getPrice() {
        return price;
    }

    @Column
    private int weightCabin;
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

    @Override
    public String toString() {
        return "FlightPackage{" +
                "id=" + id +
                ", flight=" + flight +
                ", weightCabin=" + weightCabin +
                ", weightCheckIn=" + weightCheckIn +
                ", cancellationFee=" + cancellationFee +
                ", dateChangeFee=" + dateChangeFee +
                ", mealInfo='" + mealInfo + '\'' +
                ", expressCheckIn=" + expressCheckIn +
                ", price=" + price +
                '}';
    }
}
