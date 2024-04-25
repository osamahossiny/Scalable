package com.example.demo.Commands.FlightReservationCommand;

import com.example.demo.Commands.CommandInterface;
import com.example.demo.Service.FlightReservationService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteFlightReservationCommand implements CommandInterface {

    private final FlightReservationService flightReservationService;
    private final Long flightReservationId;

    @Override
    public void execute() {
        flightReservationService.deleteFlightReservation(flightReservationId);
    }
}
