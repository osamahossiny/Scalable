package com.example.demo.Controller;
import com.example.demo.Service.FlightPackageService;
import com.example.demo.model.Flight;
import com.example.demo.model.FlightPackage;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.Commands.FlightPackageCommand.AddFlightPackageCommand;
import com.example.demo.Commands.FlightPackageCommand.DeleteFlightPackageCommand;
import com.example.demo.Commands.FlightPackageCommand.UpdateFlightPackageCommand;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/flightPackage")
public class FlightPackageController {

    private final FlightPackageService flightPackageService;

    @Autowired
    public FlightPackageController(FlightPackageService flightPackageService) {
        this.flightPackageService = flightPackageService;
    }

    @GetMapping
    public List<FlightPackage> getFlightPackages(){
        return this.flightPackageService.getFlightPackages();
    }
    /*
    @PostMapping
    public void registerFlightPackage(@RequestBody FlightPackage flightPackage){
        flightPackageService.addNewFlightPackage(flightPackage);
    }

     */
    @PostMapping
    public void registerFlightPackage(@RequestBody FlightPackage flightPackage){
        AddFlightPackageCommand addFlightPackageCommand = new AddFlightPackageCommand(flightPackageService, flightPackage);
        addFlightPackageCommand.execute();
    }

    /*
    @DeleteMapping(path = "{FlightPackageId}")
    public void deleteFlightPackageId(@PathVariable("FlightPackageId") Long flightPackageId){
        flightPackageService.deleteFlightPackage(flightPackageId);
    }

     */
    @DeleteMapping(path = "{FlightPackageId}")
    public void deleteFlightPackageId(@PathVariable("FlightPackageId") Long flightPackageId){
        DeleteFlightPackageCommand deleteFlightPackageCommand = new DeleteFlightPackageCommand(flightPackageService, flightPackageId);
        deleteFlightPackageCommand.execute();
    }

/*
    @PutMapping(path = "{FlightPackageId}")
    public void updateFlightPackage(@PathVariable("FlightPackageId") Long FlightPackageId, @RequestParam(required = false,name ="flight") Flight flight, @RequestParam(required = false,name ="weightCabin")  int weightCabin, @RequestParam(required = false,name ="weightCheckIn") int weightCheckIn, @RequestParam(required = false,name ="cancellationFee")int cancellationFee, @RequestParam(required = false,name ="dateChangeFee")int dateChangeFee, @RequestParam(required = false,name ="mealInfo") String mealInfo, @RequestParam(required = false,name ="expressCheckIn")boolean expressCheckIn, @RequestParam(required = false,name ="price")int price){
        flightPackageService.updateFlightPackage(FlightPackageId,flight,weightCabin,weightCheckIn,cancellationFee,dateChangeFee,mealInfo,expressCheckIn,price);
    }

 */
@PutMapping(path = "{FlightPackageId}")
public void updateFlightPackage(
        @PathVariable("FlightPackageId") Long flightPackageId,
        @RequestParam(required = false, name ="flight") Flight flight,
        @RequestParam(required = false, name ="weightCabin") int weightCabin,
        @RequestParam(required = false, name ="weightCheckIn") int weightCheckIn,
        @RequestParam(required = false, name ="cancellationFee") int cancellationFee,
        @RequestParam(required = false, name ="dateChangeFee") int dateChangeFee,
        @RequestParam(required = false, name ="mealInfo") String mealInfo,
        @RequestParam(required = false, name ="expressCheckIn") boolean expressCheckIn,
        @RequestParam(required = false, name ="price") int price
){
    UpdateFlightPackageCommand updateFlightPackageCommand = new UpdateFlightPackageCommand(
            flightPackageService,flightPackageId,flight,weightCabin,weightCheckIn,cancellationFee,dateChangeFee,mealInfo,expressCheckIn,price
    );
    updateFlightPackageCommand.execute();
}

}
