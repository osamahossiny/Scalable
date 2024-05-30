package com.example.demo.Controller;
import com.example.demo.Service.ComplaintsService;
import com.example.demo.Model.Complaints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/search/complaints")
public class ComplaintsController {
    @Autowired
    private ComplaintsService complaintsService;

    @GetMapping
    public List<Complaints> getAllComplaintss() {
        return complaintsService.getAllComplaintss();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Complaints> getComplaintsById(@PathVariable UUID id) {
        Optional<Complaints> complaints = Optional.ofNullable(complaintsService.getComplaintsById(id));
        return complaints.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Complaints createComplaints(@RequestBody Complaints complaints) {
        return complaintsService.saveComplaints(complaints);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComplaints(@PathVariable UUID id) {
        complaintsService.deleteComplaints(id);
        return ResponseEntity.noContent().build();
    }
}

