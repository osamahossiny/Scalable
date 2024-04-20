package com.example.demo.Controller;
import com.example.demo.Service.PlaneSeatService;
import com.example.demo.model.Plane;
import com.example.demo.model.PlaneSeat;
import com.example.demo.model.SeatCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(path = "api/v1/planeSeat")
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

    @PostMapping
    public void registerPlaneSeat(@RequestBody PlaneSeat planeSeat){
        planeSeatService.addNewPlaneSeat(planeSeat);
    }

    @DeleteMapping(path = "{planeSeatId}")
    public void deletePlaneSeat(@PathVariable("planeSeatId") Long planeSeatId){
        planeSeatService.deletePlaneSeat(planeSeatId);
    }

    @PutMapping(path = "{planeSeatId}")
    public void updateSeatPlane(@PathVariable("planeSeatId") Long planeId, @RequestParam(required = false,name = "seatNumber") int seatNumber, @RequestParam(required = false,name = "price") int price, @RequestParam(required = false, name = "SeatCategory") SeatCategory seatCategory, @RequestParam(required = false, name = "plane") Plane plane){
        planeSeatService.updatePlaneSeat(planeId, seatNumber, seatCategory, plane,price);
    }
}
