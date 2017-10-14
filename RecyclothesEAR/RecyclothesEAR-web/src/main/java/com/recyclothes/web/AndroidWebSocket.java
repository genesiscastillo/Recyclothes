package com.recyclothes.web;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by Cesar on 12-08-2016.
 */
@ApplicationScoped
@ServerEndpoint("/androidWebSocket")
public class AndroidWebSocket {

    static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(AndroidWebSocket.class);

    @OnOpen
    public void open(Session session){
        LOGGER.info("OPEN Session from Android id "+session.getId());
    }

    @OnClose
    public void close(Session session) {
        LOGGER.info("CLOSE Session from Android id "+session.getId());
    }
    @OnError
    public void error(Throwable throwable) {
        LOGGER.error("ERRO on AndroidWebSocket ",throwable);
    }

    @OnMessage
    public void handlerMessage(Session session , String message){
        LOGGER.info("Session id ="+session.getId());
        LOGGER.info("Message    ="+message);
    }
}
