package com.example.demo.Commands.FlightCommand;

import com.example.demo.Commands.CommandInterface;
import com.example.demo.Service.FlightService;
import com.example.demo.model.Flight;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddFlightCommand implements CommandInterface {

    private final FlightService flightService;
    private final Flight flight;

    @Override
    public void execute() {
        flightService.addNewFlight(flight);
    }
}
