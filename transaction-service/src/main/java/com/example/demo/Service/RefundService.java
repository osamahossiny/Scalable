package com.example.demo.Service;

import com.example.demo.model.Refund;
import com.example.demo.Repository.RefundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefundService {

    @Autowired
    private RefundRepository refundRepository;

    public Refund createRefund(Refund refund) {
        refund.setStatus("PENDING"); // Default status
        return refundRepository.save(refund);
    }

    public Refund getRefundById(Long id) {
        return refundRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Refund not found"));
    }

    public List<Refund> getAllRefunds() {
        return refundRepository.findAll();
    }


}
