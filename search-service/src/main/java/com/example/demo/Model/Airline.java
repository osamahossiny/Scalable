package com.example.demo.Model;

import jakarta.persistence.*;


@Entity
@Table(
        name = "airline",
        uniqueConstraints = {
                @UniqueConstraint(name = "airline_name_unique", columnNames = "name")
        }
)
public class Airline {

    @Id
    @SequenceGenerator(
            name = "airline_sequence",
            sequenceName = "airline_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "airline_sequence"
    )
    private Long id;
    @Column(
            name = "name",
            nullable = false,
            updatable = true,
            columnDefinition = "TEXT"
    )
    private String name;
    @Column(
            name = "iban",
            nullable = false,
            updatable = true,
            columnDefinition = "TEXT"
    )
    private String IBAN;

    @Column(
            name = "customer_service_number",
            nullable = false,
            updatable = true,
            columnDefinition = "TEXT"
    )
    private String customerServiceNumber;

    public Airline() {
    }

    public Airline(Long id, String name, String IBAN, String customerServiceNumber) {
        this.id = id;
        this.name = name;
        this.IBAN = IBAN;
        this.customerServiceNumber = customerServiceNumber;
    }

    public Airline(String name, String IBAN, String customerServiceNumber) {
        this.name = name;
        this.IBAN = IBAN;
        this.customerServiceNumber = customerServiceNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getCustomerServiceNumber() {
        return customerServiceNumber;
    }

    public void setCustomerServiceNumber(String customerServiceNumber) {
        this.customerServiceNumber = customerServiceNumber;
    }



    @Override
    public String toString() {
        return "Airline{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", IBAN='" + IBAN + '\'' +
                ", customerServiceNumber='" + customerServiceNumber + '\'' +
                '}';
    }


}
