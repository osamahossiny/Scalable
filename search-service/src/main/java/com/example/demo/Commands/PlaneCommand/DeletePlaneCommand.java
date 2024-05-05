package com.example.demo.Commands.PlaneCommand;

import com.example.demo.Commands.CommandInterface;
import com.example.demo.Service.PlaneService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeletePlaneCommand implements CommandInterface {

    private final PlaneService planeService;
    private final Long planeId;

    @Override
    public void execute() {
        planeService.deletePlane(planeId);
    }
}
