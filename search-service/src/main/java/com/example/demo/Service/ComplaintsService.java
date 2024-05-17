package com.example.demo.Service;
import com.example.demo.Repository.ComplaintsRepository;
import com.example.demo.model.Complaints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ComplaintsService {
    @Autowired
    private ComplaintsRepository complaintsRepository;

    public List<Complaints> getAllComplaintss() {
        return complaintsRepository.findAll();
    }

    public Optional<Complaints> getComplaintsById(UUID id) {
        return complaintsRepository.findById(id);
    }

    public Complaints saveComplaints(Complaints complaints) {
        complaints.setId(UUID.randomUUID());
        return complaintsRepository.save(complaints);
    }

    public void deleteComplaints(UUID id) {
        complaintsRepository.deleteById(id);
    }
}
