package com.aobajohsai.consumer;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer implements Runnable{
    @Override
    public void run(){
        try{
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");

            //Create Connection
            Connection connection = factory.createConnection();

            // Start the connection
            connection.start();

            // Create Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            //Create queue
            Destination queue = session.createQueue("Queue");

            MessageConsumer consumer = session.createConsumer(queue);

            Message message = consumer.receive(1000);

            if(message instanceof TextMessage){
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println("Consumer received: " + text);
            }

            session.close();
            connection.close();
        }catch(Exception e){
            System.out.println("Exception occured in consumer");
        }
    }
}
