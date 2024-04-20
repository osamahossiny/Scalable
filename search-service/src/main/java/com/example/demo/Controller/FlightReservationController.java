package com.example.demo.Controller;
import com.example.demo.Service.FlightReservationService;
import com.example.demo.model.AppUser;
import com.example.demo.model.FlightPackage;
import com.example.demo.model.FlightReservation;
import com.example.demo.model.PlaneSeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/v1/flightReservation")

public class FlightReservationController {
        private final FlightReservationService flightReservationService;

        @Autowired
        public FlightReservationController(FlightReservationService flightReservationService) {
            this.flightReservationService = flightReservationService;
        }

        @GetMapping
        public List<FlightReservation> getFlightReservations(){
            return this.flightReservationService.getFlightReservations();
        }

        @PostMapping
        public void registerFlightReservation(@RequestBody FlightReservation flightReservation){
            flightReservationService.addNewFlightReservation(flightReservation);
        }

        @DeleteMapping(path = "{FlightReservationID}")
        public void deleteFlightReservationId(@PathVariable("FlightReservationID") Long flightReservationId){
            flightReservationService.deleteFlightReservation(flightReservationId);
        }

        @PutMapping(path = "{FlightReservationID}")
        public void updateFlightReservation(@PathVariable("FlightReservationID") Long id, @RequestParam(required = false,name ="appUser") AppUser appUser, @RequestParam(required = false,name ="flightPackage") FlightPackage flightPackage, @RequestParam(required = false,name ="planeSeat") PlaneSeat planeSeat, @RequestParam(required = false,name ="seatChargeable")boolean seatChargeable, @RequestParam(required = false,name =" extraBaggage") boolean extraBaggage, @RequestParam(required = false,name ="withInsurance") boolean withInsurance, @RequestParam(required = false,name ="totalPrice")int totalPrice){
            flightReservationService.updateFlightReservation(id,appUser,flightPackage,planeSeat, seatChargeable, extraBaggage, withInsurance,totalPrice);
        }




}
