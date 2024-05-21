package com.example.demo.Controller;

import com.example.demo.model.Refund;
import com.example.demo.model.Promotion;
import com.example.demo.model.Complaint;
import com.example.demo.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/refund")
    public Refund processRefund(@RequestBody Refund refund) {
        return adminService.processRefund(refund);
    }
    @PutMapping("/refund/{id}/status")
    public Refund updateRefundStatus(@PathVariable Long id, @RequestBody RefundStatusUpdateRequest statusUpdateRequest) {
        return adminService.updateRefundStatus(id, statusUpdateRequest.getStatus());
    }
    @PostMapping("/promotion/{flightId}")
    public Promotion addPromotion(@RequestBody Promotion promotion, @PathVariable Long flightId) {
        return adminService.addPromotion(promotion, flightId);
    }

    @PostMapping("/complaint/{id}")
    public Complaint followUpComplaint(@PathVariable Long id, @RequestParam String status) {
        return adminService.followUpComplaint(id, status);
    }

}
