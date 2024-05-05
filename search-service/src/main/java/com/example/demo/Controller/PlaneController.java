package com.example.demo.Controller;
import com.example.demo.Service.PlaneService;
import com.example.demo.model.Airline;
import com.example.demo.model.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Commands.PlaneCommand.AddPlaneCommand;
import com.example.demo.Commands.PlaneCommand.DeletePlaneCommand;
import com.example.demo.Commands.PlaneCommand.UpdatePlaneCommand;


import java.util.List;

@RestController
@RequestMapping(path = "api/v1/plane")
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
    /*
    @PostMapping
    public void registerPlane(@RequestBody Plane plane){
        planeService.addNewPlane(plane);
    }

     */
    @PostMapping
    public void registerPlane(@RequestBody Plane plane){
        AddPlaneCommand addPlaneCommand = new AddPlaneCommand(planeService, plane);
        addPlaneCommand.execute();
    }

    /*
    @DeleteMapping(path = "{planeId}")
    public void deletePlane(@PathVariable("planeId") Long planeId){
        planeService.deletePlane(planeId);
    }

     */
    @DeleteMapping(path = "{planeId}")
    public void deletePlane(@PathVariable("planeId") Long planeId){
        DeletePlaneCommand deletePlaneCommand = new DeletePlaneCommand(planeService, planeId);
        deletePlaneCommand.execute();
    }
    /*
    @PutMapping(path = "{planeId}")
    public void updatePlane(@PathVariable("planeId") Long planeId, @RequestParam(required = false,name = "name") String name, @RequestParam(required = false, name = "type") String type, @RequestParam(required = false, name = "airline") Airline airline){
        planeService.updatePlane(planeId, name, type, airline);
    }

     */
    @PutMapping(path = "{planeId}")
    public void updatePlane(
            @PathVariable("planeId") Long planeId,
            @RequestParam(required = false, name = "name") String name,
            @RequestParam(required = false, name = "type") String type,
            @RequestParam(required = false, name = "airline") Airline airline
    ){
        UpdatePlaneCommand updatePlaneCommand = new UpdatePlaneCommand(planeService, planeId, name, type, airline);
        updatePlaneCommand.execute();
    }


}
