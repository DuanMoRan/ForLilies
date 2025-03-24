package com.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.entity.Course;
import com.example.entity.Message;
import com.example.entity.SelectCourse;
import com.example.queues.SenderServer;

@Component
@Aspect
public class UserTransactionalAspect {
    @Autowired
    private SenderServer server;

    @AfterReturning(value = "@annotation(CreateCourse)" , returning = "result")
    public void CreateCourse(Object result , ProceedingJoinPoint joinPoint){
        if(result != null){
            send("c" , (SelectCourse)joinPoint.getArgs()[0]);
        }
    }

    @AfterReturning(value = "@annotation(DeleteCourse)" , returning = "result")
    public void DeleteCourse(Object reslut , ProceedingJoinPoint joinPoint){
        if(reslut != null){
            send("d" ,  (SelectCourse)joinPoint.getArgs()[0]);
        }
    }

    public void send(String operator , SelectCourse course){
        Message message = new Message();
        message.setOperator(operator);
        Course data = new Course();
        data.setCourseNo(course.getCourseNo());
        message.setData(data);
        server.sendMessage(message);
    }
}
