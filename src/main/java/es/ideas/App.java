package es.ideas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        App.primaryStage = stage;
        //Crear una instancia de la clase FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        //Establecer la localización del fichero FXML a cargar
        loader.setLocation(getClass().getResource("primary.fxml"));
        //Establecer la localización del fichero de internacionalización
        // 1 par: nombre fichero properties base
        // 2 par: Locale por defecto
        loader.setResources(ResourceBundle.getBundle("es.ideas.i18n/cadenas",
                Locale.getDefault()));
        Parent raiz = loader.load();
        PrimaryController pc = loader.getController();
        pc.setMainWindow(this);
        
        Scene scene = new Scene(raiz);
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getPrimaryStage(){
        return primaryStage;
    }
    
    public static void main(String[] args) {
        launch();
    }

}