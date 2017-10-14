package com.recyclothes.fx.controller;

import com.recyclothes.fx.enums.ScreenEnum;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import java.nio.ByteBuffer;

/**
 * Created by Cesar on 19-07-2016.
 */
public class MenuRecyclothesHandler implements EventHandler<ActionEvent> , ControlledScreen {

    static final Logger LOGGER = Logger.getLogger(MenuRecyclothesHandler.class);

    ScreensController screensController;

    @Override
    public void setScreenParent(ScreensController screensController) {
        this.screensController = screensController;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(actionEvent.getSource() instanceof MenuItem){
            MenuItem menuItem = ( MenuItem )actionEvent.getSource();
            for(ScreenEnum screenEnum : ScreenEnum.values()){
                if(screenEnum.getDescripcionItem().equals(menuItem.getText()))  {
                    screensController.setScreen(screenEnum);
                    break;
                }
            }
        }
    }

    @Override
    @Deprecated
    public void sendToServerWebSocket(JSONObject message) {
    }

    @Override
    @Deprecated
    public void updateData(JSONObject jsonObject) {
    }

    @Override
    @Deprecated
    public void receiverByteBuffer(ByteBuffer byteBuffer) {
    }
}
