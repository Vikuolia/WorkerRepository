package com.example.worker.rabbitmq;

import com.example.worker.model.Worker;
import com.example.worker.service.WorkerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.worker.rabbitmq.Config.QUEUE;

@Component
public class Consumer {

    @Autowired
    WorkerService instructorService;

    @RabbitListener(queues = QUEUE)
    public  void consumeMessageFromQueue(Worker worker){
        System.out.println(instructorService.addWorker(worker));
    }
}

