package com.example.demo.VillaReservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Date;
@Service
public class VillaReservationService {

    private final VillaReservationRepository villaReservationRepository;

    @Autowired
    public VillaReservationService(VillaReservationRepository villaReservationRepository) {
        this.villaReservationRepository = villaReservationRepository;
    }

    public void addNewVillaReservation(VillaReservation villaReservation) {
        List<VillaReservation> existingReservation = villaReservationRepository.findByVillaidAndFromLessThanEqualAndToGreaterThanEqual(
                villaReservation.getVillaid(),
                villaReservation.getTo(),
                villaReservation.getFrom()
        );
        if (existingReservation.isEmpty()) {
            throw new IllegalStateException("Conflicting reservation for this villa and dates already exists.");
        }
        villaReservationRepository.save(villaReservation);
    }

    public void deleteVillaReservation(Long reservationId) {
        boolean exists = villaReservationRepository.existsById(reservationId);
        if (!exists) {
            throw new IllegalStateException("Villa reservation with ID " + reservationId + " does not exist.");
        }
        villaReservationRepository.deleteById(reservationId);
    }

    @Transactional
    public void updateVillaReservation(Long reservationId, Date from, Date to, Integer price, Integer numOfPersons) {
        Optional<VillaReservation> optionalVillaReservation = villaReservationRepository.findById(reservationId);
        VillaReservation villaReservation = optionalVillaReservation.orElseThrow(() ->
                new IllegalStateException("Villa reservation with ID " + reservationId + " does not exist")
        );

        // Update reservation details if provided and different from the current values
        if (from != null && !from.equals(villaReservation.getFrom())) {
            villaReservation.setFrom(from);
        }
        if (to != null && !to.equals(villaReservation.getTo())) {
            villaReservation.setTo(to);
        }
        if (price != null && !price.equals(villaReservation.getPrice())) {
            villaReservation.setPrice(price);
        }
        if (numOfPersons != null && !numOfPersons.equals(villaReservation.getNumOfPersons())) {
            villaReservation.setNumOfPersons(numOfPersons);
        }
    }
}
