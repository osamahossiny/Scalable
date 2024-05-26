package com.example.demo.Service;
import com.example.demo.Repository.PlaneRepository;
import com.example.demo.Model.Airline;
import com.example.demo.Model.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlaneService {
    private  final PlaneRepository planeRepository;

    @Autowired
    public PlaneService(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    public List<Plane> getPlanes(){
        return planeRepository.findAll();
    }

    public void addNewPlane(Plane plane) {
        boolean exists = planeRepository.existsById(plane.getId());
        if (exists) {
            throw new IllegalStateException("Plane with id " + plane.getId() + " already exists.");
        }
        Optional<Plane> planeByName = planeRepository.findPlaneByName(plane.getName());
        if (planeByName.isPresent()){
            throw new IllegalStateException("A plane with this name already exists.");
        }
        planeRepository.save(plane);
    }

    public void deletePlane(Long planeId) {
        boolean exists = planeRepository.existsById(planeId);

        if (!exists) {
            throw new IllegalStateException("Plane with id "+ planeId + " does not exist.");
        }
        planeRepository.deleteById(planeId);
    }

    @Transactional
    public void updatePlane(Long planeId, String name, String type, Airline airline) {

        Plane plane = planeRepository.findById(planeId).orElseThrow(() ->
                new IllegalStateException("plane with id " + planeId + " does not exist")
        );

        if (!plane.getId().equals(planeId)) {
            boolean exists = planeRepository.existsById(planeId);
            if (exists) {
                throw new IllegalStateException("Plane with id "+ planeId + " already exists.");
            }
            Optional<Plane> planeByName = planeRepository.findPlaneByName(plane.getName());
            if (planeByName.isPresent()){
                throw new IllegalStateException("A plane with this name already exists.");
            }
            plane.setId(planeId);
        }
        if (name != null && !name.isEmpty() && !plane.getName().equals(name)) {
            plane.setName(name);
        }
        if (type != null && !type.isEmpty() && !plane.getType().equals(type)) {
            plane.setType(type);
        }
        if (airline != null && !plane.getAirline().getId().equals(airline.getId())) {
            plane.setAirline(airline);
        }
    }
}