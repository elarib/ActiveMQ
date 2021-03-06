/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Subscriber {
    private static final String NO_GREETING = "no greeting";
    private String clientId;
    private Connection connection;
    private Session session;
    private MessageConsumer messageConsumer;
    public void create(String clientId, String topicName) throws JMSException {
        this.clientId = clientId;
        // create a Connection Factory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        // create a Connection
        connection = connectionFactory.createConnection();
        connection.setClientID(this.clientId);
        // create a Session
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // create the Topic from which messages will be received
        Topic topic = session.createTopic(topicName);
        // create a MessageConsumer for receiving messages
        messageConsumer = session.createConsumer(topic);
        // start the connection in order to receive messages
        connection.start();
    }
    public String getGreeting(int timeout) throws JMSException {
        String greeting = NO_GREETING;
        // read a message from the topic destination
        Message message = messageConsumer.receive(timeout);
        // check if a message was received
        if (message != null) {
            // cast the message to the correct type
            TextMessage textMessage = (TextMessage) message;
            // retrieve the message content
            String text = textMessage.getText();
            // create greeting
            greeting = "Hello " + text + "!";
        } else {
            System.out.println(clientId + ": no message received");
        }
        System.out.println("greeting={}"+greeting);
        return greeting;
    }
    
    public void closeConnection() throws JMSException {
        connection.close();
    }
    
     public static void main(String[] args) throws JMSException {
         Subscriber subscriber = new Subscriber();
         subscriber.create("sub1", "amqmsgtopic");
         System.out.println("Subscriber.main() : "+subscriber.getGreeting(1000));
         
     }
    
}
