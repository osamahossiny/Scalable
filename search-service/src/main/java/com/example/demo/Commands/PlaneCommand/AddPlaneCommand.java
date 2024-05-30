package com.example.demo.Commands.PlaneCommand;

import com.example.demo.Commands.CommandInterface;
import com.example.demo.Service.PlaneService;
import com.example.demo.Model.Plane;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddPlaneCommand implements CommandInterface {

    private final PlaneService planeService;
    private final Plane plane;

    @Override
    public void execute() {
        planeService.addNewPlane(plane);
    }
}
