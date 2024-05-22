package com.example.demo.Commands.PlaneSeatCommand;

import com.example.demo.Commands.CommandInterface;
import com.example.demo.Service.PlaneSeatService;
import com.example.demo.Model.Plane;
import com.example.demo.Model.SeatCategory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdatePlaneSeatCommand implements CommandInterface {

    private final PlaneSeatService planeSeatService;
    private final Long planeSeatId;
    private final int seatNumber;
    private final SeatCategory seatCategory;
    private final Plane plane;
    private final int price;

    @Override
    public void execute() {
        planeSeatService.updatePlaneSeat(planeSeatId, seatNumber, seatCategory, plane, price);
    }
}
