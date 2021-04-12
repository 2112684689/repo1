package com.wang.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer_PubSub_1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.137.128");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/wyq");
        connectionFactory.setUsername("wyq");
        connectionFactory.setPassword("111111");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        String queueName1="test_pubsub_queue1";
        String queueName2="test_pubsub_queue2";

        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body：" + new String(body));
                System.out.println("body：将日志信息打印到控制台");
            }
        };

        channel.basicConsume(queueName1,true,consumer);

    }
}
