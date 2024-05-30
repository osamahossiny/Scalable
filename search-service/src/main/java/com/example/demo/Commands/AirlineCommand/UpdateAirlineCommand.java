package com.example.demo.Commands.AirlineCommand;

import com.example.demo.Commands.CommandInterface;
import com.example.demo.Service.AirlineService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateAirlineCommand implements CommandInterface {

    private final AirlineService airlineService;
    private final Long airlineId;
    private final String name;
    private final String IBAN;
    private final String customerServiceNumber;

    @Override
    public void execute() {
        airlineService.updateAirline(airlineId, name, IBAN, customerServiceNumber);
    }
}
