package com.recyclothes.fx.controller;

import org.json.simple.JSONObject;

import java.nio.ByteBuffer;

/**
 * Created by Cesar on 19-07-2016.
 */
public interface ControlledScreen {

    void setScreenParent(ScreensController screensController);
    void sendToServerWebSocket(JSONObject jsonObject);
    void updateData(JSONObject jsonObject);
    void receiverByteBuffer (ByteBuffer byteBuffer);
}
