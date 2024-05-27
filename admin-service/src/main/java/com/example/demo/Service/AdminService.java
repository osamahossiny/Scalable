package com.example.demo.Service;

import com.example.demo.Model.Flight;
import com.example.demo.Model.Refund;
import com.example.demo.Model.Promotion;
import com.example.demo.Model.Complaints;
import com.example.demo.Repository.RefundRepository;
import com.example.demo.Repository.PromotionRepository;
import com.example.demo.Repository.FlightRepository;
import com.example.demo.Repository.ComplaintsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import org.springframework.web.client.RestTemplate;

@Service
public class AdminService {
    @Autowired
    private RefundRepository refundRepository;
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private ComplaintsRepository complaintsRepository;

    @Autowired
    private final RestTemplate restTemplate;
    public AdminService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    private String transactionServiceUrl="http://TRANSACTION-SERVICE";
    public List<Refund> getAllRefunds() {
        return refundRepository.findAll();
    }

    public Refund processRefund(Refund refund) {
        return refundRepository.save(refund);
    }
    public Refund updateRefundStatus(Long id, String status) {

        Refund refund = refundRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Refund not found"));
        refund.setStatus(status);

        notifyTransactionService(refund);
        return refundRepository.save(refund);
    }
    public Refund createRefund(Refund refund) {
        return refundRepository.save(refund);
    }
    private void notifyTransactionService(Refund refund) {
        String url = transactionServiceUrl + "/api/transaction/refunds/" + refund.getId() + "/status";
        System.out.println(url);
        restTemplate.put(url, refund);
    }


    public Promotion addPromotion(Promotion promotion, Long flightId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
        promotion.setFlight(flight);
        return promotionRepository.save(promotion);
    }

    public Complaints followUpComplaint(UUID complaintId, String status) {
        Complaints complaint = complaintsRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
        complaint.setStatus(status);
        return complaintsRepository.save(complaint);
    }

    // Add other necessary methods
}
