package com.example.demo.TrainCompany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/trainCompany")
public class TrainCompanyController {
    private final TrainCompanyService trainCompanyService;

    @Autowired
    public TrainCompanyController(TrainCompanyService trainCompanyService) {
        this.trainCompanyService = trainCompanyService;
    }

    @GetMapping
    public List<TrainCompany> getTrainCompanies() {
        return this.trainCompanyService.getTrainCompanies();
    }

    @PostMapping
    public void registerTrainCompany(@RequestBody TrainCompany trainCompany) {
        trainCompanyService.addNewTrainCompany(trainCompany);
    }

    @DeleteMapping(path = "{trainCompanyId}")
    public void deleteTrainCompany(@PathVariable("trainCompanyId") Long trainCompanyId) {
        trainCompanyService.deleteTrainCompany(trainCompanyId);
    }

    @PutMapping(path = "{trainCompanyId}")
    public void updateTrainCompany(@PathVariable("trainCompanyId") Long trainCompanyId, @RequestParam(required = false, name = "companyName") String companyName, @RequestParam(required = false, name = "companyIBAN") String companyIBAN, @RequestParam(required = false, name = "customerServiceNumber") String customerServiceNumber) {
        trainCompanyService.updateTrainCompany(trainCompanyId, companyName, companyIBAN, customerServiceNumber);
    }
}
