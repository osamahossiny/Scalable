package com.example.demo.TrainSchema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/train")
public class TrainController {

    private final TrainService trainService;

    @Autowired
    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping
    public List<Train> getTrains(){
        return this.trainService.getTrains();
    }

    @PostMapping
    public void registerTrain(@RequestBody Train train){
        trainService.addNewTrain(train);
    }

    @DeleteMapping(path = "{trainId}")
    public void deleteTrain(@PathVariable("trainId") Long trainId){
        trainService.deleteTrain(trainId);
    }

    @PutMapping(path = "{trainId}")
    public void updateTrain(@PathVariable("trainId") Long trainId, @RequestParam(required = false, name = "trainCompanyId") Long trainCompanyId, @RequestParam(required = false, name = "trainName") String trainName, @RequestParam(required = false, name = "trainModel") String trainModel, @RequestParam(required = false, name = "trainType") String trainType){
        trainService.updateTrain(trainId, trainCompanyId, trainName, trainModel, trainType);
    }

}
