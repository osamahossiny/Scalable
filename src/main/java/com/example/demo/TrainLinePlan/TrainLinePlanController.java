package com.example.demo.TrainLinePlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/trainLinePlan")
public class TrainLinePlanController {
    private final TrainLinePlanService trainLinePlanService;

    @Autowired
    public TrainLinePlanController(TrainLinePlanService trainLinePlanService) {
        this.trainLinePlanService = trainLinePlanService;
    }
    @GetMapping
    public List<TrainLinePlan> getTrainLinePlans(){
        return this.trainLinePlanService.getTrainLinePlans();
    }

    @PostMapping
    public void registerTrainLinePlan(@RequestBody TrainLinePlan trainLinePlan){
        trainLinePlanService.addNewTrainLinePlan(trainLinePlan);
    }

    @DeleteMapping(path = "{trainLinePlanId}")
    public void deleteTrainLinePlan(@PathVariable("trainLinePlanId") Long trainLinePlanId){
        trainLinePlanService.deleteTrainLinePlan(trainLinePlanId);
    }

    @PutMapping(path = "{trainLinePlanId}")
    public void updateTrainLinePlan(@PathVariable("trainLinePlanId") Long trainLinePlanId, @RequestParam(required = false, name = "trainLineId") Long trainLineId, @RequestParam(required = false, name = "classType") String classType, @RequestParam(required = false, name = "price") int price, @RequestParam(required = false, name = "insurancePrice") int insurancePrice, @RequestParam(required = false, name = "availableTickets") int availableTickets){
        trainLinePlanService.updateTrainLinePlan(trainLinePlanId, trainLineId, classType, price, insurancePrice, availableTickets);
    }
}
