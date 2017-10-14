package com.recyclothes.fx.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Cesar on 19-08-2016.
 */
public class MainCargaFotos extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception  {
        String fxmlFile = "/fxml/productos/CargarFotos.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        Scene scene = new Scene(rootNode, 1024,700);
        //scene.getStylesheets().add("/styles/styles.css");

        stage.setTitle("Cargar Fotos");
        stage.setScene(scene);
        stage.show();

    }
}
