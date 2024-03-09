package com.example.demo.TrainLinePlan;

import jakarta.persistence.*;

@Entity
@Table
public class TrainLinePlan {
    @Id
    @SequenceGenerator(
            name = "TrainLinePlan_sequence",
            sequenceName = "TrainLinePlan_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "TrainLinePlan_sequence"
    )
    private Long trainLinePlanId;
    private Long trainLineId;
    private String classType;
    private int price;
    private int insurancePrice;
    private int availableTickets;

    public TrainLinePlan() {
    }

    public TrainLinePlan(Long trainLinePlanId, Long trainLineId, String classType, int price, int insurancePrice, int availableTickets) {
        this.trainLinePlanId = trainLinePlanId;
        this.trainLineId = trainLineId;
        this.classType = classType;
        this.price = price;
        this.insurancePrice = insurancePrice;
        this.availableTickets = availableTickets;
    }

    public TrainLinePlan(Long trainLineId, String classType, int price, int insurancePrice, int availableTickets) {
        this.trainLineId = trainLineId;
        this.classType = classType;
        this.price = price;
        this.insurancePrice = insurancePrice;
        this.availableTickets = availableTickets;
    }

    public Long getTrainLinePlanId() {
        return trainLinePlanId;
    }

    public void setTrainLinePlanId(Long trainLinePlanId) {
        this.trainLinePlanId = trainLinePlanId;
    }

    public Long getTrainLineId() {
        return trainLineId;
    }

    public void setTrainLineId(Long trainLineId) {
        this.trainLineId = trainLineId;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getInsurancePrice() {
        return insurancePrice;
    }

    public void setInsurancePrice(int insurancePrice) {
        this.insurancePrice = insurancePrice;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }
}

