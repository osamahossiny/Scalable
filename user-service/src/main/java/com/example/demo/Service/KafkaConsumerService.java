package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.dto.RegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@Slf4j
public class KafkaConsumerService {

    @KafkaListener(topics = "service", groupId = "group")
    public void consumeMassage(RegisterRequest registerRequest) {
            log.info(format("Consuming massage :: %s", registerRequest.toString()));

    }
}
