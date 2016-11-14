/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import ejbbeans.TopicSenderSessionBeanLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author admin
 */
@ManagedBean(name = "topicFirstJSFManagedBean")
@RequestScoped
public class TopicFirstJSFManagedBean {

    /**
     * Creates a new instance of TopicFirstJSFManagedBean
     */
    public TopicFirstJSFManagedBean() {
    }
    
     private String msgsend;

    public String getMsgsend() {
        return msgsend;
    }

    public void setMsgsend(String msgsend) {
        this.msgsend = msgsend;
    }
    
    @EJB
    TopicSenderSessionBeanLocal sender;
    
    public void sendMessage(){
        //Sender sender = new Sender();
        sender.sendMessage(getMsgsend());
    }
}
