package com.example.demo.Service;

import com.example.demo.Model.Refund;
import com.example.demo.Model.Promotion;
import com.example.demo.Model.Flight;
import com.example.demo.Model.Complaints;
import com.example.demo.Repository.RefundRepository;
import com.example.demo.Repository.PromotionRepository;
import com.example.demo.Repository.FlightRepository;
import com.example.demo.Repository.ComplaintsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

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

    public Refund processRefund(Refund refund) {
        return refundRepository.save(refund);
    }
    public Refund updateRefundStatus(Long id, String status) {
        Refund refund = refundRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Refund not found"));
        refund.setStatus(status);
        return refundRepository.save(refund);
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
