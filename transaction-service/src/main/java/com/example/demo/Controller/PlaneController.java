package com.example.demo.Controller;
import com.example.demo.Service.PlaneService;
import com.example.demo.Model.Airline;
import com.example.demo.Model.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/transaction/plane")
public class PlaneController {
    private final PlaneService planeService;

    @Autowired
    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    @GetMapping
    public List<Plane> getPlane(){
        return this.planeService.getPlanes();
    }

    @PostMapping
    public void registerPlane(@RequestBody Plane plane){
        planeService.addNewPlane(plane);
    }

    @DeleteMapping(path = "{planeId}")
    public void deletePlane(@PathVariable("planeId") Long planeId){
        planeService.deletePlane(planeId);
    }

    @PutMapping(path = "{planeId}")
    public void updatePlane(@PathVariable("planeId") Long planeId, @RequestParam(required = false,name = "name") String name, @RequestParam(required = false, name = "type") String type, @RequestParam(required = false, name = "airline") Airline airline){
        planeService.updatePlane(planeId, name, type, airline);
    }
}