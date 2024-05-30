package com.example.demo.Service;

import com.example.demo.Repository.FlightPackageRepository;
import com.example.demo.Model.Flight;
import com.example.demo.Model.FlightPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class  FlightPackageService {
    private  final FlightPackageRepository flightPackageRepository;

    @Autowired
    public FlightPackageService(FlightPackageRepository flightPackageRepository) {
        this.flightPackageRepository = flightPackageRepository;
    }

    public List<FlightPackage> getFlightPackages(){
        return flightPackageRepository.findAll();
    }

    public void addNewFlightPackage(FlightPackage flightPackage) {
        Optional<FlightPackage> flightPackageByFlightPackageId = flightPackageRepository.findById(flightPackage.getId());
        System.out.println(flightPackage);
        if (flightPackageByFlightPackageId.isPresent()){
            throw new IllegalStateException("A Flight Package with this id already exists.");
        }

        /*
        Optional<Flight> flight = flightPackageRepository.findFlightId(flightPackage.getFlight().getFlightId());
        if (!flight.isPresent()){
            throw new IllegalStateException("This flight does not exist.");
        }
        */
        flightPackageRepository.save(flightPackage);
    }

    public void deleteFlightPackage(Long flightPackageId) {
        boolean exists = flightPackageRepository.existsById(flightPackageId);

        if (!exists) {
            throw new IllegalStateException("Flight Package with id "+ flightPackageId + " does not exist.");
        }
        flightPackageRepository.deleteById(flightPackageId);
    }

    @Transactional
    public void updateFlightPackage(Long flightPackageId, Flight flight, int weightCabin, int weightCheckIn, int cancellationFee, int dateChangeFee, String mealInfo, boolean expressCheckIn, int price) {

        FlightPackage flightPackage = flightPackageRepository.findById(flightPackageId).orElseThrow(() ->
                new IllegalStateException("flight Package with id " + flightPackageId + " does not exist")
        );

        if (!flightPackage.getId().equals(flightPackageId)) {
            Optional<FlightPackage> flightPackageOptional = flightPackageRepository.findById(flightPackageId);
            if (flightPackageOptional.isPresent()){
                throw new IllegalStateException("This id is already used.");
            }
            flightPackage.setId(flightPackageId);
        }



        if (flight != null && flightPackage.getFlight()!=flight) {
            flightPackage.setFlight(flight);
        }

        if (weightCabin != 0 && flightPackage.getWeightCabin()!=weightCabin) {
            flightPackage.setWeightCabin(weightCabin);
        }
        if (weightCheckIn != 0 && flightPackage.getWeightCheckIn()!=weightCheckIn) {
            flightPackage.setWeightCheckIn(weightCheckIn);
        }
        if (cancellationFee != 0 && flightPackage.getCancellationFee()!=cancellationFee) {
            flightPackage.setCancellationFee(cancellationFee);
        }
        if (dateChangeFee != 0 && flightPackage.getDateChangeFee()!=dateChangeFee) {
            flightPackage.setDateChangeFee(dateChangeFee);
        }

        if (mealInfo != null&& !mealInfo.isEmpty() && !flightPackage.getMealInfo().equals(mealInfo)) {
            flightPackage.setMealInfo(mealInfo);
        }



        if ( flightPackage.isExpressCheckIn()!=expressCheckIn) {
            flightPackage.setExpressCheckIn(expressCheckIn);
        }


        if (price != 0 && flightPackage.getPrice()!=price) {
            flightPackage.setPrice(price);
        }


    }

}
