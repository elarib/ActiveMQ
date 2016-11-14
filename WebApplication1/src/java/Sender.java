
import entity.EventMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQObjectMessage;

public class Sender {

    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageProducer producer = null;

    public Sender() {

    }

    public void sendMessage() {

        try {
            factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //destination = session.createQueue("SAMPLEQUEUE");
            for(int i =0; i<3; i++){
                destination = session.createQueue("amqmsg"+i);
                producer = session.createProducer(destination);
                sendMsgToDest( destination, session, i);
            
            }
            
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    
    void sendMsgToDest(Destination dest, Session sess, int step) throws JMSException{
        MessageProducer prod = sess.createProducer(dest);
        EventMessage eventMessage1 = new EventMessage(1,"Message from FirstClient "+step);
            
            //Object Message
            ObjectMessage objectMessage1 = sess.createObjectMessage();
            objectMessage1.setObject(eventMessage1);
            prod.send(objectMessage1);
            
            prod.close();
            
    }

    public static void main(String[] args) {
        Sender sender = new Sender();
        sender.sendMessage();
    }
}
