package com.example.demo.Controller;

import com.example.demo.Model.Refund;
import com.example.demo.Service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/refunds")
public class RefundController {

    @Autowired
    private RefundService refundService;

    @PostMapping
    public ResponseEntity<Refund> createRefund(@RequestBody Refund refund) {
        //String jwt = token.substring(7);
       // String username = jwtUtil.extractUsername(jwt);
        // Assuming you have a method to get userId from username
        // add auth to get user_id
         Long userId = 1L;
        refund.setUserId(userId);
        Refund createdRefund = refundService.createRefund(refund);
        return ResponseEntity.ok(createdRefund);
    }


}
