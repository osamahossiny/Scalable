package com.example.demo.Commands.FlightReservationCommand;

import com.example.demo.Commands.CommandInterface;
import com.example.demo.Model.User;
import com.example.demo.Service.FlightReservationService;
import com.example.demo.Model.AppUser;
import com.example.demo.Model.FlightPackage;
import com.example.demo.Model.PlaneSeat;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateFlightReservationCommand implements CommandInterface {

    private final FlightReservationService flightReservationService;
    private final Long id;
    private final User appUser;
    private final FlightPackage flightPackage;
    private final PlaneSeat planeSeat;
    private final boolean seatChargeable;
    private final boolean extraBaggage;
    private final boolean withInsurance;
    private final int totalPrice;

    @Override
    public void execute() {
        flightReservationService.updateFlightReservation(
                id,
                appUser,
                flightPackage,
                planeSeat,
                seatChargeable,
                extraBaggage,
                withInsurance,
                totalPrice
        );
    }
}
