package com.example.demo.Commands.FlightCommand;

import com.example.demo.Commands.CommandInterface;
import com.example.demo.Service.FlightService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteFlightCommand implements CommandInterface {

    private final FlightService flightService;
    private final Long flightId;

    @Override
    public void execute() {
        flightService.deleteFlight(flightId);
    }
}
