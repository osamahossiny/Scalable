package com.example.demo.TrainTicket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TrainTicketService {

    private final TrainTicketRepository trainTicketRepository;

    @Autowired
    public TrainTicketService(TrainTicketRepository trainTicketRepository) {
        this.trainTicketRepository = trainTicketRepository;
    }

    public List<TrainTicket> getTrainTickets() {
        return trainTicketRepository.findAll();
    }

    public void addNewTrainTicket(TrainTicket trainTicket) {
        Optional<TrainTicket> trainTicketById = trainTicketRepository.findTrainTicketById(trainTicket.getId());

        if (trainTicketById.isPresent()) {
            throw new IllegalStateException("A train ticket with this id already exists.");
        }
        trainTicketRepository.save(trainTicket);
    }

    public void deleteTrainTicket(Long trainTicketId) {
        boolean exists = trainTicketRepository.existsById(trainTicketId);

        if (!exists) {
            throw new IllegalStateException("Train ticket with id " + trainTicketId + " does not exist.");
        }
        trainTicketRepository.deleteById(trainTicketId);
    }

    @Transactional
    public void updateTrainTicket(Long trainTicketId, Long UserId, Long TrainLinePlanId, int price, boolean withInsurance) {

        TrainTicket trainTicket = trainTicketRepository.findById(trainTicketId).orElseThrow(() ->
                new IllegalStateException("train ticket with id " + trainTicketId + " does not exist")
        );
        if (UserId != null && !trainTicket.getUserId().equals(UserId)) {
            trainTicket.setUserId(UserId);
        }
        if (TrainLinePlanId != null && !trainTicket.getTrainLinePlanId().equals(TrainLinePlanId)) {
            trainTicket.setTrainLinePlanId(TrainLinePlanId);
        }
        if (price >= 0 && trainTicket.getPrice() != price) {
            trainTicket.setPrice(price);
        }
        if (trainTicket.isWithInsurance() != withInsurance) {
            trainTicket.setWithInsurance(withInsurance);
        }
    }
}