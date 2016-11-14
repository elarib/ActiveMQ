
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public class PublisherMe {
    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageProducer producer = null;

        private String nom_topic = "amqmsgtopic";
    
    public void sendMessage(String msg) {

        try {
            factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic(nom_topic);
            producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage();
            message.setText(msg);
            producer.send(message);
            System.out.println("Sent Topic: " + message.getText());
            producer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        PublisherMe publisherMe = new PublisherMe();
        publisherMe.sendMessage("Hello2");
    }
    
}
