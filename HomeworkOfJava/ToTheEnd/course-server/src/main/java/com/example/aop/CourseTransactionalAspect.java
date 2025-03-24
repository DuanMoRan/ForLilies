package com.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.entity.Course;
import com.example.entity.CourseDTO;
import com.example.entity.Message;
import com.example.queues.SenderServer;


@Component
@Aspect
public class CourseTransactionalAspect {
    @Autowired
    private SenderServer server;

    @AfterReturning(value = "@annotation(CreateCourse)" , returning = "result")
    public void CreateCourse(Object result , ProceedingJoinPoint joinPoint){
        if(result != null){
            send("c", (Course)joinPoint.getArgs()[0]);
        }
    }

    @AfterReturning(value = "@annotation(UpdateCourse)" , returning = "result")
    public void UpdateCourse(Object result , ProceedingJoinPoint joinPoint){
        if(result != null){
            send("u", (Course)joinPoint.getArgs()[0]);
        }
    }

    @AfterReturning(value = "@annotation(DeleteCourse)" , returning = "result")
    public void DeleteCourse(Object result , ProceedingJoinPoint joinPoint){
        if(result != null){
            send("d", (Course)joinPoint.getArgs()[0]);
        }
    }


    public void send(String operator, Course course) {
        CourseDTO dto = new CourseDTO(course);
        Message message = new Message(operator, dto);
        server.sendMessage(message);
    }
}
