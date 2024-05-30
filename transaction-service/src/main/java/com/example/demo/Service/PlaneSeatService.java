package com.example.demo.Service;
import com.example.demo.Repository.PlaneSeatRepository;
import com.example.demo.Model.Plane;
import com.example.demo.Model.PlaneSeat;
import com.example.demo.Model.SeatCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlaneSeatService {
    private  final PlaneSeatRepository planeSeatRepository;

    @Autowired
    public PlaneSeatService(PlaneSeatRepository planeSeatRepository) {
        this.planeSeatRepository = planeSeatRepository;
    }

    public List<PlaneSeat> getPlaneSeats(){
        return planeSeatRepository.findAll();
    }

    public void addNewPlaneSeat(PlaneSeat planeSeat) {
        boolean exists = planeSeatRepository.existsById(planeSeat.getId());
        if (exists) {
            throw new IllegalStateException("Plane seat with id " + planeSeat.getId() + " already exists.");
        }

        planeSeatRepository.save(planeSeat);
    }

    public void deletePlaneSeat(Long planeSeatId) {
        boolean exists = planeSeatRepository.existsById(planeSeatId);

        if (!exists) {
            throw new IllegalStateException("Plane seat with id "+ planeSeatId + " does not exist.");
        }
        planeSeatRepository.deleteById(planeSeatId);
    }

    @Transactional
    public void updatePlaneSeat(Long id, int seatNumber, SeatCategory seatCategory, Plane plane, int price) {

        PlaneSeat planeSeat = planeSeatRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("plane seat with id " + id + " does not exist")
        );

        if (!planeSeat.getId().equals(id)) {
            planeSeat.setId(id);
        }
        if (seatNumber != 0 && planeSeat.getSeatNumber()!=seatNumber) {
            planeSeat.setSeatNumber(seatNumber);
        }
        if (price != 0 && planeSeat.getPrice()!=price) {
            planeSeat.setPrice(price);
        }
        if (seatCategory != null  && !planeSeat.getSeatCategory().equals(seatCategory)) {
            planeSeat.setSeatCategory(seatCategory);
        }
        if (plane != null && !planeSeat.getPlane().getId().equals(plane.getId())) {
            planeSeat.setPlane(plane);
        }
    }
}
