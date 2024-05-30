package com.example.demo.Controller;
import com.example.demo.Service.FlightReservationService;
import com.example.demo.Model.FlightReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/transaction/flightReservation")

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

        @GetMapping(path = "user")
        public List<FlightReservation> getUserFlightReservations(){
            return this.flightReservationService.getUserFlightReservations();
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
        public void updateFlightReservation(@PathVariable("FlightReservationID") Long id, @RequestParam(required = false,name ="user_id") Long user_id, @RequestParam(required = false,name ="package_id") Long package_id, @RequestParam(required = false,name ="seat_id") Long seat_id, @RequestParam(required = false,name ="seatChargeable")boolean seatChargeable, @RequestParam(required = false,name ="extraBaggage") boolean extraBaggage, @RequestParam(required = false,name ="withInsurance") boolean withInsurance, @RequestParam(required = false,name ="paymentMethod") FlightReservation.PaymentMethod paymentMethod){
            flightReservationService.updateFlightReservation(id,package_id,seat_id, seatChargeable, extraBaggage, withInsurance,paymentMethod);
        }




}
