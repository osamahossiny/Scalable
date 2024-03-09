package com.example.demo.TrainLine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class TrainLineService {
    private final TrainLineRepository trainLineRepository;

    @Autowired
    public TrainLineService(TrainLineRepository trainLineRepository) {
        this.trainLineRepository = trainLineRepository;
    }

    public List<TrainLine> getTrainLines() {
        return trainLineRepository.findAll();
    }

    public void addNewTrainLine(TrainLine trainLine) {
        Optional<TrainLine> trainLineOptional = trainLineRepository.findTrainLineById(trainLine.getTrainLineId());
        if (trainLineOptional.isPresent()) {
            throw new IllegalStateException("Train Line already exists");
        }
        trainLineRepository.save(trainLine);
    }

    public void deleteTrainLine(Long trainLineId) {
        boolean exists = trainLineRepository.existsById(trainLineId);
        if (!exists) {
            throw new IllegalStateException("Train Line with id " + trainLineId + " does not exist");
        }
        trainLineRepository.deleteById(trainLineId);
    }

    @Transactional
    public void updateTrainLine(Long trainLineId,Long trainId, String fromStation, String toStation, LocalDate departureDate, LocalDate arrivalDate, LocalTime departureTime, LocalTime arrivalTime){
        TrainLine trainLine = trainLineRepository.findById(trainLineId)
                .orElseThrow(() -> new IllegalStateException(
                        "Train Line with id " + trainLineId + " does not exist"
                ));
        if(trainId != null && !trainLine.getTrainId().equals(trainId)){
            trainLine.setTrainId(trainId);
        }
        if(fromStation != null && !trainLine.getFromStation().equals(fromStation)){
            trainLine.setFromStation(fromStation);
        }
        if(toStation != null && !trainLine.getToStation().equals(toStation)){
            trainLine.setToStation(toStation);
        }
        if(departureDate != null && !trainLine.getDepartureDate().equals(departureDate)){
            trainLine.setDepartureDate(departureDate);
        }
        if(arrivalDate != null && !trainLine.getArrivalDate().equals(arrivalDate)){
            trainLine.setArrivalDate(arrivalDate);
        }
        if(departureTime != null && !trainLine.getDepartureTime().equals(departureTime)){
            trainLine.setDepartureTime(departureTime);
        }
        if(arrivalTime != null && !trainLine.getArrivalTime().equals(arrivalTime)){
            trainLine.setArrivalTime(arrivalTime);
        }
    }
}
