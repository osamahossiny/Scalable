package com.example.demo.Model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table
public class Complaints {
    @PrimaryKey
    private UUID id;
    private String description;
    private String status;
    public Complaints(UUID id, String description, String status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }
    // Getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
}
