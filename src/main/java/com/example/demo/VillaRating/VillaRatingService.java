package com.example.demo.VillaRating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VillaRatingService {

    private final VillaRatingRepository villaRatingRepository;

    @Autowired
    public VillaRatingService(VillaRatingRepository villaRatingRepository) {
        this.villaRatingRepository = villaRatingRepository;
    }

    public void addNewVillaRating(VillaRating villaRating) {
        Optional<VillaRating> existingRating = villaRatingRepository.findRateByUserIdAndVillaId(villaRating.getUserId(), villaRating.getVillaId());
        if (existingRating.isPresent()) {
            throw new IllegalStateException("Rating for this user and villa already exists.");
        }
        villaRatingRepository.save(villaRating);
    }

    public void deleteVillaRating(Long userId, Long villaId) {
        Optional<VillaRating> existingRating = villaRatingRepository.findRateByUserIdAndVillaId(userId, villaId);
        if (existingRating.isEmpty()) {
            throw new IllegalStateException("Rating for this user and villa does not exist.");
        }
        villaRatingRepository.delete(existingRating.get());
    }

    @Transactional
    public void updateVillaRating(Long userId, Long villaId, Double rating, String review) {
        Optional<VillaRating> optionalVillaRating = villaRatingRepository.findRateByUserIdAndVillaId(userId, villaId);
        VillaRating villaRating = optionalVillaRating.orElseThrow(() ->
                new IllegalStateException("Villa rating for user " + userId + " and villa " + villaId + " does not exist")
        );

        // Update rating and review if provided and different from the current values
        if (rating != null && !rating.equals(villaRating.getRating())) {
            villaRating.setRating(rating);
        }
        if (review != null && !review.equals(villaRating.getReview())) {
            villaRating.setReview(review);
        }
    }
}
