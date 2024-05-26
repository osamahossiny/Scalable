package com.example.demo.Service;
import com.example.demo.Model.*;
import com.example.demo.Repository.FlightReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class FlightReservationService {
    private static final String CACHE_PREFIX = "flightReservation::";
    private  final FlightReservationRepository flightReservationRepository;
    @Autowired
    private RedisTemplate<String, FlightReservation> redisTemplate;

    @Autowired
    public FlightReservationService(FlightReservationRepository flightReservationRepository) {
        this.flightReservationRepository = flightReservationRepository;
    }

    public List<FlightReservation> getFlightReservations(){
        return flightReservationRepository.findAll();
    }
    public FlightReservation getFlightReservationsId(Long id) {
        System.out.println("GET");
        FlightReservation cached = redisTemplate.opsForValue().get(CACHE_PREFIX+ id);
        if (cached != null) {
            System.out.println("CASHED");
            return cached;
        } else {
            System.out.println("DB");
            FlightReservation db = flightReservationRepository.findById(id).orElse(null);
            if (db != null) {
                redisTemplate.opsForValue().set(CACHE_PREFIX + id, db, 10, TimeUnit.MINUTES);
            }
            return db;
        }
    }
    public void addNewFlightReservation(FlightReservation flightReservation) {
        Optional<FlightReservation> flightReservationByFlightReservationId = flightReservationRepository.findById(flightReservation.getId());
        System.out.println(flightReservation);
        if (flightReservationByFlightReservationId.isPresent()){
            throw new IllegalStateException("A Flight Reservation with this id already exists.");
        }

        Optional<User> appUser = flightReservationRepository.findAppUserId(flightReservation.getAppUser().getId());
        if (!appUser.isPresent()){
            throw new IllegalStateException("This appUser does not exist.");
        }

        Optional<FlightPackage> flightPackage = flightReservationRepository.findFlightPackageId(flightReservation.getFlightPackage().getId());
        if (!flightPackage.isPresent()){
            throw new IllegalStateException("This flightPackage does not exist.");
        }

        Optional<PlaneSeat> planeSeat = flightReservationRepository.findPlaneSeatId(flightReservation.getPlaneSeat().getId());
        if (!planeSeat.isPresent()){
            throw new IllegalStateException("This planeSeat does not exist.");
        }


        FlightReservation saved= flightReservationRepository.save(flightReservation);
        redisTemplate.opsForValue().set(CACHE_PREFIX + saved.getId(), saved, 10, TimeUnit.MINUTES);

    }

    public void deleteFlightReservation(Long flightReservationId) {
        boolean exists = flightReservationRepository.existsById(flightReservationId);

        if (!exists) {
            throw new IllegalStateException("Flight Reservation with id "+ flightReservationId + " does not exist.");
        }
        flightReservationRepository.deleteById(flightReservationId);
        redisTemplate.delete(CACHE_PREFIX + flightReservationId);

    }

    @Transactional
    public void updateFlightReservation(Long flightReservationId ,User appUser, FlightPackage flightPackage, PlaneSeat planeSeat, boolean seatChargeable, boolean extraBaggage, boolean withInsurance, int totalPrice) {

        FlightReservation flightReservation = flightReservationRepository.findById(flightReservationId).orElseThrow(() ->
                new IllegalStateException("flight Package with id " + flightReservationId + " does not exist")
        );

        if (!flightReservation.getId().equals(flightReservationId)) {
            Optional<FlightReservation> flightReservationOptional = flightReservationRepository.findById(flightReservationId);
            if (flightReservationOptional.isPresent()){
                throw new IllegalStateException("This id is already used.");
            }
            flightReservation.setId(flightReservationId);
        }


        if (appUser != null && flightReservation.getAppUser()!=appUser) {
            flightReservation.setAppUser(appUser);
        }


        if (flightPackage != null && flightReservation.getFlightPackage()!=flightPackage) {
            flightReservation.setFlightPackage(flightPackage);
        }


        if (planeSeat != null && flightReservation.getPlaneSeat()!=planeSeat) {
            flightReservation.setPlaneSeat(planeSeat);
        }


        if ( flightReservation.isSeatChargeable()!=seatChargeable) {
            flightReservation.setSeatChargeable(seatChargeable);
        }

        if ( flightReservation.isExtraBaggage()!=extraBaggage) {
            flightReservation.setExtraBaggage(extraBaggage);
        }

        if ( flightReservation.isWithInsurance()!=withInsurance) {
            flightReservation.setWithInsurance(withInsurance);
        }

        if (totalPrice != 0 && flightReservation.getTotalPrice()!=totalPrice) {
            flightReservation.setTotalPrice(totalPrice);
        }
        FlightReservation saved=flightReservationRepository.save(flightReservation);
        redisTemplate.opsForValue().set(CACHE_PREFIX + saved.getId(), saved, 10, TimeUnit.MINUTES);
    }

}
