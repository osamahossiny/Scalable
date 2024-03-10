package com.example.demo.Villa;

import com.example.demo.Airline.Airline;
import com.example.demo.Airline.AirlineRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class VillaService {
    private final VillaRepository villaRepository;
    public VillaService(VillaRepository villaRepository) {
        this.villaRepository = villaRepository;
    }

    public List<Villa> getVillas(){
        return villaRepository.findAll();
    }

    public void addNewVilla(Villa villa) {
        Optional<Airline> villaByName = villaRepository.findVillaByName(villa.getVillaName());

        if (villaByName.isPresent()){
            throw new IllegalStateException("A Villa with this name already exists.");
        }
        villaRepository.save(villa);
    }

    public void deleteVilla(Long villaId) {
        boolean exists = villaRepository.existsById(villaId);

        if (!exists) {
            throw new IllegalStateException("Villa with id "+ villaId + " does not exist.");
        }
        villaRepository.deleteById(villaId);
    }

    @Transactional
    public void updateVilla(Long villaId, String name, String iban) {

        Villa villa = villaRepository.findById(villaId).orElseThrow(() ->
                new IllegalStateException("villa with id " + villaId + " does not exist")
        );

        if (name != null && !name.isEmpty() && !villa.getVillaName().equals(name)) {
            Optional<Airline> airlineOptional = villaRepository.findVillaByName(name);
            if (airlineOptional.isPresent()){
                throw new IllegalStateException("This name is already used.");
            }
            villa.setVillaName(name);
        }
        if (iban != null && !iban.isEmpty() && !villa.getIBAN().equals(iban)) {
            villa.setIBAN(iban);
        }
    }

}
