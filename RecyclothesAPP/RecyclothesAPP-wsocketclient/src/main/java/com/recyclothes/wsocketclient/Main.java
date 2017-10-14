package com.recyclothes.wsocketclient;

import org.json.simple.JSONObject;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;

/**
 * Enterprise Application Client main class.
 *
 */
public class Main {

    ClienteAdminWebSocketClient clienteAdminWebSocketClient = new ClienteAdminWebSocketClient();

    public static void main( String[] args )     {
        System.out.println("Hello World!");
        Main main = new Main();
        main.connectToWebSocket();
        main.serviceTestSaludo();
    }

    public void serviceTestSaludo()   {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("action" , "SALUDO_TEST");
            System.out.println("REQUEST jsonObject-->"+jsonObject );
            clienteAdminWebSocketClient.send(jsonObject);
            Thread.sleep(10000L);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void connectToWebSocket() {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {

            URI uri = URI.create("ws://web-babycaprichitos.rhcloud.com:8000/RecyclothesEAR-web/clienteWebSocket");
            container.connectToServer(clienteAdminWebSocketClient , uri);
        } catch (DeploymentException | IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }



}
