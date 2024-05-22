package com.example.demo.Commands;

import com.example.demo.Model.Airline;
import com.example.demo.Service.AirlineService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddAirlineCommand implements CommandInterface{

    private final AirlineService airlineService;
    private final Airline airline;

    @Override
    public void execute() {
        airlineService.addNewAirline(airline);
    }
}
