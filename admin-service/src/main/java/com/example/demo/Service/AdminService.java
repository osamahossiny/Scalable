package com.example.demo.Service;

import com.example.demo.model.Refund;
import com.example.demo.model.Promotion;
import com.example.demo.model.Flight;
import com.example.demo.model.Complaint;
import com.example.demo.Repository.RefundRepository;
import com.example.demo.Repository.PromotionRepository;
import com.example.demo.Repository.FlightRepository;
import com.example.demo.Repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private RefundRepository refundRepository;
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private ComplaintRepository complaintRepository;

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

    public Complaint followUpComplaint(Long complaintId, String status) {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
        complaint.setStatus(status);
        return complaintRepository.save(complaint);
    }

    // Add other necessary methods
}
