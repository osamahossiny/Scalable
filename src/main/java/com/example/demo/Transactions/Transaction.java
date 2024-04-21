package com.example.demo.Transactions;

import com.example.demo.Plane.Plane;
import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;


@Entity
@Table(
        name = "transaction",
        uniqueConstraints = {
                @UniqueConstraint(name = "Transaction_id_unique", columnNames = "id")
        }
)
public class Transaction {
    

        @Id
        @SequenceGenerator(
                name = "transaction_sequence",
                sequenceName = "transaction_sequence",
                allocationSize = 1
        )
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "transaction_sequence"
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

        @OneToMany(
                mappedBy = "transaction",
                //orphanRemoval = true,
                cascade = {CascadeType.ALL},
                fetch = FetchType.LAZY
        )
        private List<Plane> planes;
        @Column(
                name = "customer_service_number",
                nullable = false,
                updatable = true,
                columnDefinition = "TEXT"
        )
        private String customerServiceNumber;

        public transaction() {
        }

        public transaction(Long id, String name, String IBAN, String customerServiceNumber) {
            this.id = id;
            this.name = name;
            this.IBAN = IBAN;
            this.customerServiceNumber = customerServiceNumber;
        }

        public transaction(String name, String IBAN, String customerServiceNumber) {
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

        public void addPlane(Plane plane) {
            if (!this.planes.contains(plane)) {
                this.planes.add(plane);
                plane.settransaction(this);
            }
        }

        public void removePlane(Plane plane) {
            if (!this.planes.contains(plane)) {
                this.planes.remove(plane);
                plane.settransaction(null);
            }
        }

        @Override
        public String toString() {
            return "transaction{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", IBAN='" + IBAN + '\'' +
                    ", customerServiceNumber='" + customerServiceNumber + '\'' +
                    '}';
        }

    }
