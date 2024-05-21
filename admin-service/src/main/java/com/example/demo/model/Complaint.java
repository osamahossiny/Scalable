package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(
        name = "complaint",
        uniqueConstraints = {
                @UniqueConstraint(name = "complaint_unique", columnNames = "id")
        }
)
public class Complaint {

    @Id
    @SequenceGenerator(
            name = "complaint_sequence",
            sequenceName = "complaint_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "complaint_sequence"
    )
    private Long id;

    @Column(
            name = "user_id",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private Long userId;

    @Column(
            name = "description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String description;

    @Column(
            name = "status",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String status;

    public Complaint() {
    }

    public Complaint(Long id, Long userId, String description, String status) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.status = status;
    }

    public Complaint(Long userId, String description, String status) {
        this.userId = userId;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "id=" + id +
                ", userId=" + userId +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
