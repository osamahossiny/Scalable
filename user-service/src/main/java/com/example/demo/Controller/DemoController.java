package com.example.demo.Controller;

import com.example.demo.Service.KafkaProducerService;
import com.example.demo.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/messages")
@RequiredArgsConstructor
//@PreAuthorize("hasRole('ADMIN')")
public class DemoController {

    private final KafkaProducerService kafkaProducerService;

    @PostMapping
    public ResponseEntity<String> sendMessage(
            @RequestBody RegisterRequest registerRequest
    ){
        kafkaProducerService.sendRegisterRequestMessage(registerRequest);
        return ResponseEntity.ok(registerRequest.toString());
    }

//    @GetMapping
//    @PreAuthorize("hasAuthority('admin:read')")
//    public String get() {
//        return "GET:: admin controller";
//    }
}
