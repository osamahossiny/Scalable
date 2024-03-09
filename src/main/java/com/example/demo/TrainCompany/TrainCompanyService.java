package com.example.demo.TrainCompany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrainCompanyService {
    private final TrainCompanyRepository trainCompanyRepository;
    @Autowired
    public TrainCompanyService(TrainCompanyRepository trainCompanyRepository) {
        this.trainCompanyRepository = trainCompanyRepository;
    }
    public List<TrainCompany> getTrainCompanies() {
        return trainCompanyRepository.findAll();
    }
    public void addNewTrainCompany(TrainCompany trainCompany) {
        trainCompanyRepository.findTrainCompanyByName(trainCompany.getCompanyName())
                .ifPresent(trainCompany1 -> {
                    throw new IllegalStateException("Train Company already exists");
                });
        trainCompanyRepository.save(trainCompany);
    }
    public void deleteTrainCompany(Long trainCompanyId) {
        boolean exists = trainCompanyRepository.existsById(trainCompanyId);
        if (!exists) {
            throw new IllegalStateException("Train Company with id " + trainCompanyId + " does not exist");
        }
        trainCompanyRepository.deleteById(trainCompanyId);
    }
    @Transactional
    public void updateTrainCompany(Long trainCompanyId, String companyName, String companyIBAN, String customerServiceNumber) {
        TrainCompany trainCompany = trainCompanyRepository.findById(trainCompanyId)
                .orElseThrow(() -> new IllegalStateException(
                        "Train Company with id " + trainCompanyId + " does not exist"
                ));
        if (companyName != null && !companyName.isEmpty() && !trainCompany.getCompanyName().equals(companyName)) {
            trainCompany.setCompanyName(companyName);
        }

        if (companyIBAN != null && !companyIBAN.isEmpty() && !trainCompany.getCompanyIBAN().equals(companyIBAN)) {
            trainCompany.setCompanyIBAN(companyIBAN);
        }
        if (customerServiceNumber != null && !customerServiceNumber.isEmpty() && !trainCompany.getCustomerServiceNumber().equals(customerServiceNumber)) {
            trainCompany.setCustomerServiceNumber(customerServiceNumber);
        }
    }
}