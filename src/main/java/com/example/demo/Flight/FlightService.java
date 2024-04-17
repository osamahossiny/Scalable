package com.example.demo.Flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private  final  FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getFlights(){
        return flightRepository.findAll();
    }

    public void addNewFlight(Flight flight) {
        Optional<Flight> flightByFlightId = flightRepository.findFlightByFlightId(flight.getFlightId());
        System.out.println(flight);
        if (flightByFlightId.isPresent()){
            throw new IllegalStateException("A Flight with this id already exists.");
        }
        flightRepository.save(flight);
    }

    public void deleteFlight(Long flightId) {
        boolean exists = flightRepository.existsById(flightId);

        if (!exists) {
            throw new IllegalStateException("Flight with id "+ flightId + " does not exist.");
        }
        flightRepository.deleteById(flightId);
    }

    @Transactional
    public void updateFlight(Long flightId, String departureLocation, String arrivalLocation, String timeOfDep, String timeOfArrival, long planeId, float distance, float flightPrice, float insurancePrice, float extraBaggagePrice, String depAirport, String arrivalAirPort, String depDate, String arrivalDate) {

        Flight flight = flightRepository.findById(flightId).orElseThrow(() ->
                new IllegalStateException("flight with id " + flightId + " does not exist")
        );

        if (!flight.getFlightId().equals(flightId)) {
            Optional<Flight> flightOptional = flightRepository.findFlightByFlightId(flightId);
            if (flightOptional.isPresent()){
                throw new IllegalStateException("This id is already used.");
            }
            flight.setFlightId(flightId);
        }
        if (departureLocation != null && !departureLocation.isEmpty() && !flight.getDepartureLocation().equals(departureLocation)) {
            flight.setDepartureLocation(departureLocation);
        }
        if (arrivalLocation != null && !arrivalLocation.isEmpty() && !flight.getArrivalLocation().equals(arrivalLocation)) {
            flight.setArrivalLocation(arrivalLocation);
        }
        if (timeOfDep != null&& !timeOfDep.isEmpty() && !flight.getTimeOfDep().equals(timeOfDep)) {
            flight.setTimeOfDep(timeOfDep);
        }
        if (timeOfArrival != null && !timeOfArrival.isEmpty() && !flight.getTimeOfArrival().equals(timeOfArrival)) {
            flight.setTimeOfArrival(timeOfArrival);
        }
        if (planeId != 0 && flight.getPlaneId()!=planeId) {
            flight.setPlaneId(planeId);
        }
        if (distance != 0 && flight.getDistance()!=distance) {
            flight.setDistance(distance);
        }

        if (insurancePrice != 0 && flight.getInsurancePrice()!=insurancePrice) {
            flight.setInsurancePrice(insurancePrice);
        }
        if (flightPrice != 0 && flight.getFlightPrice()!=flightPrice) {
            flight.setFlightPrice(flightPrice);
        }
        if (extraBaggagePrice != 0 && flight.getExtraBaggagePrice()!=extraBaggagePrice) {
            flight.setExtraBaggagePrice(extraBaggagePrice);
        }
        if (depAirport != null && !depAirport.isEmpty() && !flight.getDepAirport().equals(depAirport)) {
            flight.setDepAirport(depAirport);
        }
        if (arrivalAirPort != null && !arrivalAirPort.isEmpty() && !flight.getArrivalAirPort().equals(arrivalAirPort)) {
            flight.setArrivalAirPort(arrivalAirPort);
        }
        if (depDate != null && !arrivalDate.isEmpty()&& !flight.getDepDate().equals(depDate)) {
            flight.setDepDate(depDate);
        }
        if (arrivalDate != null && !arrivalDate.isEmpty()&& !flight.getArrivalDate().equals(arrivalDate)) {
            flight.setArrivalDate(arrivalDate);
        }
    }
}
