package com.recyclothes.fx.controller;

import com.recyclothes.common.dto.FotoProductoDTO;
import com.recyclothes.common.dto.ProductoDTO;
import com.recyclothes.common.enums.CatalogoEnum;
import com.recyclothes.common.enums.EstadoProductoEnum;
import com.recyclothes.common.enums.TallaEnum;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Cesar on 19-07-2016.
 */
public class RegistrarProductoController implements Initializable ,ControlledScreen {

    static final Logger LOGGER = Logger.getLogger(RegistrarProductoController.class);

    ScreensController screensController;

    @FXML ComboBox comboBoxCategoria;
    @FXML ComboBox comboBoxTalla;
    @FXML TextArea textAreaDescripcion;
    @FXML TextField textFieldPrecio;

    @FXML FlowPane flowPane;

    @FXML Button buttonRegistrar;
    @FXML Button buttonLimpiar;

    private Dragboard db;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textAreaDescripcion.setStyle("-fx-font-size: 22pt;");
        textFieldPrecio.setStyle("-fx-font-size: 22pt;");
        try{
            ButtonHandler buttonHandler =   new ButtonHandler();
            buttonLimpiar.setOnMouseClicked(buttonHandler);
            buttonRegistrar.setOnMouseClicked(buttonHandler);

            comboBoxCategoria.getItems().addAll(CatalogoEnum.values());
            comboBoxCategoria.getSelectionModel().selectFirst();
            comboBoxTalla.getItems().addAll(TallaEnum.values());
            comboBoxTalla.getSelectionModel().selectFirst();
            textFieldPrecio.setAlignment(Pos.BASELINE_RIGHT);
            textFieldPrecio.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!newValue.matches("\\d*")) {
                        textFieldPrecio.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            });
            flowPane.setAlignment(Pos.CENTER);
            flowPane.setOnDragOver(new EventHandler<DragEvent>() {
                @Override
                public void handle(final DragEvent event) {
                    mouseDragOver(event);
                }
            });

            flowPane.setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(final DragEvent event) {
                    mouseDragDropped(event);
                }
            });

            flowPane.setOnDragExited(new EventHandler<DragEvent>() {
                @Override
                public void handle(final DragEvent event) {
                    flowPane.setStyle("-fx-border-color: #C6C6C6;");
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public class ButtonHandler implements  EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent mouseEvent) {
            Button button = (Button)mouseEvent.getSource();
            if( buttonLimpiar.equals(button) ) {
                limpiarPantalla();
            }
            else if( buttonRegistrar.equals( button )) {
                try {
                    List<FotoProductoDTO> fotoProductoDTOs = new ArrayList<>();
                    ProductoDTO productoDTO = new ProductoDTO();
                    productoDTO.setEstadoProductoEnum(EstadoProductoEnum.PENDIENTE);
                    productoDTO.setTallaEnum((TallaEnum) comboBoxTalla.getSelectionModel().getSelectedItem());
                    productoDTO.setCatalogoEnum((CatalogoEnum) comboBoxCategoria.getSelectionModel().getSelectedItem());
                    productoDTO.setPrecioProducto(Long.valueOf(textFieldPrecio.getText().isEmpty() ? "0" : textFieldPrecio.getText()));
                    productoDTO.setDescripcion(textAreaDescripcion.getText().isEmpty() ? "Sin Descripcion" : textAreaDescripcion.getText());
                    for (int i = 0; i < flowPane.getChildren().size(); i++) {
                        Path path = (Path) flowPane.getChildren().get(i).getUserData();
                        FotoProductoDTO fotoProductoDTO = new FotoProductoDTO();
                        byte[] bytes = Files.readAllBytes( path );
                        fotoProductoDTO.setFoto(bytes);
                        fotoProductoDTOs.add(fotoProductoDTO);
                    }
                    List<Path> paths = new ArrayList<>();
                    for (int i = flowPane.getChildren().size() -1 ; i >= 0; i--)    {
                        Path path = (Path) flowPane.getChildren().get(i).getUserData();
                        paths.add(path);
                    }
                    flowPane.getChildren().clear();
                    for(Path path : paths)      {
                        String filename = path.toFile().getName();
                    }
                    limpiarPantalla();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void limpiarPantalla()  {
        flowPane.getChildren().clear();
        textAreaDescripcion.setText("");
        textFieldPrecio.setText("");
        buttonRegistrar.setDisable(Boolean.TRUE);
    }

    void addImage(File file , FlowPane pane) throws IOException  {
        Path patha = Paths.get(file.getAbsolutePath());
        Path pathb = Paths.get("d:\\REPOSITORIO\\"+file.getName());
        Files.move(patha, pathb , StandardCopyOption.REPLACE_EXISTING );
        //Files.delete(patha);
        //Path path = Paths.get(file.getAbsolutePath());
        Image img = new Image(new FileInputStream(pathb.toFile()));
        ImageView imageView = new ImageView();
        imageView.setImage( img );
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);
        imageView.setUserData(pathb);
        pane.getChildren().add(imageView);
        buttonRegistrar.setDisable(Boolean.FALSE);
    }

    private void mouseDragDropped(final DragEvent e) {
        db = e.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            success = true;
            // Only get the first file from the list
            final File file = db.getFiles().get(0);
            //Platform.runLater(new Runnable() {
              //  @Override
                //public void run() {
                    try {
                        if (!(flowPane.getChildren().size() < 3) ) {
                            flowPane.getChildren().remove(0);
                        }
                        addImage( file , flowPane);
                        db.getFiles().clear();
                    } catch ( IOException ex) {
                        LOGGER.error(RegistrarProductoController.class.getName() , ex);
                    }
                //}
            //});
        }
        e.setDropCompleted(success);
        e.consume();
    }

    private  void mouseDragOver(final DragEvent e) {
        final Dragboard db = e.getDragboard();

        final boolean isAccepted = db.getFiles().get(0).getName().toLowerCase().endsWith(".png")
                || db.getFiles().get(0).getName().toLowerCase().endsWith(".jpeg")
                || db.getFiles().get(0).getName().toLowerCase().endsWith(".jpg");

        if (db.hasFiles()) {
            if (isAccepted) {
                flowPane.setStyle("-fx-border-color: red;"
                        + "-fx-border-width: 5;"
                        + "-fx-background-color: #C6C6C6;"
                        + "-fx-border-style: solid;");
                e.acceptTransferModes(TransferMode.COPY);
            }
        } else {
            e.consume();
        }
    }

    @Override
    public void setScreenParent(ScreensController screensController) {
        this.screensController = screensController;
    }

    @Override
    public void sendToServerWebSocket(final JSONObject jsonObject) {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                LOGGER.info("sendToServerWebSocket "+(String)jsonObject.get("action"));
                try {
                    screensController.getSession().getBasicRemote().sendText(jsonObject.toString());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    @Override
    public void updateData(JSONObject jsonObject) {

    }

    @Override
    public void receiverByteBuffer(ByteBuffer byteBuffer) {

    }
}
