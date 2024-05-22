package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, User> kafkaTemplate;

    public void sendRegisterRequestMessage(RegisterRequest registerRequest){
        Message<RegisterRequest> message = MessageBuilder.withPayload(registerRequest).setHeader(KafkaHeaders.TOPIC, "service").build();
        kafkaTemplate.send(message);
    }
}
