package com.example.demo.Controller;
import com.example.demo.Service.FlightService;
import com.example.demo.Model.Flight;
import com.example.demo.Model.FlightAttributes;
import com.example.demo.Model.Plane;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.Commands.FlightCommand.AddFlightCommand;
import com.example.demo.Commands.FlightCommand.DeleteFlightCommand;
import com.example.demo.Commands.FlightCommand.UpdateFlightCommand;

import java.util.*;

@RestController
@RequestMapping(path = "api/search/flight")
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
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Optional<Flight> flight = Optional.ofNullable(flightService.getFlightId(id));
        return flight.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping(path = "/OneWay")
    public List<Flight> oneWay(@RequestBody FlightAttributes attributes){
        return this.flightService.getDirectFlights(attributes);
    }
    @PostMapping(path = "/OneWay")
    public List<Flight> oneWay1(@RequestBody FlightAttributes attributes){
        return this.flightService.getDirectFlights(attributes);
    }
    @GetMapping(path = "/TwoWay")
    public List<Object[]> twoWay(@RequestBody FlightAttributes attributes){
        return this.flightService.getTwoWay(attributes);
    }
    @PostMapping(path = "/TwoWay")
    public List<Object[]> twoWay1(@RequestBody FlightAttributes attributes){
        return this.flightService.getTwoWay(attributes);
    }
    @GetMapping(path = "/RoundTrip")
    public List<Object[]> roundTrip(@RequestBody FlightAttributes attributes){
        return this.flightService.getRoundTrip(attributes);
    }
    @PostMapping(path = "/RoundTrip")
    public List<Object[]> roundTrip1(@RequestBody FlightAttributes attributes){
        return this.flightService.getRoundTrip(attributes);
    }
    @GetMapping(path = "/Filtered")
    public List<Flight> filtered(@RequestBody FlightAttributes attributes){
        return this.flightService.getFiltered(attributes);
    }
    @PostMapping(path = "/Filtered")
    public List<Flight> filtered1(@RequestBody FlightAttributes attributes){
        return this.flightService.getFiltered(attributes);
    }
/*
    @PostMapping
    public void registerFlight(@RequestBody Flight flight){
        System.out.println("test1");
        flightService.addNewFlight(flight);
    }
*/
    @PostMapping
    public void registerFlight(@RequestBody Flight flight){
        AddFlightCommand addFlightCommand = new AddFlightCommand(flightService, flight);
        addFlightCommand.execute();
    }

    /*
    @DeleteMapping(path = "{FlightId}")
    public void deleteFlightId(@PathVariable("FlightId") Long flightId){
        flightService.deleteFlight(flightId);
    }
*/
    @DeleteMapping(path = "{FlightId}")
    public void deleteFlightId(@PathVariable("FlightId") Long flightId){
        DeleteFlightCommand deleteFlightCommand = new DeleteFlightCommand(flightService, flightId);
        deleteFlightCommand.execute();
    }
    /*
    @PutMapping(path = "{FlightId}")
    public void updateAirline(@PathVariable("FlightId") Long FlightId, @RequestParam(required = false,name ="departureLocation")String departureLocation, @RequestParam(required = false,name ="arrivalLocation") String arrivalLocation, @RequestParam(required = false,name ="timeOfDep") String timeOfDep, @RequestParam(required = false,name ="timeOfArrival") String timeOfArrival, @RequestParam(required = false,name ="plane") Plane plane, @RequestParam(required = false,name ="distance") float distance, @RequestParam(required = false,name ="flightPrice") float flightPrice, @RequestParam(required = false,name ="insurancePrice") float insurancePrice, @RequestParam(required = false,name ="extraBaggagePrice") float extraBaggagePrice, @RequestParam(required = false,name ="depAirport") String depAirport, @RequestParam(required = false,name ="arrivalAirPort") String arrivalAirPort, @RequestParam(required = false,name ="depDate") String depDate, @RequestParam(required = false,name ="arrivalDate")String arrivalDate ){
        flightService.updateFlight(FlightId, departureLocation,arrivalLocation,timeOfDep,timeOfArrival, plane, distance, flightPrice, insurancePrice, extraBaggagePrice,depAirport,arrivalAirPort,depDate,arrivalDate);
    }
*/
    @PutMapping(path = "{FlightId}")
    public void updateAirline(
            @PathVariable("FlightId") Long flightId,
            @RequestParam(required = false, name ="departureLocation") String departureLocation,
            @RequestParam(required = false, name ="arrivalLocation") String arrivalLocation,
            @RequestParam(required = false, name ="timeOfDep") String timeOfDep,
            @RequestParam(required = false, name ="timeOfArrival") String timeOfArrival,
            @RequestParam(required = false, name ="plane") Plane plane,
            @RequestParam(required = false, name ="distance") float distance,
            @RequestParam(required = false, name ="flightPrice") float flightPrice,
            @RequestParam(required = false, name ="insurancePrice") float insurancePrice,
            @RequestParam(required = false, name ="extraBaggagePrice") float extraBaggagePrice,
            @RequestParam(required = false, name ="depAirport") String depAirport,
            @RequestParam(required = false, name ="arrivalAirPort") String arrivalAirPort,
            @RequestParam(required = false, name ="depDate") String depDate,
            @RequestParam(required = false, name ="arrivalDate") String arrivalDate
    ){
        UpdateFlightCommand updateFlightCommand = new UpdateFlightCommand(
                flightService,flightId,departureLocation,arrivalLocation,timeOfDep,timeOfArrival,plane,distance,flightPrice,
                insurancePrice,extraBaggagePrice,depAirport,arrivalAirPort,depDate,arrivalDate
        );
        updateFlightCommand.execute();
    }


}
