package com.example.demo.Commands.FlightPackageCommand;

import com.example.demo.Commands.CommandInterface;
import com.example.demo.Service.FlightPackageService;
import com.example.demo.Model.FlightPackage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddFlightPackageCommand implements CommandInterface {

    private final FlightPackageService flightPackageService;
    private final FlightPackage flightPackage;

    @Override
    public void execute() {
        flightPackageService.addNewFlightPackage(flightPackage);
    }
}
