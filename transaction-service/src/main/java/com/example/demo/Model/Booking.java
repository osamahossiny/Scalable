package com.example.demo.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @SequenceGenerator(
            name = "booking_id_sequence",
            sequenceName = "booking_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "booking_id_sequence"
    )
    private Long id;


    private String bookingDate;
    private String bookingTime;
    private String bookingStatus;
    private Float bookingPrice;
    private String bookingPaymentMethod;
    private String bookingPaymentStatus;
    private String bookingPaymentDate;



    public Booking(Long id, String bookingDate, String bookingTime, String bookingStatus, Float bookingPrice, String bookingPaymentMethod, String bookingPaymentStatus, String bookingPaymentDate) {
        this.id = id;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.bookingStatus = bookingStatus;
        this.bookingPrice = bookingPrice;
        this.bookingPaymentMethod = bookingPaymentMethod;
        this.bookingPaymentStatus = bookingPaymentStatus;
        this.bookingPaymentDate = bookingPaymentDate;
    }

    public Booking() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Float getBookingPrice() {
        return bookingPrice;
    }

    public void setBookingPrice(Float bookingPrice) {
        this.bookingPrice = bookingPrice;
    }

    public String getBookingPaymentMethod() {
        return bookingPaymentMethod;
    }

    public void setBookingPaymentMethod(String bookingPaymentMethod) {
        this.bookingPaymentMethod = bookingPaymentMethod;
    }

    public String getBookingPaymentStatus() {
        return bookingPaymentStatus;
    }

    public void setBookingPaymentStatus(String bookingPaymentStatus) {
        this.bookingPaymentStatus = bookingPaymentStatus;
    }

    public String getBookingPaymentDate() {
        return bookingPaymentDate;
    }

    public void setBookingPaymentDate(String bookingPaymentDate) {
        this.bookingPaymentDate = bookingPaymentDate;
    }
}
