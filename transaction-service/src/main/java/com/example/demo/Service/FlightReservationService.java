package com.example.demo.Service;


import com.example.demo.Repository.FlightReservationRepository;
import com.example.demo.model.AppUser;
import com.example.demo.model.FlightPackage;
import com.example.demo.model.FlightReservation;
import com.example.demo.model.PlaneSeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FlightReservationService {

    private  final FlightReservationRepository flightReservationRepository;

    @Autowired
    public FlightReservationService(FlightReservationRepository flightReservationRepository) {
        this.flightReservationRepository = flightReservationRepository;
    }

    public List<FlightReservation> getFlightReservations(){
        return flightReservationRepository.findAll();
    }

    public void addNewFlightReservation(FlightReservation flightReservation) {
        Optional<FlightReservation> flightReservationByFlightReservationId = flightReservationRepository.findById(flightReservation.getId());
        System.out.println(flightReservation);
        if (flightReservationByFlightReservationId.isPresent()){
            throw new IllegalStateException("A Flight Reservation with this id already exists.");
        }

        Optional<AppUser> appUser = flightReservationRepository.findAppUserId(flightReservation.getAppUser().getId());
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


        flightReservationRepository.save(flightReservation);
    }

    public void deleteFlightReservation(Long flightReservationId) {
        boolean exists = flightReservationRepository.existsById(flightReservationId);

        if (!exists) {
            throw new IllegalStateException("Flight Reservation with id "+ flightReservationId + " does not exist.");
        }
        flightReservationRepository.deleteById(flightReservationId);
    }

    @Transactional
    public void updateFlightReservation(Long flightReservationId , Long user_id, Long package_id, Long seat_id, boolean seatChargeable, boolean extraBaggage, boolean withInsurance, FlightReservation.PaymentMethod paymentMethod) {

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

        if(user_id!=null && !Objects.equals(flightReservation.getAppUser().getId(), user_id)){
            flightReservation.setAppUser(flightReservationRepository.findAppUserId(user_id).orElseThrow(() ->
                    new IllegalStateException("user with id " + user_id + " does not exist")
            ));
        }
        int newPrice = flightReservation.getTotalPrice();
        boolean priceChanged = false;
        if(package_id!=null && !flightReservation.getFlightPackage().getId().equals(package_id)){
            if(flightReservation.getFlightPackage()!=null){
                newPrice -= flightReservation.getFlightPackage().getPrice();
            }
            FlightPackage flightPackage = flightReservationRepository.findFlightPackageId(package_id).orElseThrow(() ->
                    new IllegalStateException("flight package with id " + package_id + " does not exist")
            );
            newPrice+= flightPackage.getPrice();
            priceChanged = true;
            flightReservation.setFlightPackage(flightPackage);
        }

        if(seat_id!=null && !flightReservation.getPlaneSeat().getId().equals(seat_id)){
            System.out.println("HEEEEEEREEEEE seat id = " + seat_id);
            if(flightReservation.getPlaneSeat()!=null){
                System.out.println("not null");
                newPrice-= flightReservation.getPlaneSeat().getPrice();
            }
            PlaneSeat planeSeat = flightReservationRepository.findPlaneSeatId(seat_id).orElseThrow(() ->
                    new IllegalStateException("plane seat with id " + seat_id + " does not exist")
            );
            newPrice+= planeSeat.getPrice();
            priceChanged = true;
            flightReservation.setPlaneSeat(planeSeat);
        }

        if ( flightReservation.isSeatChargeable()!=seatChargeable) {
            flightReservation.setSeatChargeable(seatChargeable);
            if (seatChargeable) {
                newPrice += flightReservation.getSeatChargeablePrice();
            }
            else{
                newPrice -= flightReservation.getSeatChargeablePrice();
            }
            priceChanged = true;
        }

        if ( flightReservation.isExtraBaggage()!=extraBaggage) {
            flightReservation.setExtraBaggage(extraBaggage);
            if(extraBaggage){
                newPrice += flightReservation.getExtraBaggagePrice();
            }
            else{
                newPrice -= flightReservation.getExtraBaggagePrice();
            }
            priceChanged = true;
        }

        if ( flightReservation.isWithInsurance()!=withInsurance) {
            flightReservation.setWithInsurance(withInsurance);
            if(withInsurance){
                newPrice += flightReservation.getWithInsurancePrice();
                 }
            else{
                newPrice -= flightReservation.getWithInsurancePrice();
                }
            priceChanged = true;
        }

        if(priceChanged){
            flightReservation.setTotalPrice(newPrice);
        }

        if(paymentMethod != null && flightReservation.getPaymentMethod() != paymentMethod){
            flightReservation.setPaymentMethod(paymentMethod);
        }


    }

}
