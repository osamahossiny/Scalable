package com.example.demo.Service;


import com.example.demo.Repository.FlightReservationRepository;
import com.example.demo.model.AppUser;
import com.example.demo.model.Flight;
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
        //Long userId, Long reservationId, LocalDateTime transactionDateTime, FlightReservation.PaymentMethod paymentMethod, BigDecimal transactionAmount, Status status
        if(flightReservation.getAppUser()==null){
            throw new IllegalStateException("AppUser cannot be null.");
        }
        Optional<AppUser> appUser = flightReservationRepository.findAppUserId(flightReservation.getAppUser().getId());
        if (!appUser.isPresent()){
            throw new IllegalStateException("This appUser does not exist.");
        }

        if(flightReservation.getFlightPackage()==null){
            throw new IllegalStateException("FlightPackage cannot be null.");
        }
        Optional<FlightPackage> flightPackage = flightReservationRepository.findFlightPackageId(flightReservation.getFlightPackage().getId());
        if (!flightPackage.isPresent()){
            throw new IllegalStateException("This flightPackage does not exist.");
        }
        Flight flight = flightPackage.get().getFlight();
        if(flightReservation.getPlaneSeat()==null){
            throw new IllegalStateException("PlaneSeat cannot be null.");
        }
        long seatId = flightReservation.getPlaneSeat().getId();
        System.out.println(seatId);
        System.out.println("SANAD");
        Optional<PlaneSeat> planeSeat = flightReservationRepository.findPlaneSeatWithId(seatId);
        if (!planeSeat.isPresent()){
            throw new IllegalStateException("This planeSeat does not exist.");
        }
        Optional<FlightReservation> reservationSameUser = flightReservationRepository.findFlightReservation(flightReservation.getAppUser().getId(), flightReservation.getPlaneSeat().getId(), flight.getFlightId());


        if(reservationSameUser.isPresent()){
            System.out.println("reservationSameUser: { "+reservationSameUser.get().getAppUser().getId()+", "+reservationSameUser.get().getPlaneSeat().getId()+", "+reservationSameUser.get().getFlightPackage().getFlight().getFlightId()+" }");
            System.out.println("flightReservation: { "+flightReservation.getAppUser().getId()+", "+flightReservation.getPlaneSeat().getId()+", "+flight.getFlightId()+" }");
            throw new IllegalStateException("This user already has a reservation with this seat and package.");
        }

        Optional<FlightReservation> reservationSameSeat = flightReservationRepository.findReservedSeat(flightReservation.getPlaneSeat().getId(), flight.getFlightId());
        //check if the seat is already reserved
        if(reservationSameSeat.isPresent()){
            System.out.println("HEERREEEEEEEEE");
            System.out.println(flightReservationRepository.findReservedSeat(flightReservation.getPlaneSeat().getId(), flight.getFlightId()));

            throw new IllegalStateException("This seat is already reserved.");
        }
        if(!Objects.equals(flight.getPlane().getId(), planeSeat.get().getPlane().getId())){
            throw new IllegalStateException("This seat is not in the same plane as the flight.");
        }

        float totalPrice = flightPackage.get().getPrice()+planeSeat.get().getPrice();
        if(flightReservation.isSeatChargeable()){
            totalPrice+= flightReservation.getSeatChargeablePrice();
        }
        if(flightReservation.isExtraBaggage()){
            totalPrice+= flight.getExtraBaggagePrice();
        }
        if(flightReservation.isWithInsurance()){
            totalPrice+= flight.getInsurancePrice();
        }
        flightReservation.setTotalPrice(totalPrice);
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
        float newPrice = flightReservation.getTotalPrice();
        boolean priceChanged = false;
        if(package_id!=null && !flightReservation.getFlightPackage().getId().equals(package_id)){
            FlightPackage flightPackage = flightReservationRepository.findFlightPackageId(package_id).orElseThrow(() ->
                    new IllegalStateException("flight package with id " + package_id + " does not exist")
            );
            if(flightReservation.getFlightPackage()!=null){
                newPrice -= flightReservation.getFlightPackage().getPrice();
            }
            newPrice+= flightPackage.getPrice();
            priceChanged = true;
            flightReservation.setFlightPackage(flightPackage);
        }

        Flight flight = flightReservation.getFlightPackage().getFlight();

        if(seat_id!=null && !flightReservation.getPlaneSeat().getId().equals(seat_id)){
            PlaneSeat planeSeat = flightReservationRepository.findPlaneSeatWithId(seat_id).orElseThrow(() ->
                    new IllegalStateException("plane seat with id " + seat_id + " does not exist")
            );
            flight = flightReservation.getFlightPackage().getFlight();

            if(flightReservationRepository.findReservedSeat(planeSeat.getId(), flight.getFlightId()).isPresent()){
                throw new IllegalStateException("This seat is already reserved.");
            }
            if(flightReservation.getPlaneSeat()!=null){
                newPrice-= flightReservation.getPlaneSeat().getPrice();
            }
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
                newPrice += flight.getExtraBaggagePrice();
            }
            else{
                newPrice -= flight.getExtraBaggagePrice();
            }
            priceChanged = true;
        }

        if ( flightReservation.isWithInsurance()!=withInsurance) {
            flightReservation.setWithInsurance(withInsurance);
            if(withInsurance){
                newPrice += flight.getInsurancePrice();
                 }
            else{
                newPrice -= flight.getInsurancePrice();
                }
            priceChanged = true;
        }

        if(priceChanged){
            flightReservation.setTotalPrice(newPrice);
        }

        if(paymentMethod != null && flightReservation.getPaymentMethod() != paymentMethod){
            flightReservation.setPaymentMethod(paymentMethod);
        }
        Optional<FlightReservation> reservationSameUser = flightReservationRepository.findFlightReservation(flightReservation.getAppUser().getId(), flightReservation.getPlaneSeat().getId(), flight.getFlightId());
        if(reservationSameUser.isPresent()){
            System.out.println("reservationSameUser: { "+reservationSameUser.get().getAppUser().getId()+", "+reservationSameUser.get().getPlaneSeat().getId()+", "+reservationSameUser.get().getFlightPackage().getFlight().getFlightId()+" }");
            System.out.println("flightReservation: { "+flightReservation.getAppUser().getId()+", "+flightReservation.getPlaneSeat().getId()+", "+flight.getFlightId()+" }");
            throw new IllegalStateException("This user already has a reservation with this seat and package.");
        }
        if(!Objects.equals(flight.getPlane().getId(), flightReservation.getPlaneSeat().getPlane().getId())){
            throw new IllegalStateException("This seat is not in the same plane as the flight.");
        }


    }

}
