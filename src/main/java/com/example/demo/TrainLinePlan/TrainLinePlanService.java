package com.example.demo.TrainLinePlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrainLinePlanService {
    private final TrainLinePlanRepository trainLinePlanRepository;
    @Autowired
    public TrainLinePlanService(TrainLinePlanRepository trainLinePlanRepository) {
        this.trainLinePlanRepository = trainLinePlanRepository;
    }
    public List<TrainLinePlan> getTrainLinePlans() {
        return trainLinePlanRepository.findAll();
    }

    public void addNewTrainLinePlan(TrainLinePlan trainLinePlan) {
        trainLinePlanRepository.save(trainLinePlan);
    }

    public void deleteTrainLinePlan(Long trainLinePlanId) {
        boolean exists = trainLinePlanRepository.existsById(trainLinePlanId);
        if (!exists) {
            throw new IllegalStateException("Train Line Plan with id " + trainLinePlanId + " does not exist");
        }
        trainLinePlanRepository.deleteById(trainLinePlanId);
    }

    @Transactional
    public void updateTrainLinePlan(Long trainLinePlanId, Long trainLineId, String classType, int price, int insurancePrice, int availableTickets){
        TrainLinePlan trainLinePlan = trainLinePlanRepository.findById(trainLinePlanId)
                .orElseThrow(() -> new IllegalStateException(
                        "Train Line Plan with id " + trainLinePlanId + " does not exist"
                ));
        if(trainLineId != null && !trainLinePlan.getTrainLineId().equals(trainLineId)){
            trainLinePlan.setTrainLineId(trainLineId);
        }
        if(classType != null && !trainLinePlan.getClassType().equals(classType)){
            trainLinePlan.setClassType(classType);
        }
        if(price != 0 && trainLinePlan.getPrice() != price){
            trainLinePlan.setPrice(price);
        }
        if(insurancePrice != 0 && trainLinePlan.getInsurancePrice() != insurancePrice){
            trainLinePlan.setInsurancePrice(insurancePrice);
        }
        if(availableTickets != 0 && trainLinePlan.getAvailableTickets() != availableTickets){
            trainLinePlan.setAvailableTickets(availableTickets);
        }
    }
}
