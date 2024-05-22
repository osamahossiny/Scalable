package com.example.demo.Model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import lombok.Data;
import java.util.UUID;

@Data
@Table
public class Complaints {
    @PrimaryKey
    private UUID id;
    private String userID;
    private String description;
    private String status;

    public Complaints(String userID, String description) {
        this.id = UUID.randomUUID();
        this.userID = userID;
        this.description = description;
        this.status = "Pending"; // Default status
    }

    public Complaints() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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
