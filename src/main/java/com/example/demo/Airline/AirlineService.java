package com.example.demo.Airline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirlineService {

    private  final  AirlineRepository airlineRepository;

    @Autowired
    public AirlineService(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    public List<Airline> getAirlines(){
        return airlineRepository.findAll();
    }

    public void addNewAirline(Airline airline) {
        Optional<Airline> airlineByName = airlineRepository.findAirlineByName(airline.getName());

        if (airlineByName.isPresent()){
            throw new IllegalStateException("An airline with this name already exists.");
        }
        airlineRepository.save(airline);
    }

    public void deleteAirline(Long airlineId) {
        boolean exists = airlineRepository.existsById(airlineId);

        if (!exists) {
            throw new IllegalStateException("Airline with id "+ airlineId + " does not exist.");
        }
        airlineRepository.deleteById(airlineId);
    }
}
