package com.recyclothes.wsocketclient;

import javafx.scene.layout.StackPane;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.websocket.*;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by Cesar on 22-07-2016.
 */
@ClientEndpoint
public class ClienteAdminWebSocketClient extends StackPane {

    private Session session;

    @OnOpen
    public void open(Session session){
        this.session = session;
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
    @OnMessage
    public void getMessage(String message ) {
        try {
            JSONObject jsonMessage = (JSONObject) new JSONParser().parse(new StringReader(message));
            System.out.println("RESPONSE jsonMessage-->"+jsonMessage );
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void send(JSONObject jsonObject) throws IOException {
        session.getBasicRemote().sendText(jsonObject.toString());
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
