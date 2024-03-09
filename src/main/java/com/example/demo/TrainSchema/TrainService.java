package com.example.demo.TrainSchema;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainService {
    private final TrainRepository trainRepository;

    @Autowired
    public TrainService(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    public List<Train> getTrains() {
        return trainRepository.findAll();
    }

    public void addNewTrain(Train train) {
        Optional<Train> trainByName = trainRepository.findTrainByName(train.getTrainName());
        if (trainByName.isPresent()){
            throw new IllegalStateException("Train already exists");
        }
        trainRepository.save(train);
    }

    public void deleteTrain(Long trainId) {
        boolean exists = trainRepository.existsById(trainId);
        if (!exists) {
            throw new IllegalStateException("Train with id " + trainId + " does not exist");
        }
        trainRepository.deleteById(trainId);
    }

    @Transactional
    public void updateTrain(Long trainId,Long trainCompanyId, String trainName, String trainModel, String trainType){
        Train train = trainRepository.findById(trainId)
                .orElseThrow(() -> new IllegalStateException(
                        "Train with id " + trainId + " does not exist"
                ));
        if(trainCompanyId != null && !train.getTrainCompanyId().equals(trainCompanyId)){
            train.setTrainCompanyId(trainCompanyId);
        }
        if(trainName != null && !train.getTrainName().equals(trainName)){
            train.setTrainName(trainName);
        }
        if(trainModel != null && !train.getTrainModel().equals(trainModel)){
            train.setTrainModel(trainModel);
        }
        if(trainType != null && !train.getTrainType().equals(trainType)){
            train.setTrainType(trainType);
        }
    }
}
