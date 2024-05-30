package com.example.demo.Controller;
import com.example.demo.Model.Refund;
import com.example.demo.Service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/admin/refunds")
public class Request {
    private final AdminService adminService;
    public Request(AdminService adminService) {
        this.adminService = adminService;
    }
    @PostMapping
    public ResponseEntity<Refund> createRefund(@RequestBody Refund refund) {
        Refund createdRefund = adminService.createRefund(refund);
        return ResponseEntity.ok(createdRefund);
    }
}
