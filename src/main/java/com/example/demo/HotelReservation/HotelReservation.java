package com.example.demo.HotelReservation;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class HotelReservation {


    @Id
    @SequenceGenerator(
            name = "HotelReservation_sequence",
            sequenceName = "HotelReservation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "HotelReservation_sequence"
    )
    private Long id;

    private Long userId;
    private Long hotelId;

    private Long roomID;

    private double price;

    private boolean withFood;

    private int numberOfPersons;

    private LocalDate checkIn;

    private LocalDate checkOut;

    private boolean roomOnly;


    public HotelReservation() {
    }

    public HotelReservation(Long id, Long userId, Long hotelId, Long roomID, double price, boolean withFood, int numberOfPersons, LocalDate checkIn, LocalDate checkOut, boolean roomOnly) {
        this.id = id;
        this.userId = userId;
        this.hotelId = hotelId;
        this.roomID = roomID;
        this.price = price;
        this.withFood = withFood;
        this.numberOfPersons = numberOfPersons;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.roomOnly = roomOnly;
    }

    public HotelReservation(Long userId, Long hotelId, Long roomID, double price, boolean withFood, int numberOfPersons, LocalDate checkIn, LocalDate checkOut, boolean roomOnly) {
        this.userId = userId;
        this.hotelId = hotelId;
        this.roomID = roomID;
        this.price = price;
        this.withFood = withFood;
        this.numberOfPersons = numberOfPersons;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.roomOnly = roomOnly;
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

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getRoomID() {
        return roomID;
    }

    public void setRoomID(Long roomID) {
        this.roomID = roomID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isWithFood() {
        return withFood;
    }

    public void setWithFood(boolean withFood) {
        this.withFood = withFood;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public boolean isRoomOnly() {
        return roomOnly;
    }

    public void setRoomOnly(boolean roomOnly) {
        this.roomOnly = roomOnly;
    }

    @Override
    public String toString() {
        return "HotelReservation{" +
                "id=" + id +
                "hotelId=" + hotelId + '\'' +
                ", userID='" + userId + '\'' +
                ", roomID='" + roomID + '\'' +
                ", price='" + price + '\'' +
                ", withFood='" + withFood + '\'' +
                ", numberOfPersons='" + numberOfPersons + '\'' +
                ", from='" + checkIn + '\'' +
                ", to='" + checkOut + '\'' +
                ", roomOnly='" + roomOnly + '\'' +
                '}';
    }
}
