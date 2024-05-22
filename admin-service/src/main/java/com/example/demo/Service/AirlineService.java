package com.example.demo.Service;

import com.example.demo.Repository.AirlineRepository;
import com.example.demo.Model.Airline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AirlineService {

    private  final AirlineRepository airlineRepository;

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

    @Transactional
    public void updateAirline(Long airlineId, String name, String iban, String customerServiceNumber) {

        Airline airline = airlineRepository.findById(airlineId).orElseThrow(() ->
            new IllegalStateException("airline with id " + airlineId + " does not exist")
        );

        if (name != null && !name.isEmpty() && !airline.getName().equals(name)) {
            Optional<Airline> airlineOptional = airlineRepository.findAirlineByName(name);
            if (airlineOptional.isPresent()){
                throw new IllegalStateException("This name is already used.");
            }
            airline.setName(name);
        }
        if (iban != null && !iban.isEmpty() && !airline.getIBAN().equals(iban)) {
            airline.setIBAN(iban);
        }
        if (customerServiceNumber != null && !customerServiceNumber.isEmpty() && !airline.getCustomerServiceNumber().equals(customerServiceNumber)) {
            airline.setCustomerServiceNumber(customerServiceNumber);
        }
    }
}
