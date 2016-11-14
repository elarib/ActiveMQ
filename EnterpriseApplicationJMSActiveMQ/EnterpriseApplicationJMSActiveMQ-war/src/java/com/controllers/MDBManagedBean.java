/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.expone.Sender;
import ejbbeans.ActiveMQProducerLocal;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.ejb.EJB;
/**
 *
 * @author user
 */
@ManagedBean(name = "mdbManagedBean")
@RequestScoped
public class MDBManagedBean {

    private String msgsend;

    public String getMsgsend() {
        return msgsend;
    }

    public void setMsgsend(String msgsend) {
        this.msgsend = msgsend;
    }
    
    public MDBManagedBean() {
    }
    
    @EJB
    ActiveMQProducerLocal producer;
    
    public void sendMessage(){
        producer.sendMessage(getMsgsend());
    }
    
}
