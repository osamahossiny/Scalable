package com.example.demo.Controller;

import com.example.demo.Service.AirlineService;
import com.example.demo.Commands.AddAirlineCommand;
import com.example.demo.Model.Airline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/admin/airline")
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
        //airlineService.addNewAirline(airline);
        AddAirlineCommand addAirlineCommand= new AddAirlineCommand(airlineService, airline);
        addAirlineCommand.execute();
    }

    @DeleteMapping(path = "{airlineId}")
    public void deleteAirline(@PathVariable("airlineId") Long airlineId){
        airlineService.deleteAirline(airlineId);
    }

    @PutMapping(path = "{airlineId}")
    public void updateAirline(@PathVariable("airlineId") Long airlineId, @RequestParam(required = false,name = "name") String name, @RequestParam(required = false, name = "IBAN") String IBAN, @RequestParam(required = false, name = "customerServiceNumber") String customerServiceNumber){
        airlineService.updateAirline(airlineId, name, IBAN, customerServiceNumber);
    }
}
