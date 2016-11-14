
import entity.EventMessage;
import java.util.Arrays;
import java.util.Enumeration;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQObjectMessage;

public class ReceiverBrowserQueue {

    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageConsumer consumer = null;

    public ReceiverBrowserQueue() {

    }

    public void receiveMessage() {
        try {
            System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");
            factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("amqmsg");
            QueueBrowser browser = session.createBrowser(queue);
            //Browse messages object
            Enumeration<Message> e = (Enumeration<Message>) browser.getEnumeration();
            while (e.hasMoreElements()) {
              Message msg = e.nextElement();
              //System.out.println("Found " + msg.getJMSMessageID());
              ActiveMQObjectMessage acom = (ActiveMQObjectMessage) msg;
              EventMessage evt = (EventMessage) acom.getObject();
              System.out.println(this.getClass().getName()+ " has received a message : " + evt);
            }
            
            browser.close();
            session.close();
            connection.close();
            
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ReceiverBrowserQueue receiverBrowserQueue = new ReceiverBrowserQueue();
        receiverBrowserQueue.receiveMessage();
    }
}
