package com.example.demo;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Sender {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message;
        String[] shoppingListArray = {"Eggs", "Bacon", "Ketchup","Sandwiches","Ham"};
        int i;
        int length = shoppingListArray.length;
        for(i = 0; i<length; i++){
            message = shoppingListArray[i];
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println("Shopping list item sent: '" + message + "'");
        }


        channel.close();
        connection.close();
    }
}