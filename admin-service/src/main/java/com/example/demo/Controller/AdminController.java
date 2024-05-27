package com.example.demo.Controller;

import com.example.demo.Model.Refund;
import com.example.demo.Model.Promotion;
import com.example.demo.Model.Complaints;
import com.example.demo.Service.AdminService;
import com.example.demo.dto.UserTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
@RefreshScope
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Value("${admin.status}")
    private String adminStatus;

    @PutMapping("/refund/{id}/status")
    public ResponseEntity<?> updateRefundStatus(@PathVariable Long id, @RequestBody RefundStatusUpdateRequest statusUpdateRequest) {
        if ("frozen".equalsIgnoreCase(adminStatus)) {
            return ResponseEntity.status(403).body("Operation not allowed. The system is currently frozen.");
        }
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getCredentials());
        Refund updatedRefund = adminService.updateRefundStatus(id, statusUpdateRequest.getStatus());
        return ResponseEntity.ok(updatedRefund);
    }

    @GetMapping("/refunds")
    public ResponseEntity<?> getAllRefunds() {
        if (adminStatus.equals("frozen")) {
            return ResponseEntity.status(403).body("Operation not allowed. The system is currently frozen.");
        }
        List<Refund> refunds = adminService.getAllRefunds();
        return ResponseEntity.ok(refunds);
    }

    @PostMapping("/promotion/{flightId}")
    public ResponseEntity<?> addPromotion(@RequestBody Promotion promotion, @PathVariable Long flightId) {
        if ("frozen".equalsIgnoreCase(adminStatus)) {
            return ResponseEntity.status(403).body("Operation not allowed. The system is currently frozen.");
        }
        Promotion addedPromotion = adminService.addPromotion(promotion, flightId);
        return ResponseEntity.ok(addedPromotion);
    }

    @PutMapping("/followup/{complaintId}")
    public ResponseEntity<?> followUpComplaint(@PathVariable UUID complaintId, @RequestParam String status) {
        if ("frozen".equalsIgnoreCase(adminStatus)) {
            return ResponseEntity.status(403).body("Operation not allowed. The system is currently frozen.");
        }
        Complaints updatedComplaint = adminService.followUpComplaint(complaintId, status);
        return ResponseEntity.ok(updatedComplaint);
    }
}
