package com.example.queues;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;

@Service
public class SenderServer {
    @Autowired
    private RabbitTemplate template;

    public void sendMessage(Message message) {
        template.convertAndSend("select-server", message);

    }
}
