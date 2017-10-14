package com.recyclothes.fx.controller;

import com.recyclothes.common.dto.CargaFotoProductoDTO;
import com.recyclothes.common.dto.FotoProductoDTO;
import com.recyclothes.common.enums.AccionEnum;
import com.recyclothes.common.utils.Utils;
import com.recyclothes.fx.property.CargaFotoProperty;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

/**
 * Created by Cesar on 19-08-2016.
 */
public class CargaFotosController implements Initializable, ControlledScreen {
    static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(CargaFotosController.class);

    BigInteger paginaActual = BigInteger.ONE;
    ScreensController screensController;
    Deque<CargaFotoProductoDTO> cargaFotoProductoDTODeque = new ArrayDeque<>();
    String rutaRepositorio = "D:\\REPOSITORIO\\";
    final FileChooser fileChooser = new FileChooser();
    @FXML
    TableView tableView;
    @FXML
    ImageView imageView;
    @FXML
    Button buttonEliminar;
    @FXML
    Button buttonPagAnterior;
    @FXML
    Button buttonPagSiguiente;
    @FXML
    Button buttonRevisar;
    @FXML
    Button buttonOpenFile;
    @FXML
    Button buttonLimpiarPantalla;
    Long totalPaginas = 1l;

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

    public void sendImageToServerWebSocket(byte[] bytes) {
        LOGGER.info("sendImageToServerWebSocket");
        try {
            ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
            screensController.getSession().getBasicRemote().sendBinary(byteBuffer);
            //byteBuffer.clear();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void cargarFotoProducto(CargaFotoProductoDTO cargaFotoProductoDTO){
/*
        imageView.setImage(null);
        buttonRevisar.setVisible(Boolean.FALSE);
        buttonEliminar.setUserData(cargaFotoProperty);
        buttonEliminar.setVisible(Boolean.FALSE);
*/
        cargaFotoProductoDTODeque.add(cargaFotoProductoDTO);
        Long idFotoProducto = cargaFotoProductoDTO.getFotoProductoDTO().getIdFotoProducto();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("action", AccionEnum.ADMIN_CARGAR_FOTO_PRODUCTO.name());
        jsonObject.put("idFotoProducto" , idFotoProducto);
        sendToServerWebSocket(jsonObject);
    }

    @Override
    public void receiverByteBuffer(final ByteBuffer byteBuffer) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                CargaFotoProductoDTO cargaFotoProductoDTO = cargaFotoProductoDTODeque.poll();
                cargaFotoProductoDTO.getFotoProductoDTO().setFoto(byteBuffer.array());
                CargaFotoProperty cargaFotoProperty = new CargaFotoProperty(cargaFotoProductoDTO);
                tableView.getItems().add(cargaFotoProperty);
/*
                Image image = new Image(new ByteArrayInputStream(byteBuffer.array()));
                imageView.setImage(image);
                buttonEliminar.setVisible(Boolean.TRUE);
*/
            }
        });
    }

    @Override
    public void updateData(JSONObject jsonMessage) {
        LOGGER.info(jsonMessage.toString()  );
        AccionEnum accionEnum = AccionEnum.valueOf((String)jsonMessage.get("action"));
        switch(accionEnum) {
            case ADMIN_GRABAR_FOTO_PRODUCTO:
                Long size = (Long) jsonMessage.get("size");
                Long idFotoProducto = (Long) jsonMessage.get("idFotoProducto");
                CargaFotoProductoDTO cargaFotoProductoDTO = cargaFotoProductoDTODeque.poll();
                cargaFotoProductoDTO.getFotoProductoDTO().setIdFotoProducto(idFotoProducto);
                CargaFotoProperty cargaFotoProperty = new CargaFotoProperty(cargaFotoProductoDTO);
                tableView.getItems().add(cargaFotoProperty);
                LOGGER.info(cargaFotoProductoDTO);
                LOGGER.info("SIZE = " + size);
                LOGGER.info("-----------------------------------------");
                break;
            case ADMIN_INICIAR_CARGA_FOTOS_PRODUCTOS:
                try {
                    totalPaginas = (Long) jsonMessage.get("totalPaginas");
                    String lisIdFotoProducto = (String) jsonMessage.get("lisIdFotoProducto");
                    JSONArray jsonArray = (JSONArray) new JSONParser().parse(new StringReader(lisIdFotoProducto));
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                        Long idFotoProducto2 = (Long) jsonObject1.get("idFotoProducto");
                        FotoProductoDTO fotoProductoDTO = new FotoProductoDTO();
                        fotoProductoDTO.setIdFotoProducto(idFotoProducto2);
                        CargaFotoProductoDTO cargaFotoProductoDTO1 = new CargaFotoProductoDTO();
                        cargaFotoProductoDTO1.setFotoProductoDTO(fotoProductoDTO);
                        cargarFotoProducto(cargaFotoProductoDTO1);
                    }
                    //tableView.getItems().add(cargaFotoProperty1);
                    LOGGER.info("paginaActual "+paginaActual.intValue());
                    LOGGER.info("totalPaginas "+totalPaginas.intValue());
                    buttonPagAnterior.setVisible(Boolean.TRUE);
                    buttonPagSiguiente.setVisible(Boolean.TRUE);
                    if(paginaActual.intValue() == 1){
                        buttonPagAnterior.setVisible(Boolean.FALSE);
                    }else if(paginaActual.intValue() == totalPaginas.intValue()  )  {
                        buttonPagSiguiente.setVisible(Boolean.FALSE);
                    }
                    buttonLimpiarPantalla.setVisible(Boolean.TRUE);
                }catch(Exception e){
                    LOGGER.error("updateData ADMIN_INICIAR_CARGA_FOTOS_PRODUCTOS" , e);
                }

                break;

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ClickHandler clickHandler = new ClickHandler();
        buttonPagAnterior.setOnMouseClicked(clickHandler);
        buttonPagSiguiente.setOnMouseClicked(clickHandler);
        buttonRevisar.setOnMouseClicked(clickHandler);
        buttonEliminar.setOnMouseClicked(clickHandler);
        buttonLimpiarPantalla.setOnMouseClicked(clickHandler);
        buttonOpenFile.setOnMouseClicked(clickHandler);

        TableColumn<CargaFotoProperty, String> idFotoProductoCol = new TableColumn<>("idFotoProducto");
        idFotoProductoCol.setStyle("-fx-alignment: CENTER-RIGHT;");
        idFotoProductoCol.setCellValueFactory(new PropertyValueFactory("idFotoProducto"));
        idFotoProductoCol.setPrefWidth(tableView.getPrefWidth() / 5);

        TableColumn<CargaFotoProperty, String> nombreFicheroCol = new TableColumn<>("nombreFichero");
        nombreFicheroCol.setStyle("-fx-alignment: CENTER-RIGHT;");
        nombreFicheroCol.setCellValueFactory(new PropertyValueFactory("nombreFichero"));
        nombreFicheroCol.setPrefWidth(tableView.getPrefWidth() / 5);

        TableColumn<CargaFotoProperty, String> anchoCol = new TableColumn<>("ancho");
        anchoCol.setCellValueFactory(new PropertyValueFactory("ancho"));
        anchoCol.setPrefWidth(tableView.getPrefWidth() / 5);

        TableColumn<CargaFotoProperty, String> altoCol = new TableColumn<>("alto");
        altoCol.setCellValueFactory(new PropertyValueFactory("alto"));
        altoCol.setPrefWidth(tableView.getPrefWidth() / 5);

        TableColumn<CargaFotoProperty, String> sizeCol = new TableColumn<>("size");
        sizeCol.setCellValueFactory(new PropertyValueFactory("size"));
        sizeCol.setPrefWidth(tableView.getPrefWidth() / 5);

        tableView.getColumns().setAll(idFotoProductoCol, nombreFicheroCol, anchoCol, altoCol, sizeCol);

        tableView.setRowFactory(new Callback<TableView<CargaFotoProperty>, TableRow<CargaFotoProperty>>() {
            @Override
            public TableRow<CargaFotoProperty> call(TableView<CargaFotoProperty> tableView2) {
                final TableRow<CargaFotoProperty> row = new TableRow<CargaFotoProperty>() {
                    @Override
                    protected void updateItem(CargaFotoProperty cargaFotoProperty, boolean empty) {
                        super.updateItem(cargaFotoProperty, empty);
                    }
                };
                row.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        final int index = row.getIndex();
                        if (row.getTableView().getSelectionModel().getSelectedIndex() == index) {
                            CargaFotoProperty cargaFotoProperty = row.getTableView().getSelectionModel().getSelectedItem();
                            imageView.setImage(null);
                            buttonRevisar.setVisible(Boolean.FALSE);
                            buttonEliminar.setUserData(cargaFotoProperty);
                            //cargarFotoProducto(cargaFotoProperty );
                            Image image = new Image(new ByteArrayInputStream(cargaFotoProperty.getCargaFotoProductoDTO().getFotoProductoDTO().getFoto()));
                            imageView.setImage(image);
                            buttonEliminar.setVisible(Boolean.TRUE);
                        }
                    }
                });
                return row;
            }
        });
    }
    void addImage(File file ) {
        try {
            Path patha = Paths.get(file.getAbsolutePath());
            Path pathb = Paths.get("D:\\REPOSITORIO\\"+file.getName());

            byte[] bytes = Files.readAllBytes(patha);
            BufferedImage image = ImageIO.read(file);
            int width = image.getWidth();
            int height = image.getHeight();
            long fileSizeInBytes = file.length();
            // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
            long fileSizeInKB = fileSizeInBytes / 1024;
        // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
            //long fileSizeInMB = fileSizeInKB / 1024;
            CargaFotoProductoDTO cargaFotoProductoDTO = new CargaFotoProductoDTO();
            FotoProductoDTO fotoProductoDTO = new FotoProductoDTO();
            fotoProductoDTO.setFoto(bytes);
            cargaFotoProductoDTO.setNameFile(file.getName());
            cargaFotoProductoDTO.setAncho(width);
            cargaFotoProductoDTO.setAlto(height);
            cargaFotoProductoDTO.setSize(fileSizeInKB);
            cargaFotoProductoDTO.setFotoProductoDTO(fotoProductoDTO);
            cargaFotoProductoDTODeque.add(cargaFotoProductoDTO);
            sendImageToServerWebSocket(bytes);
            Files.move(patha, pathb , StandardCopyOption.REPLACE_EXISTING );
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void limpiarTabla()  {
        paginaActual = BigInteger.ONE;
        buttonRevisar.setVisible(Boolean.TRUE);
        tableView.getItems().clear();
        imageView.setImage(null);
        buttonEliminar.setVisible(Boolean.FALSE);
        buttonPagAnterior.setVisible(Boolean.FALSE);
        buttonPagSiguiente.setVisible(Boolean.FALSE);
        buttonLimpiarPantalla.setVisible(Boolean.FALSE);
    }

    public void eliminarFoto()  {
        CargaFotoProperty cargaFotoProperty= (CargaFotoProperty)buttonEliminar.getUserData();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("action", AccionEnum.ADMIN_ELIMINAR_FOTO_PRODUCTO.name());
        jsonObject.put("idFotoProducto" , cargaFotoProperty.getCargaFotoProductoDTO().getFotoProductoDTO().getIdFotoProducto());
        sendToServerWebSocket(jsonObject);
        tableView.getItems().remove(cargaFotoProperty);
        buttonEliminar.setVisible(Boolean.FALSE);
        this.imageView.setImage(null);
    }

    public void revisarFotosCargados() {
        buttonRevisar.setVisible(Boolean.FALSE);
        cargaFotosPorPagina(paginaActual.intValue());
    }

    public void cargaFotosPorPagina(Integer paginaSeleccionada)   {
        buttonPagAnterior.setVisible(Boolean.FALSE);
        buttonPagSiguiente.setVisible(Boolean.FALSE);
        tableView.getItems().clear();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("action", AccionEnum.ADMIN_INICIAR_CARGA_FOTOS_PRODUCTOS.name());
        jsonObject.put("paginaSeleccionada", paginaSeleccionada);
        sendToServerWebSocket(jsonObject);
    }
    public void openFileChooser()   {
        buttonLimpiarPantalla.setVisible(Boolean.TRUE);
        imageView.setImage(null);
        tableView.getItems().clear();
        paginaActual = BigInteger.ONE;
        buttonEliminar.setVisible(Boolean.FALSE);
        buttonPagSiguiente.setVisible(Boolean.FALSE);
        buttonPagAnterior.setVisible(Boolean.FALSE);

        List<File> list = fileChooser.showOpenMultipleDialog(tableView.getScene().getWindow());
        if (list != null) {
            rutaRepositorio = "D:\\REPOSITORIO\\"+ Utils.getFecha_Hora_Formato_Fichero(Calendar.getInstance().getTime())+"\\";
            Path newdir = Paths.get(rutaRepositorio);
            try {
                Files.createDirectories(newdir);
            } catch (IOException e) {
                System.err.println(e);
            }
            for (File file : list) {
                addImage( file );
            }
        }
    }

    public class ClickHandler implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent mouseEvent) {
            imageView.setImage(null);
            buttonEliminar.setVisible(Boolean.FALSE);
            Button button = (Button) mouseEvent.getSource();
            if(button.equals(buttonOpenFile)){
                openFileChooser();
            }
            else if(button.equals(buttonRevisar))    {
                revisarFotosCargados();
            }
            else if(button.equals(buttonEliminar))  {
                eliminarFoto();
            }
            else if(button.equals(buttonPagAnterior)){
                paginaActual = paginaActual.subtract(BigInteger.ONE);
                cargaFotosPorPagina(paginaActual.intValue());
            }
            else if(button.equals(buttonPagSiguiente))  {
                paginaActual = paginaActual.add(BigInteger.ONE);
                cargaFotosPorPagina(paginaActual.intValue());
            }
            else if(button.equals(buttonLimpiarPantalla)){
                limpiarTabla();
            }
        }
    }
}
