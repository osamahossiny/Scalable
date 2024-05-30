package com.example.demo.Model;

import jakarta.persistence.*;

@Entity
@Table(
        name = "refund",
        uniqueConstraints = {
                @UniqueConstraint(name = "refund_unique", columnNames = "id")
        }
)
public class Refund {

    @Id
    @SequenceGenerator(
            name = "refund_sequence",
            sequenceName = "refund_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "refund_sequence"
    )
    private Long id;

    @Column(
            name = "user_id",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private Long userId;

    @Column(
            name = "amount",
            nullable = false,
            columnDefinition = "DECIMAL"
    )
    private Double amount;

    @Column(
            name = "status",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String status;

    public Refund() {
    }

    public Refund(Long id, Long userId, Double amount, String status) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.status = status;
    }

    public Refund(Long userId, Double amount, String status) {
        this.userId = userId;
        this.amount = amount;
        this.status = status;
    }

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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Refund{" +
                "id=" + id +
                ", userId=" + userId +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }
}
