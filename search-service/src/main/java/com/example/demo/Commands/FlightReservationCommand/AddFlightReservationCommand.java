package com.example.demo.Commands.FlightReservationCommand;

import com.example.demo.Commands.CommandInterface;
import com.example.demo.Service.FlightReservationService;
import com.example.demo.Model.FlightReservation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddFlightReservationCommand implements CommandInterface {

    private final FlightReservationService flightReservationService;
    private final FlightReservation flightReservation;

    @Override
    public void execute() {
        flightReservationService.addNewFlightReservation(flightReservation);
    }
}
