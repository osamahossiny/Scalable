package com.example.demo.Controller;

import com.example.demo.model.Refund;
import com.example.demo.model.Promotion;
import com.example.demo.model.Complaints;
import com.example.demo.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;


    @PutMapping("/refund/{id}/status")
    public Refund updateRefundStatus(@PathVariable Long id, @RequestBody RefundStatusUpdateRequest statusUpdateRequest) {
        return adminService.updateRefundStatus(id, statusUpdateRequest.getStatus());
    }
    @PostMapping("/promotion/{flightId}")
    public Promotion addPromotion(@RequestBody Promotion promotion, @PathVariable Long flightId) {
        return adminService.addPromotion(promotion, flightId);
    }

    @PutMapping("/followup/{complaintId}")
    public ResponseEntity<Complaints> followUpComplaint(@PathVariable UUID complaintId, @RequestParam String status) {
        Complaints updatedComplaint = adminService.followUpComplaint(complaintId, status);
        return ResponseEntity.ok(updatedComplaint);
    }

}
