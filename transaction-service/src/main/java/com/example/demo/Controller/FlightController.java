package com.example.demo.Controller;

import com.example.demo.Service.FlightService;
import com.example.demo.Model.Flight;
import com.example.demo.Model.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/transaction/flight")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<Flight> getFlights(){
        return this.flightService.getFlights();
    }

    @PostMapping
    public void registerFlight(@RequestBody Flight flight){
        flightService.addNewFlight(flight);
    }

    @DeleteMapping(path = "{FlightId}")
    public void deleteFlightId(@PathVariable("FlightId") Long flightId){
        flightService.deleteFlight(flightId);
    }

    @PutMapping(path = "{FlightId}")
    public void updateAirline(@PathVariable("FlightId") Long FlightId, @RequestParam(required = false,name ="departureLocation")String departureLocation, @RequestParam(required = false,name ="arrivalLocation") String arrivalLocation, @RequestParam(required = false,name ="timeOfDep") String timeOfDep, @RequestParam(required = false,name ="timeOfArrival") String timeOfArrival, @RequestParam(required = false,name ="plane") Plane plane, @RequestParam(required = false,name ="distance") float distance, @RequestParam(required = false,name ="flightPrice") float flightPrice, @RequestParam(required = false,name ="insurancePrice") float insurancePrice, @RequestParam(required = false,name ="extraBaggagePrice") float extraBaggagePrice, @RequestParam(required = false,name ="depAirport") String depAirport, @RequestParam(required = false,name ="arrivalAirPort") String arrivalAirPort, @RequestParam(required = false,name ="depDate") String depDate, @RequestParam(required = false,name ="arrivalDate")String arrivalDate ){
        flightService.updateFlight(FlightId, departureLocation,arrivalLocation,timeOfDep,timeOfArrival, plane, distance, flightPrice, insurancePrice, extraBaggagePrice,depAirport,arrivalAirPort,depDate,arrivalDate);
    }
}
