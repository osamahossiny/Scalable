package com.example.demo.HotelRating;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class HotelRatingService {

    private  final HotelRatingRepository hotelRatingRepository;

    @Autowired
    public HotelRatingService(HotelRatingRepository hotelRatingRepository) {
        this.hotelRatingRepository = hotelRatingRepository;
    }

    public List<HotelRating> getHotelRatings(){
        return hotelRatingRepository.findAll();
    }

    public void addNewHotelRating(HotelRating hotelRating) {
        Optional<HotelRating> hotelRatingByHotelId = hotelRatingRepository.findHotelRatingById(hotelRating.getHotelId());

        if (hotelRatingByHotelId.isPresent()){
            throw new IllegalStateException("A rating for this hotel already exists.");
        }
        hotelRatingRepository.save(hotelRating);
    }

    public void deleteHotelRating(Long hotelRatingId) {
        boolean exists = hotelRatingRepository.existsById(hotelRatingId);

        if (!exists) {
            throw new IllegalStateException("Hotel Rating with id "+ hotelRatingId + " does not exist.");
        }
        hotelRatingRepository.deleteById(hotelRatingId);
    }

    @Transactional
    public void updateHotelRating(Long hotelRatingId, Long userId, Long hotelId, double rating, String review) {

        HotelRating hotelRating = hotelRatingRepository.findById(hotelId).orElseThrow(()->
                new IllegalStateException("Hotel Rating with id " + hotelRatingId + "does not exist")
                );
        if(userId != null && !userId.equals(hotelRating.getUserId())){
            hotelRating.setUserId(userId);
        }
        if(hotelId != null && !hotelId.equals(hotelRating.getHotelId())){
            hotelRating.setHotelId(hotelId);
        }
        if(rating != 0 && !(hotelRating.getRating()==rating)){
            hotelRating.setRating(rating);
        }
        if(review != null && !review.isEmpty() && !hotelRating.getReview().equals(review)){
            hotelRating.setReview(review);
        }
    }
}
