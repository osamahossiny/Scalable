package com.example.demo.TrainCompany;

import jakarta.persistence.*;

@Entity
@Table
public class TrainCompany {
    @Id
    @SequenceGenerator(
            name = "train_company_sequence",
            sequenceName = "train_company_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "train_company_sequence"
    )
    private Long trainCompanyId;
    private String companyName;
    private String companyIBAN;
    private String customerServiceNumber;

    public TrainCompany() {
    }

    public TrainCompany(Long trainCompanyId, String companyName, String companyIBAN, String customerServiceNumber) {
        this.trainCompanyId = trainCompanyId;
        this.companyName = companyName;
        this.companyIBAN = companyIBAN;
        this.customerServiceNumber = customerServiceNumber;
    }

    public TrainCompany(String companyName, String companyIBAN, String customerServiceNumber) {
        this.companyName = companyName;
        this.companyIBAN = companyIBAN;
        this.customerServiceNumber = customerServiceNumber;
    }

    public Long getTrainCompanyId() {
        return trainCompanyId;
    }

    public void setTrainCompanyId(Long trainCompanyId) {
        this.trainCompanyId = trainCompanyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyIBAN() {
        return companyIBAN;
    }

    public void setCompanyIBAN(String companyIBAN) {
        this.companyIBAN = companyIBAN;
    }

    public String getCustomerServiceNumber() {
        return customerServiceNumber;
    }

    public void setCustomerServiceNumber(String customerServiceNumber) {
        this.customerServiceNumber = customerServiceNumber;
    }
}
