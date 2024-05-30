package com.example.demo.Commands.PlaneCommand;

import com.example.demo.Commands.CommandInterface;
import com.example.demo.Service.PlaneService;
import com.example.demo.Model.Airline;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdatePlaneCommand implements CommandInterface {

    private final PlaneService planeService;
    private final Long planeId;
    private final String name;
    private final String type;
    private final Airline airline;

    @Override
    public void execute() {
        planeService.updatePlane(planeId, name, type, airline);
    }
}
