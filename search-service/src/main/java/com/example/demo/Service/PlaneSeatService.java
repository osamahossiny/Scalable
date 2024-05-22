package com.example.demo.Service;
import com.example.demo.Repository.PlaneSeatRepository;
import com.example.demo.Model.Plane;
import com.example.demo.Model.PlaneSeat;
import com.example.demo.Model.SeatCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class PlaneSeatService {
    private static final String CACHE_PREFIX = "planeSeat::";
    private  final PlaneSeatRepository planeSeatRepository;
    @Autowired
    private RedisTemplate<String, PlaneSeat> redisTemplate;
    @Autowired
    public PlaneSeatService(PlaneSeatRepository planeSeatRepository) {
        this.planeSeatRepository = planeSeatRepository;
    }

    public List<PlaneSeat> getPlaneSeats(){
        return planeSeatRepository.findAll();
    }
    public PlaneSeat getPlaneSeatId(Long id) {
        System.out.println("GET");
        PlaneSeat cached = redisTemplate.opsForValue().get(CACHE_PREFIX+ id);
        if (cached != null) {
            System.out.println("CASHED");
            return cached;
        } else {
            System.out.println("DB");
            PlaneSeat db = planeSeatRepository.findById(id).orElse(null);
            if (db != null) {
                redisTemplate.opsForValue().set(CACHE_PREFIX + id, db, 10, TimeUnit.MINUTES);
            }
            return db;
        }
    }
    public void addNewPlaneSeat(PlaneSeat planeSeat) {
        boolean exists = planeSeatRepository.existsById(planeSeat.getId());
        if (exists) {
            throw new IllegalStateException("Plane seat with id " + planeSeat.getId() + " already exists.");
        }

        PlaneSeat saved =planeSeatRepository.save(planeSeat);
        redisTemplate.opsForValue().set(CACHE_PREFIX + saved.getId(), saved, 10, TimeUnit.MINUTES);
    }

    public void deletePlaneSeat(Long planeSeatId) {
        boolean exists = planeSeatRepository.existsById(planeSeatId);

        if (!exists) {
            throw new IllegalStateException("Plane seat with id "+ planeSeatId + " does not exist.");
        }
        planeSeatRepository.deleteById(planeSeatId);
        redisTemplate.delete(CACHE_PREFIX + planeSeatId);

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
        PlaneSeat saved=planeSeatRepository.save(planeSeat);
        redisTemplate.opsForValue().set(CACHE_PREFIX + saved.getId(), saved, 10, TimeUnit.MINUTES);
    }
}
