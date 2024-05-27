package com.example.demo.Controller;

import com.example.demo.Model.Refund;
import com.example.demo.Service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/refunds")
public class RefundController {

    @Autowired
    private RefundService refundService;

        @PostMapping
        public ResponseEntity<Refund> createRefund(@RequestBody Refund refund) {
           // Long userId = (long)SecurityContextHolder.getContext().getAuthentication().getCredentials();
           refund.setUserId(1L);
            Refund createdRefund = refundService.createRefund(refund);
            return ResponseEntity.ok(createdRefund);
        }
    @PutMapping("/{id}/status")
    public ResponseEntity<Refund> updateRefundStatus(@PathVariable Long id, @RequestBody Refund refund) {
        System.out.println("test api");
        Refund updatedRefund = refundService.updateRefundStatus(id, refund.getStatus());
        return ResponseEntity.ok(updatedRefund);
    }


}
