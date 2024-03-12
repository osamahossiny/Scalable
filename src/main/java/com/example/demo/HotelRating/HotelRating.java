package com.example.demo.HotelRating;

import jakarta.persistence.*;

@Entity
@Table
public class HotelRating {

    @Id
    @SequenceGenerator(
            name = "HotelRating_sequence",
            sequenceName = "HotelRating_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "HotelRating_sequence"
    )
    private Long id;
    private Long userId;
    private Long hotelId;
    private double rating;
    private String review;


    public HotelRating() {
    }


    public HotelRating(Long id, Long userId, Long hotelId, double rating, String review) {
        this.id = id;
        this.userId = userId;
        this.hotelId = hotelId;
        this.rating = rating;
        this.review = review;
    }

    public HotelRating(Long userId, Long hotelId, double rating, String review) {
        this.userId = userId;
        this.hotelId = hotelId;
        this.rating = rating;
        this.review = review;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "HotelRating{" +
                "id=" + id +
                "hotelId=" + hotelId + '\'' +
                ", userID='" + userId + '\'' +
                ", rating='" + rating + '\'' +
                ", review='" + review + '\'' +
                '}';
    }
}
