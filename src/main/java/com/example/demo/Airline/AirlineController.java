package com.example.demo.Airline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/airline")
public class AirlineController {

    private final AirlineService airlineService;

    @Autowired
    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @GetMapping
    public List<Airline> getAirlines(){
        return this.airlineService.getAirlines();
    }

    @PostMapping
    public void registerAirline(@RequestBody Airline airline){
        airlineService.addNewAirline(airline);
    }

    @DeleteMapping(path = "{airlineId}")
    public void deleteAirline(@PathVariable("airlineId") Long airlineId){
        airlineService.deleteAirline(airlineId);
    }
}
