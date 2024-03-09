package com.example.demo.TrainTicket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/trainTicket")
public class TrainTicketController {

        private final TrainTicketService trainTicketService;

        @Autowired
        public TrainTicketController(TrainTicketService trainTicketService) {
            this.trainTicketService = trainTicketService;
        }

        @GetMapping
        public List<TrainTicket> getTrainTickets(){
            return this.trainTicketService.getTrainTickets();
        }

        @PostMapping
        public void registerTrainTicket(@RequestBody TrainTicket trainTicket){
            trainTicketService.addNewTrainTicket(trainTicket);
        }

        @DeleteMapping(path = "{trainTicketId}")
        public void deleteTrainTicket(@PathVariable("trainTicketId") Long trainTicketId){
            trainTicketService.deleteTrainTicket(trainTicketId);
        }

        @PutMapping(path = "{trainTicketId}")
        public void updateTrainTicket(@PathVariable("trainTicketId") Long trainTicketId, @RequestParam(required = false,name = "UserId") Long UserId, @RequestParam(required = false, name = "TrainLinePlanId") Long TrainLinePlanId, @RequestParam(required = false, name = "price") int price, @RequestParam(required = false, name = "withInsurance") boolean withInsurance){
            trainTicketService.updateTrainTicket(trainTicketId, UserId, TrainLinePlanId, price, withInsurance);
        }
}
