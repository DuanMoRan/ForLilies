package com.example.queues;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Course;
import com.example.entity.Message;
import com.example.service.SelectRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ReceiveServer {

    @Autowired
    private SelectRepository repository;

    @RabbitListener(queues = "select-server")
    public void receiveMessage(Message message){
        ObjectMapper objectMapper = new ObjectMapper();
        Course course = objectMapper.convertValue(message.getData(), Course.class);

        switch (message.getOperator()) {
            case "c":
                repository.save(course);
                break;
            case "d":
                repository.deleteById(course.getCourseNo());
                break;
        }
    }
    
}