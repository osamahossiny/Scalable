package com.example.demo.Commands.PlaneSeatCommand;

import com.example.demo.Commands.CommandInterface;
import com.example.demo.Service.PlaneSeatService;
import com.example.demo.Model.PlaneSeat;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddPlaneSeatCommand implements CommandInterface {

    private final PlaneSeatService planeSeatService;
    private final PlaneSeat planeSeat;

    @Override
    public void execute() {
        planeSeatService.addNewPlaneSeat(planeSeat);
    }
}
