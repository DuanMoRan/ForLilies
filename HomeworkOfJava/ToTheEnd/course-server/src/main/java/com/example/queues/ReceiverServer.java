package com.example.queues;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CourseMapper;
import com.example.entity.Course;
import com.example.entity.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ReceiverServer {
    @Autowired
    private CourseMapper mapper;

    @RabbitListener(queues = "course-server")
    public void ReceiverMessage(Message message) {
        // Course course = (Course) message.getData();
        ObjectMapper objectMapper = new ObjectMapper();
        Course course = objectMapper.convertValue(message.getData(), Course.class);
        switch (message.getOperator()) {
            case "c":
                mapper.add(course);
                break;
            case "d":
                mapper.decreas(course);
                break;
        }
    }
}
