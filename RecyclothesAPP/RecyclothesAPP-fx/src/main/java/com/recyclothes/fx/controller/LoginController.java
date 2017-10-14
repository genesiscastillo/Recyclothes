package com.recyclothes.fx.controller;

import com.recyclothes.fx.MainTest;
import com.recyclothes.fx.enums.EndpointUrlEnum;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Cesar on 23-08-2016.
 */
public class LoginController implements Initializable {

    @FXML
    Button buttonLogin;

    @FXML
    TextField textFieldLogin;

    @FXML
    TextField textFieldPassword;

    @FXML
    ComboBox<EndpointUrlEnum> comboBoxEndpointUrl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxEndpointUrl.getItems().addAll(EndpointUrlEnum.values());
        comboBoxEndpointUrl.getSelectionModel().selectFirst();
        buttonLogin.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
                stage.hide();
                Scene scene = new MainTest().getScene(comboBoxEndpointUrl.getSelectionModel().getSelectedItem().getUtl());
                stage.setScene(scene);
                stage.show();
            }
        });
    }
}
