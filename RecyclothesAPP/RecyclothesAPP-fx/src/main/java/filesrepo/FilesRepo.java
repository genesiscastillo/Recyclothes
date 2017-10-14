/*
 * This proyect is only an example of Drag and Drop with a file.
 * 
 * The file should be copied to the local App directory
 * 
 * visit http://afelipelc.wordpress.com
 * 
 * This example idea is taken on http://www.ex-javamonday.appspot.com/Code/Java/JavaFX/Demonstratesadraganddropfeature.htm
 * 
 * 
 * by @afelipelc
 */
package filesrepo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author afelipelc
 */
public class FilesRepo extends Application { 
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Drag & Drop - AFelipe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}