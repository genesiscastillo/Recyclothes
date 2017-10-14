package com.recyclothes.wsocketclient;

import javax.websocket.*;

/**
 * Created by Cesar on 22-07-2016.
 */
@ClientEndpoint
public class BackOfficeWebSocketClient {

    @OnOpen
    public void open(Session session){
        System.out.println("OPEN....");
    }
    @OnClose
    public void close(Session session){
        System.out.println("CLOSE....");
    }
    @OnError
    public void error(Throwable throwable){
        System.out.println("ERROR....");
        throwable.printStackTrace();
    }

}
