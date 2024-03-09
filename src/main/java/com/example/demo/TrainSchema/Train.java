package com.example.demo.TrainSchema;

import jakarta.persistence.*;
@Entity
@Table
public class Train {
    @Id
    @SequenceGenerator(
            name = "Train_sequence",
            sequenceName = "TrainLinePlan_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Train_sequence"
    )

    private Long trainId;
    private Long trainCompanyId;
    private String trainName;
    private String trainModel;
    private String trainType;

    public Train() {
    }

    public Train(Long trainId, Long trainCompanyId, String trainName, String trainModel, String trainType) {
        this.trainId = trainId;
        this.trainCompanyId = trainCompanyId;
        this.trainName = trainName;
        this.trainModel = trainModel;
        this.trainType = trainType;
    }

    public Train(Long trainCompanyId, String trainName, String trainModel, String trainType) {
        this.trainCompanyId = trainCompanyId;
        this.trainName = trainName;
        this.trainModel = trainModel;
        this.trainType = trainType;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public Long getTrainCompanyId() {
        return trainCompanyId;
    }

    public void setTrainCompanyId(Long trainCompanyId) {
        this.trainCompanyId = trainCompanyId;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainModel() {
        return trainModel;
    }

    public void setTrainModel(String trainModel) {
        this.trainModel = trainModel;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }
}
