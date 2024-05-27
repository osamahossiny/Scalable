package com.example.demo.Controller;

import com.example.demo.Model.Refund;
import com.example.demo.Service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction/refunds")
public class RefundController {

    @Autowired
    private RefundService refundService;

        @PostMapping
        public ResponseEntity<Refund> createRefund(@RequestBody Refund refund) {
//            String userId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
//            System.out.println(userId);
            refund.setUserId(1l);
            refund.setStatus("Pending");
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
