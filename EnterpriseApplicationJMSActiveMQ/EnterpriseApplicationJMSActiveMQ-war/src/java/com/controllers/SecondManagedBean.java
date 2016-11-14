/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.expone.Receiver;
import com.expone.Sender;
import ejbbeans.ReceiverSessionBean;
import ejbbeans.ReceiverSessionBeanLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author user
 */
@ManagedBean(name = "receiveManagedBean")
@RequestScoped
public class SecondManagedBean {

    private String msgreceive;

    public String getMsgreceive() {
        return msgreceive;
    }

    public void setMsgreceive(String msgreceive) {
        this.msgreceive = msgreceive;
    }
    
    public SecondManagedBean() {
    }
    
    @EJB
    ReceiverSessionBeanLocal receiver;
    
     public void recieveMessage(){
         //Receiver receiver = new Receiver();
         String msg = receiver.receiveMessage();
         System.out.println("Message is : "+msg);
         setMsgreceive(msg);
    }
    
}
