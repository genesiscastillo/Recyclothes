package com.recyclothes.fx.main;/**
 * Created by Cesar on 23-08-2016.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainLogin extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception  {
        String fxmlFile = "/fxml/productos/Login.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        Scene scene = new Scene(rootNode, 1024,700);
        stage.setTitle("Hello JavaFX and Maven");
        stage.setScene(scene);
        stage.show();

    }
}
