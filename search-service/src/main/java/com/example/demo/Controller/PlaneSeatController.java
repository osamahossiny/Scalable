package com.example.demo.Controller;
import com.example.demo.Service.PlaneSeatService;
import com.example.demo.Model.Plane;
import com.example.demo.Model.PlaneSeat;
import com.example.demo.Model.SeatCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import com.example.demo.Commands.PlaneSeatCommand.AddPlaneSeatCommand;
import com.example.demo.Commands.PlaneSeatCommand.DeletePlaneSeatCommand;
import com.example.demo.Commands.PlaneSeatCommand.UpdatePlaneSeatCommand;




@RestController
@RequestMapping(path = "api/search/planeSeat")
public class PlaneSeatController {
    private final PlaneSeatService planeSeatService;

    @Autowired
    public PlaneSeatController(PlaneSeatService planeSeatService) {
        this.planeSeatService = planeSeatService;
    }

    @GetMapping
    public List<PlaneSeat> getPlaneSeats(){
        return this.planeSeatService.getPlaneSeats();
    }
    @GetMapping("/{id}")
    public ResponseEntity<PlaneSeat> getPlaneById(@PathVariable Long id) {
        Optional<PlaneSeat> planeSeat = Optional.ofNullable(planeSeatService.getPlaneSeatId(id));
        return planeSeat.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    /*
    @PostMapping
    public void registerPlaneSeat(@RequestBody PlaneSeat planeSeat){
        planeSeatService.addNewPlaneSeat(planeSeat);
    }
     */
    @PostMapping
    public void registerPlaneSeat(@RequestBody PlaneSeat planeSeat){
        AddPlaneSeatCommand addPlaneSeatCommand = new AddPlaneSeatCommand(planeSeatService, planeSeat);
        addPlaneSeatCommand.execute();
    }


    /*
    @DeleteMapping(path = "{planeSeatId}")
    public void deletePlaneSeat(@PathVariable("planeSeatId") Long planeSeatId){
        planeSeatService.deletePlaneSeat(planeSeatId);
    }

     */
    @DeleteMapping(path = "{planeSeatId}")
    public void deletePlaneSeat(@PathVariable("planeSeatId") Long planeSeatId){
        DeletePlaneSeatCommand deletePlaneSeatCommand = new DeletePlaneSeatCommand(planeSeatService, planeSeatId);
        deletePlaneSeatCommand.execute();
    }

    /*
    @PutMapping(path = "{planeSeatId}")
    public void updateSeatPlane(@PathVariable("planeSeatId") Long planeId, @RequestParam(required = false,name = "seatNumber") int seatNumber, @RequestParam(required = false,name = "price") int price, @RequestParam(required = false, name = "SeatCategory") SeatCategory seatCategory, @RequestParam(required = false, name = "plane") Plane plane){
        planeSeatService.updatePlaneSeat(planeId, seatNumber, seatCategory, plane,price);
    }

     */
    @PutMapping(path = "{planeSeatId}")
    public void updateSeatPlane(
            @PathVariable("planeSeatId") Long planeSeatId,
            @RequestParam(required = false, name = "seatNumber") int seatNumber,
            @RequestParam(required = false, name = "price") int price,
            @RequestParam(required = false, name = "SeatCategory") SeatCategory seatCategory,
            @RequestParam(required = false, name = "plane") Plane plane
    ){
        UpdatePlaneSeatCommand updatePlaneSeatCommand = new UpdatePlaneSeatCommand(
                planeSeatService,planeSeatId,seatNumber,seatCategory,plane,price
        );
        updatePlaneSeatCommand.execute();
    }


}
