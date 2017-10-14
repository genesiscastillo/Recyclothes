package com.recyclothes.fx.main;
/**
 * Created by Cesar on 21-07-2016.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainModificarProducto extends Application {

    public static void main(String[] args) throws Exception  {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String fxmlFile = "/fxml/productos/ModificarProducto.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        Scene scene = new Scene(rootNode, 1024,650);
        scene.getStylesheets().add("/styles/styles.css");

        primaryStage.setTitle("Hello JavaFX and Maven");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
