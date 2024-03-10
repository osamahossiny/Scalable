package com.example.demo.Hotels;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/hotel")
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }
    @GetMapping
    public List<Hotel> getHotels(){
        return hotelService.getHotels();
    }

    @PostMapping
    public void registerHotel(@RequestBody Hotel hotel){
        hotelService.addNewHotel(hotel);
    }
    @DeleteMapping(path = "{hotelId}")
    public void deleteHotel(@PathVariable("hotelId") Long hotelId){
        hotelService.deleteHotel(hotelId);
    }
        @PutMapping(path = "{hotelId}")
    public void updateHotel(@PathVariable("hotelId") Long hotelId, @RequestParam(required = false,name = "name") String name, @RequestParam(required = false, name = "country") String country, @RequestParam(required = false, name = "city") String city, @RequestParam(required = false, name = "address") String address, @RequestParam(required = false, name = "type") String type, @RequestParam(required = false, name = "additionalInfo") String additionalInfo, @RequestParam(required = false, name = "foodPricePerPerson") double foodPricePerPerson, @RequestParam(required = false, name = "cancellationPolicy") String cancellationPolicy, @RequestParam(required = false, name = "rules") List<String> rules, @RequestParam(required = false, name = "amenities") List<String> amenities, @RequestParam(required = false, name = "IBAN") String IBAN){
        hotelService.updateHotel(hotelId, name, country, city, address, type, additionalInfo, foodPricePerPerson, cancellationPolicy, rules, amenities, IBAN);
    }
}
