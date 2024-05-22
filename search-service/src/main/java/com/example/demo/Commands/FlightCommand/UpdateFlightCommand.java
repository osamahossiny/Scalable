package com.example.demo.Commands.FlightCommand;

import com.example.demo.Commands.CommandInterface;
import com.example.demo.Service.FlightService;
import com.example.demo.Model.Plane;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateFlightCommand implements CommandInterface {

    private final FlightService flightService;
    private final Long flightId;
    private final String departureLocation;
    private final String arrivalLocation;
    private final String timeOfDep;
    private final String timeOfArrival;
    private final Plane plane;
    private final float distance;
    private final float flightPrice;
    private final float insurancePrice;
    private final float extraBaggagePrice;
    private final String depAirport;
    private final String arrivalAirPort;
    private final String depDate;
    private final String arrivalDate;

    @Override
    public void execute() {
        flightService.updateFlight(
                flightId,
                departureLocation,
                arrivalLocation,
                timeOfDep,
                timeOfArrival,
                plane,
                distance,
                flightPrice,
                insurancePrice,
                extraBaggagePrice,
                depAirport,
                arrivalAirPort,
                depDate,
                arrivalDate
        );
    }
}
