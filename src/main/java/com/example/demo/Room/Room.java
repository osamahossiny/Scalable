package com.example.demo.Room;

import jakarta.persistence.*;
import java.util.List;


@Entity
@Table
public class Room {


    @Id
    @SequenceGenerator(
            name = "Room_sequence",
            sequenceName = "Room_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Room_sequence"
    )
    private Long id;

    private Long hotelId;

    private double roomPrice;

    private int roomNumber;

    private String roomType;
    private double roomSpace;

    @ElementCollection
    @CollectionTable(name = "room_features", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "feature")
    private List<String> features;

    @ElementCollection
    @CollectionTable(name = "room_breakfast", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "breakfast")
    private List<String> breakfast;

    private double breakfastPrice;

    public Room() {
    }

    public Room(Long id, Long hotelId, double roomPrice, int roomNumber, String roomType, double roomSpace, List<String> features, List<String> breakfast, double breakfastPrice) {
        this.id = id;
        this.hotelId = hotelId;
        this.roomPrice = roomPrice;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomSpace = roomSpace;
        this.features = features;
        this.breakfast = breakfast;
        this.breakfastPrice = breakfastPrice;
    }

    public Room(Long hotelId, double roomPrice, int roomNumber, String roomType, double roomSpace, List<String> features, List<String> breakfast, double breakfastPrice) {
        this.hotelId = hotelId;
        this.roomPrice = roomPrice;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomSpace = roomSpace;
        this.features = features;
        this.breakfast = breakfast;
        this.breakfastPrice = breakfastPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getRoomSpace() {
        return roomSpace;
    }

    public void setRoomSpace(double roomSpace) {
        this.roomSpace = roomSpace;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public List<String> getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(List<String> breakfast) {
        this.breakfast = breakfast;
    }

    public double getBreakfastPrice() {
        return breakfastPrice;
    }

    public void setBreakfastPrice(double breakfastPrice) {
        this.breakfastPrice = breakfastPrice;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", hotelId=" + hotelId + + '\'' +
                ", roomPrice='" + roomPrice + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", roomType='" + roomType + '\'' +
                ", roomSpace='" + roomSpace + '\'' +
                ", features='" + features + '\'' +
                ", breakfast='" + breakfast + '\'' +
                ", breakfastPrice='" + breakfastPrice + '\'' +
                '}';
    }
}
