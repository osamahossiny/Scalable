package com.example.demo.VillaRating;

public class VillaRating {

    private Long UserId;
    private Long VillaId;
    private Double Rating;
    private String Review;

    public VillaRating(){

    }
    public VillaRating(Long userId, Long villaId, Double rating, String review) {
        UserId = userId;
        VillaId = villaId;
        Rating = rating;
        Review = review;
    }

    public VillaRating(Double rating, String review) {
        Rating = rating;
        Review = review;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public Long getVillaId() {
        return VillaId;
    }

    public void setVillaId(Long villaId) {
        VillaId = villaId;
    }

    public Double getRating() {
        return Rating;
    }

    public void setRating(Double rating) {
        Rating = rating;
    }

    public String getReview() {
        return Review;
    }

    public void setReview(String review) {
        Review = review;
    }

    @Override
    public String toString() {
        return "VillaRating{" +
                "UserId=" + UserId +
                ", VillaId=" + VillaId +
                ", Rating=" + Rating +
                ", Review='" + Review + '\'' +
                '}';
    }
}
