package com.example.demo.Model;

import jakarta.persistence.*;

@Entity
@Table(
        name = "promotion",
        uniqueConstraints = {
                @UniqueConstraint(name = "promotion_code_unique", columnNames = "code")
        }
)
public class Promotion {

    @Id
    @SequenceGenerator(
            name = "promotion_sequence",
            sequenceName = "promotion_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "promotion_sequence"
    )
    private Long id;

    @Column(name = "code", nullable = false, columnDefinition = "TEXT")
    private String code;

    @Column(name = "discount", nullable = false, columnDefinition = "DECIMAL")
    private Double discount;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    public Promotion() {
    }

    public Promotion(Long id, String code, Double discount, Flight flight) {
        this.id = id;
        this.code = code;
        this.discount = discount;
        this.flight = flight;
    }

    public Promotion(String code, Double discount, Flight flight) {
        this.code = code;
        this.discount = discount;
        this.flight = flight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", discount=" + discount +
                ", flight=" + flight +
                '}';
    }
}
