package com.example.demo.Commands.AirlineCommand;

import com.example.demo.Commands.CommandInterface;
import com.example.demo.Service.AirlineService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteAirlineCommand implements CommandInterface {

    private final AirlineService airlineService;
    private final Long airlineId;

    @Override
    public void execute() {
        airlineService.deleteAirline(airlineId);
    }
}
