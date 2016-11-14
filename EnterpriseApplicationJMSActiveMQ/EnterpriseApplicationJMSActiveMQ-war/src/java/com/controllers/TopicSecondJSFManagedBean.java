/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import ejbbeans.TopicReceiverSessionBeanLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author admin
 */
@ManagedBean(name = "topicSecondJSFManagedBean")
@RequestScoped
public class TopicSecondJSFManagedBean {

    /**
     * Creates a new instance of TopicSecondJSFManagedBean
     */
    public TopicSecondJSFManagedBean() {
    }
    
     private String msgreceive;

    public String getMsgreceive() {
        return msgreceive;
    }

    public void setMsgreceive(String msgreceive) {
        this.msgreceive = msgreceive;
    }
    
    @EJB
    TopicReceiverSessionBeanLocal receiver;
    
    public void recieveMessage(){
         //Receiver receiver = new Receiver();
         String msg = receiver.receiveMessage();
         System.out.println("Message is : "+msg);
         setMsgreceive(msg);
    }
    
}
