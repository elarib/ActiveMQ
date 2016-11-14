/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.expone.Sender;
import ejbbeans.SenderSessionBean;
import ejbbeans.SenderSessionBeanLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author user
 */
@ManagedBean(name = "sendManagedBean")
@RequestScoped
public class FirstManagedBean {

    private String msgsend;

    public String getMsgsend() {
        return msgsend;
    }

    public void setMsgsend(String msgsend) {
        this.msgsend = msgsend;
    }
    
    public FirstManagedBean() {
    }
    
    @EJB
    SenderSessionBeanLocal sender;
    
    public void sendMessage(){
        //Sender sender = new Sender();
        sender.sendMessage(getMsgsend());
    }
    
}
