package com.example.demo.HotelRating;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/hotelRating")
public class HotelRatingController {

    private final HotelRatingService hotelRatingService;


    @Autowired
    public HotelRatingController(HotelRatingService hotelRatingService) {
        this.hotelRatingService = hotelRatingService;
    }

    @GetMapping
    public List<HotelRating> getHotelRatings(){
        return this.hotelRatingService.getHotelRatings();
    }
    @PostMapping
    public void registerHotelRating(@RequestBody HotelRating hotelRating){
        hotelRatingService.addNewHotelRating(hotelRating);
    }

    @DeleteMapping(path = "{hotelRatingId}")
    public void deleteHotelRating(@PathVariable("hotelRatingId") Long hotelRatingId){
        hotelRatingService.deleteHotelRating(hotelRatingId);
    }

    @PutMapping(path = "{updateHotelRatingId}")
    public void updateHotelRating(@PathVariable("updateHotelRatingId") Long hotelRatingId, @RequestParam(required = false,name = "userId") Long userId, @RequestParam(required = false, name = "hotelId") Long hotelId, @RequestParam(required = false, name = "rating") double rating, @RequestParam(required = false, name = "review") String review){
        hotelRatingService.updateHotelRating(hotelRatingId, userId, hotelId, rating, review);
    }
}
