/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbbeans;

import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface SenderSessionBeanLocal {
    public void sendMessage(String msg);
}
