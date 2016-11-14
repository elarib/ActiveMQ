package performance;


import entity.EventMessage;
import java.util.Arrays;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQObjectMessage;

public class Receiver {

    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageConsumer consumer = null;

    public Receiver() {

    }

    public void receiveMessage() {
        try {
            System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");
            factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session./*CLIENT_ACKNOWLEDGE*/AUTO_ACKNOWLEDGE);
            //destination = session.createQueue("SAMPLEQUEUE");
            destination = session.createQueue("amqmsg");
            consumer = session.createConsumer(destination);
            Message message = consumer.receive();
            //Receive message text
            /*if (message instanceof TextMessage) {
                TextMessage text = (TextMessage) message;
                System.out.println("Message is : " + text.getText());
            }*/
            
            //Receive message object
            if (message instanceof ObjectMessage) {
                ActiveMQObjectMessage acom = (ActiveMQObjectMessage) message;
                EventMessage evt = (EventMessage) acom.getObject();
                System.out.println(this.getClass().getName()+ "has received a message : " + evt);
                /*if(evt.getMessageId() == 5){
                    message.acknowledge();
                }*/
            }
            
            consumer.close();
            session.close();
            connection.close();
            
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        receiver.receiveMessage();
    }
}
