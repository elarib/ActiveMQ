/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbbeans;

import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author user
 */
@Stateless
public class TopicReceiverSessionBean implements TopicReceiverSessionBeanLocal {

    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageConsumer consumer = null;
    private String nom_topic = "amqmsgtopic";
    
    public String receiveMessage() {
        String msg="";    
        try {
            factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic(nom_topic);
            consumer = session.createConsumer(destination);
            
            Message message = consumer.receive();
            if (message instanceof TextMessage) {
                TextMessage text = (TextMessage) message;
                msg = text.getText();
                //System.out.println("Received message2 : "+text.getText());
            }     
                /*MessageListener listner = new MessageListener() {
                        public void onMessage(Message message) {
                            try {
                                if (message instanceof TextMessage) {
                                    TextMessage textMessage = (TextMessage) message;
                                    System.out.println("Received Topic message : "+ textMessage.getText() + "'");
                                }
                            } catch (JMSException e) {
                                System.out.println("Caught:" + e);
                                e.printStackTrace();
                            }
                        }
                };
            consumer.setMessageListener(listner);*/
            consumer.close();
            session.close();
            connection.close();
            
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return msg;
    }
    
}
