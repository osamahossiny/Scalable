package com.example.demo.Commands.FlightPackageCommand;

import com.example.demo.Commands.CommandInterface;
import com.example.demo.Service.FlightPackageService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteFlightPackageCommand implements CommandInterface {

    private final FlightPackageService flightPackageService;
    private final Long flightPackageId;

    @Override
    public void execute() {
        flightPackageService.deleteFlightPackage(flightPackageId);
    }
}
