package com.example.demo.Commands.FlightPackageCommand;

import com.example.demo.Commands.CommandInterface;
import com.example.demo.Service.FlightPackageService;
import com.example.demo.Model.Flight;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateFlightPackageCommand implements CommandInterface {

    private final FlightPackageService flightPackageService;
    private final Long flightPackageId;
    private final Flight flight;
    private final int weightCabin;
    private final int weightCheckIn;
    private final int cancellationFee;
    private final int dateChangeFee;
    private final String mealInfo;
    private final boolean expressCheckIn;
    private final int price;

    @Override
    public void execute() {
        flightPackageService.updateFlightPackage(
                flightPackageId,flight,weightCabin,weightCheckIn,cancellationFee,dateChangeFee,mealInfo,expressCheckIn,price
        );
    }
}
