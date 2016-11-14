/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdb;

import com.pwebsockets.MyWebsocket;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author user
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "amqmsgmd")
})
//@ResourceAdapter("activemq-rar-5.14.0.rar")
public class NewMessageBean implements MessageListener {
    
    public NewMessageBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            //System.out.println("We've received a message: " + message.getJMSMessageID());
            TextMessage text = (TextMessage) message;
            System.out.println("Message MDB received is : "+text.getText());
            //send message by websocket
            MyWebsocket.send(text.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    
}
