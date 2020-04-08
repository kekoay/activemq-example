package com.aobajohsai.producer;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer implements Runnable{
    @Override
    public void run() {
        try{
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            Connection con = factory.createConnection();

            con.start();

            Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination queue = session.createQueue("Queue");

            MessageProducer producer = session.createProducer(queue);

            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            String msg = "Stay home!";

            TextMessage message = session.createTextMessage(msg);

            System.out.println("Producer sent: " + msg);

            producer.send(message);

            session.close();
            con.close();
        }catch (Exception e){
            System.out.println("Exception occured in the producer.");
        }
    }
}
