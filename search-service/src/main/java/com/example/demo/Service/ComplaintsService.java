package com.example.demo.Service;
import com.example.demo.Repository.ComplaintsRepository;
import com.example.demo.Model.Complaints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class ComplaintsService {

    private static final String COMPLAINTS_CACHE_PREFIX = "user::";

    @Autowired
    private ComplaintsRepository complaintsRepository;
    @Autowired
    private RedisTemplate<String, Complaints> redisTemplate;

    public List<Complaints> getAllComplaintss() {
        return complaintsRepository.findAll();
    }

    public Complaints getComplaintsById(UUID id) {
        System.out.println("GET");
        Complaints cachedComplaint = redisTemplate.opsForValue().get(COMPLAINTS_CACHE_PREFIX+ id);
        if (cachedComplaint != null) {
            System.out.println("CASHED");
            return cachedComplaint;
        } else {
            System.out.println("DB");
            Complaints dbComplaint = complaintsRepository.findById(id).orElse(null);
            if (dbComplaint != null) {
                redisTemplate.opsForValue().set(COMPLAINTS_CACHE_PREFIX + id, dbComplaint, 10, TimeUnit.MINUTES);
            }
            return dbComplaint;
        }
    }

    public Complaints saveComplaints(Complaints complaints) {
        complaints.setId(UUID.randomUUID());
        complaints.setStatus("Pending");
        // added auth for user
        complaints.setUserID(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
        redisTemplate.opsForValue().set(COMPLAINTS_CACHE_PREFIX + complaints.getId(), complaints, 10, TimeUnit.MINUTES);
        return complaintsRepository.save(complaints);
    }

    public void deleteComplaints(UUID id) {
        complaintsRepository.deleteById(id);
        redisTemplate.delete(COMPLAINTS_CACHE_PREFIX + id);
    }
}
