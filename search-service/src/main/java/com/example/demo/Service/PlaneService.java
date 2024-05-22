package com.example.demo.Service;
import com.example.demo.Repository.PlaneRepository;
import com.example.demo.Model.Airline;
import com.example.demo.Model.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class PlaneService {
    private static final String CACHE_PREFIX = "plane::";
    private  final PlaneRepository planeRepository;
    @Autowired
    private RedisTemplate<String, Plane> redisTemplate;
    @Autowired
    public PlaneService(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    public List<Plane> getPlanes(){
        return planeRepository.findAll();
    }
    public Plane getPlaneId(Long id) {
        System.out.println("GET");
        Plane cached = redisTemplate.opsForValue().get(CACHE_PREFIX+ id);
        if (cached != null) {
            System.out.println("CASHED");
            return cached;
        } else {
            System.out.println("DB");
            Plane db = planeRepository.findById(id).orElse(null);
            if (db != null) {
                redisTemplate.opsForValue().set(CACHE_PREFIX + id, db, 10, TimeUnit.MINUTES);
            }
            return db;
        }
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
        Plane saved=planeRepository.save(plane);
        redisTemplate.opsForValue().set(CACHE_PREFIX + saved.getId(), saved, 10, TimeUnit.MINUTES);

    }

    public void deletePlane(Long planeId) {
        boolean exists = planeRepository.existsById(planeId);

        if (!exists) {
            throw new IllegalStateException("Plane with id "+ planeId + " does not exist.");
        }
        planeRepository.deleteById(planeId);
        redisTemplate.delete(CACHE_PREFIX + planeId);
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
        Plane saved=planeRepository.save(plane);
        redisTemplate.opsForValue().set(CACHE_PREFIX + saved.getId(), saved, 10, TimeUnit.MINUTES);

    }
}
