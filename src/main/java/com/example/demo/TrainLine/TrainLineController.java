package com.example.demo.TrainLine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/trainLine")
public class TrainLineController {
    private final TrainLineService trainLineService;

    @Autowired
    public TrainLineController(TrainLineService trainLineService) {
        this.trainLineService = trainLineService;
    }
    @GetMapping
    public List<TrainLine> getTrainLines(){
        return this.trainLineService.getTrainLines();
    }

    @PostMapping
    public void registerTrainLine(@RequestBody TrainLine trainLine){
        trainLineService.addNewTrainLine(trainLine);
    }

    @DeleteMapping(path = "{trainLineId}")
    public void deleteTrainLine(@PathVariable("trainLineId") Long trainLineId){
        trainLineService.deleteTrainLine(trainLineId);
    }

    @PutMapping(path = "{trainLineId}")
    public void updateTrainLine(@PathVariable("trainLineId") Long trainLineId, @RequestParam(required = false, name = "trainId") Long trainId, @RequestParam(required = false, name = "fromStation") String fromStation, @RequestParam(required = false, name = "toStation") String toStation, @RequestParam(required = false, name = "departureDate") LocalDate departureDate, @RequestParam(required = false, name = "arrivalDate") LocalDate arrivalDate, @RequestParam(required = false, name = "departureTime") LocalTime departureTime, @RequestParam(required = false, name = "arrivalTime") LocalTime arrivalTime){
        trainLineService.updateTrainLine(trainLineId, trainId, fromStation, toStation, departureDate, arrivalDate, departureTime, arrivalTime);
    }
}
