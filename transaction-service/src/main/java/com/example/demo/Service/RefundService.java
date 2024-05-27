package com.example.demo.Service;

import com.example.demo.Model.Refund;
import com.example.demo.Repository.RefundRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RefundService {
    private final RefundRepository refundRepository;
    private final RestTemplate restTemplate;


    private String adminServiceUrl="http://admin_service";

    public RefundService(RefundRepository refundRepository, RestTemplate restTemplate) {
        this.refundRepository = refundRepository;
        this.restTemplate = restTemplate;
    }

    public Refund createRefund(Refund refund) {
        Refund createdRefund = refundRepository.save(refund);
        notifyAdminService(createdRefund);
        return createdRefund;
    }

    private void notifyAdminService(Refund refund) {
        String url = adminServiceUrl + "/api/v1/refunds";
        restTemplate.postForEntity(url, refund, Void.class);
    }
    public Refund updateRefundStatus(Long id, String status) {
        System.out.println(id);
        Refund refund = refundRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Refund not found"));
        refund.setStatus(status);
        return refundRepository.save(refund);
    }
}
