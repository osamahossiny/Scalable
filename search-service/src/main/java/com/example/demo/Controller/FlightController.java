package com.example.demo.Controller;
import com.example.demo.Service.FlightService;
import com.example.demo.model.Flight;
import com.example.demo.model.FlightAttributes;
import com.example.demo.model.Plane;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/flight")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<Flight> getFlights(){
        System.out.println(this.flightService.getFlights());
        return this.flightService.getFlights();
    }

    @GetMapping(path = "/OneWay")
    public Optional<List<Flight>> search(@RequestBody FlightAttributes attributes){
        return this.flightService.getFlights(attributes);
    }
    @GetMapping(path = "/TwoWay")
    public List<Object[]> filters(@RequestBody FlightAttributes attributes){
        return this.flightService.getTwoWay(attributes);
    }

    @PostMapping
    public void registerFlight(@RequestBody Flight flight){
        System.out.println("test1");flightService.addNewFlight(flight);
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
