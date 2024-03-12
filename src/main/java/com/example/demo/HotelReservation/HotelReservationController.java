package com.example.demo.HotelReservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/hotelReservation")
public class HotelReservationController {

    private final HotelReservationService hotelReservationService;

    @Autowired
    public HotelReservationController(HotelReservationService hotelReservationService) {
        this.hotelReservationService = hotelReservationService;
    }


    @GetMapping
    public List<HotelReservation> getHotelReservations() {
        return hotelReservationService.getHotelRating();
    }

    @PostMapping
    public void registerHotelReservation(@RequestBody HotelReservation hotelReservation) {
        hotelReservationService.addNewHotelReservation(hotelReservation);
    }

    @DeleteMapping(path = "{hotelReservationId}")
    public void deleteHotelReservation(@PathVariable("hotelReservationId") Long hotelReservationId) {
        hotelReservationService.deleteHotelReservation(hotelReservationId);
    }

    @PutMapping(path = "{hotelReservationId}")
    public void updateHotelReservation(@PathVariable("hotelReservationId") Long hotelReservationId, @RequestParam(required = false, name = "userId") Long userId, @RequestParam(required = false, name = "hotelId") Long hotelId, @RequestParam(required = false, name = "roomId") Long roomId, @RequestParam(required = false, name = "checkIn") LocalDate checkIn, @RequestParam(required = false, name = "checkOut") LocalDate checkOut) {
        hotelReservationService.updateHotelReservation(hotelReservationId, userId, hotelId, roomId, checkIn, checkOut);
    }

}
