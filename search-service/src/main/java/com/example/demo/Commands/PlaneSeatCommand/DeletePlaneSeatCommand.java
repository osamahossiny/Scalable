package com.example.demo.Commands.PlaneSeatCommand;

import com.example.demo.Commands.CommandInterface;
import com.example.demo.Service.PlaneSeatService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeletePlaneSeatCommand implements CommandInterface {

    private final PlaneSeatService planeSeatService;
    private final Long planeSeatId;

    @Override
    public void execute() {
        planeSeatService.deletePlaneSeat(planeSeatId);
    }
}
