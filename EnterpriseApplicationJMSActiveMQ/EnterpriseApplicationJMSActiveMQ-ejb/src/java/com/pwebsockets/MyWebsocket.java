/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwebsockets;

/**
 *
 * @author admin
 */

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/mywebsocket")
public class MyWebsocket {
    
    static Queue<Session> queue = new ConcurrentLinkedQueue<>();

    public static void send(String msg) {
        try {
            for (Session session : queue) {
                session.getBasicRemote().sendText(msg);
                System.out.println("Send: {0} "+ msg + " to " + session.getId());
            }
        } catch (IOException e) {
        }
    }

    @OnOpen
    public void open(Session session) {
        queue.add(session);
    }

    @OnClose
    public void close(Session session) {
        queue.remove(session);
    }

    @OnError
    public void error(Session session, Throwable t) {
        queue.remove(session);
    }
}