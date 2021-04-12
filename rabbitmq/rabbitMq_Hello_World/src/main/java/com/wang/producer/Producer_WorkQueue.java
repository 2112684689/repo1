package com.wang.producer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer_WorkQueue {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.137.128");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/wyq");
        connectionFactory.setUsername("wyq");
        connectionFactory.setPassword("111111");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("work_queue", true, false, false, null);

        for(int i=1;i<11;i++){
            String word="workqueue~~~~~"+i;
            channel.basicPublish("","work_queue",null,word.getBytes());
        }

        channel.close();
        connection.close();


    }
}
