package com.example.demo.Controller;
import com.example.demo.Model.*;
import com.example.demo.Service.FlightReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Commands.FlightReservationCommand.AddFlightReservationCommand;
import com.example.demo.Commands.FlightReservationCommand.DeleteFlightReservationCommand;
import com.example.demo.Commands.FlightReservationCommand.UpdateFlightReservationCommand;



import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "api/search/flightReservation")

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
        @GetMapping("/{id}")
        public ResponseEntity<FlightReservation> getFlightReservationsById(@PathVariable Long id) {
            Optional<FlightReservation> flightReservation = Optional.ofNullable(flightReservationService.getFlightReservationsId(id));
            return flightReservation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }
        /*
        @PostMapping
        public void registerFlightReservation(@RequestBody FlightReservation flightReservation){
            flightReservationService.addNewFlightReservation(flightReservation);
        }


         */

        @PostMapping
        public void registerFlightReservation(@RequestBody FlightReservation flightReservation){
            AddFlightReservationCommand addFlightReservationCommand = new AddFlightReservationCommand(flightReservationService, flightReservation);
           addFlightReservationCommand.execute();
        }
/*
    @DeleteMapping(path = "{FlightReservationID}")
        public void deleteFlightReservationId(@PathVariable("FlightReservationID") Long flightReservationId){
            flightReservationService.deleteFlightReservation(flightReservationId);
        }

 */

    @DeleteMapping(path = "{FlightReservationID}")
    public void deleteFlightReservationId(@PathVariable("FlightReservationID") Long flightReservationId){
        DeleteFlightReservationCommand deleteFlightReservationCommand = new DeleteFlightReservationCommand(flightReservationService, flightReservationId);
        deleteFlightReservationCommand.execute();
    }


/*
    @PutMapping(path = "{FlightReservationID}")
        public void updateFlightReservation(@PathVariable("FlightReservationID") Long id, @RequestParam(required = false,name ="appUser") AppUser appUser, @RequestParam(required = false,name ="flightPackage") FlightPackage flightPackage, @RequestParam(required = false,name ="planeSeat") PlaneSeat planeSeat, @RequestParam(required = false,name ="seatChargeable")boolean seatChargeable, @RequestParam(required = false,name =" extraBaggage") boolean extraBaggage, @RequestParam(required = false,name ="withInsurance") boolean withInsurance, @RequestParam(required = false,name ="totalPrice")int totalPrice){
            flightReservationService.updateFlightReservation(id,appUser,flightPackage,planeSeat, seatChargeable, extraBaggage, withInsurance,totalPrice);
        }
*/
@PutMapping(path = "{FlightReservationID}")
public void updateFlightReservation(
        @PathVariable("FlightReservationID") Long id,
        @RequestParam(required = false, name ="appUser") User appUser,
        @RequestParam(required = false, name ="flightPackage") FlightPackage flightPackage,
        @RequestParam(required = false, name ="planeSeat") PlaneSeat planeSeat,
        @RequestParam(required = false, name ="seatChargeable") boolean seatChargeable,
        @RequestParam(required = false, name ="extraBaggage") boolean extraBaggage,
        @RequestParam(required = false, name ="withInsurance") boolean withInsurance,
        @RequestParam(required = false, name ="totalPrice") int totalPrice
){
    UpdateFlightReservationCommand updateFlightReservationCommand = new UpdateFlightReservationCommand(
            flightReservationService,id,appUser,flightPackage,planeSeat,seatChargeable,extraBaggage,withInsurance,totalPrice
    );
    updateFlightReservationCommand.execute();
}





}
