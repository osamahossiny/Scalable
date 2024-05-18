package com.example.demo.model;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import lombok.Data;
import java.util.UUID;

@Data
@Table
public class Complaints {
    @PrimaryKey
    private UUID id;
    private String UserID;
    private String description;

    public Complaints(String UserID, String description) {
        id=UUID.randomUUID();
        this.UserID = UserID;
        this.description = description;
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
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}