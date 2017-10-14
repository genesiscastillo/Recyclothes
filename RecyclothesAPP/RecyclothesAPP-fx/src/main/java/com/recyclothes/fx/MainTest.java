package com.recyclothes.fx;/**
 * Created by Cesar on 19-07-2016.
 */

import com.recyclothes.fx.controller.MenuRecyclothesHandler;
import com.recyclothes.fx.controller.ScreensController;
import com.recyclothes.fx.enums.EndpointUrlEnum;
import com.recyclothes.fx.enums.ScreenEnum;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainTest extends Application {
    static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(MainTest.class);
    private BorderPane borderPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Recyclothes");
        Scene scene = getScene(EndpointUrlEnum.LOCALHOST_8080.getUtl());
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public Scene getScene(String urlEndpointWebSocket ) {
        final ScreensController screensController = new ScreensController(urlEndpointWebSocket);
        for(ScreenEnum screenEnum : ScreenEnum.values()) {
            LOGGER.info("Cargando screenEnum "+screenEnum.name());
            screensController.loadScreen(screenEnum);
        }
        screensController.setScreen(ScreenEnum.INTRO);

        Group root = new Group();
        Scene scene = new Scene(root, 1024, 700, Color.WHITE);
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Producto");

        MenuItem itemCargarFoto  = new MenuItem(ScreenEnum.CARGAR_FOTOS.getDescripcionItem());
        MenuItem itemRegistrarProducto  = new MenuItem(ScreenEnum.REGISTRAR_PRODUCTO.getDescripcionItem());
        MenuItem itemModificarProducto  = new MenuItem(ScreenEnum.MODIFICAR_PRODUCTO.getDescripcionItem());
        MenuItem itemStockProducto      = new MenuItem(ScreenEnum.AGENDAR_RESERVA.getDescripcionItem());
        MenuItem itemEstadisticas      = new MenuItem(ScreenEnum.ESTADISTICAS.getDescripcionItem());
        MenuItem itemRegistroCliente  = new MenuItem(ScreenEnum.REGISTRO_CLIENTE.getDescripcionItem());

        MenuRecyclothesHandler menuRecyclothesHandler = new MenuRecyclothesHandler();
        menuRecyclothesHandler.setScreenParent(screensController);

        itemCargarFoto.setOnAction(menuRecyclothesHandler);
        itemRegistrarProducto.setOnAction(menuRecyclothesHandler);
        itemModificarProducto.setOnAction(  menuRecyclothesHandler );
        itemStockProducto.setOnAction(      menuRecyclothesHandler );
        itemEstadisticas.setOnAction(       menuRecyclothesHandler );
        itemRegistroCliente.setOnAction(    menuRecyclothesHandler );

        menu.getItems().add(itemCargarFoto);
        menu.getItems().add(itemRegistrarProducto);
        menu.getItems().add(itemModificarProducto);
        menu.getItems().add(new SeparatorMenuItem());
        menu.getItems().add(itemStockProducto);
        menu.getItems().add(new SeparatorMenuItem());
        menu.getItems().add(itemEstadisticas);

        menuBar.getMenus().add(menu);

        Menu tools = new Menu("Cliente");
        tools.getItems().add( itemRegistroCliente );

        menuBar.getMenus().add(tools);
        menuBar.setPrefWidth(1024);
        borderPane = new BorderPane();
        borderPane.setTop(menuBar);

        borderPane.setCenter(screensController);

        root.getChildren().add(borderPane);
        return scene;
    }
}
