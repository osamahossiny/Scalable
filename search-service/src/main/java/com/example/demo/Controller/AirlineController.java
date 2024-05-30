package com.example.demo.Controller;

import com.example.demo.Commands.AirlineCommand.AddAirlineCommand;
import com.example.demo.Service.AirlineService;
import com.example.demo.Model.Airline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Commands.AirlineCommand.DeleteAirlineCommand;
import com.example.demo.Commands.AirlineCommand.UpdateAirlineCommand;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/search/airline")
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
    @GetMapping("/{id}")
    public ResponseEntity<Airline> getAirlineById(@PathVariable Long id) {
        Optional<Airline> airline = Optional.ofNullable(airlineService.getAirlineById(id));
        return airline.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public void registerAirline(@RequestBody Airline airline){
        AddAirlineCommand addAirlineCommand = new AddAirlineCommand(airlineService,airline);
        addAirlineCommand.execute();
    }
    /*
    @DeleteMapping(path = "{airlineId}")
    public void deleteAirline(@PathVariable("airlineId") Long airlineId){
        airlineService.deleteAirline(airlineId);
    }

     */
    @DeleteMapping(path = "{airlineId}")
    public void deleteAirline(@PathVariable("airlineId") Long airlineId){
        DeleteAirlineCommand deleteAirlineCommand = new DeleteAirlineCommand(airlineService, airlineId);
        deleteAirlineCommand.execute();
    }

    /*
    @PutMapping(path = "{airlineId}")
    public void updateAirline(@PathVariable("airlineId") Long airlineId, @RequestParam(required = false,name = "name") String name, @RequestParam(required = false, name = "IBAN") String IBAN, @RequestParam(required = false, name = "customerServiceNumber") String customerServiceNumber){
        airlineService.updateAirline(airlineId, name, IBAN, customerServiceNumber);
    }*/
    @PutMapping(path = "{airlineId}")
    public void updateAirline(
            @PathVariable("airlineId") Long airlineId,
            @RequestParam(required = false, name = "name") String name,
            @RequestParam(required = false, name = "IBAN") String IBAN,
            @RequestParam(required = false, name = "customerServiceNumber") String customerServiceNumber
    ){
        UpdateAirlineCommand updateAirlineCommand = new UpdateAirlineCommand(
                airlineService,airlineId,name,IBAN,customerServiceNumber
        );
        updateAirlineCommand.execute();
    }


}
