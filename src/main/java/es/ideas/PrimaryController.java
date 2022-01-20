package es.ideas;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class PrimaryController implements Initializable{

    @FXML
    private ToggleButton tgpSp;
    @FXML
    private ToggleButton tgpEn;
    @FXML
    private ToggleButton tgpFr;
    @FXML
    private Button btnAceptar;
    
    private App aplicacionPrincipal;
    @FXML
    private Button btnCancelar;
    
    public void setMainWindow(App main){
        this.aplicacionPrincipal= main;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Muestra los valores de Locale cada vez que se carga la escena
        Locale localeActual = Locale.getDefault();
        System.out.println("Locale Seleccionado: \n" +
                " getLanguage: " + localeActual.getLanguage() +
                " - getCountry: " + localeActual.getCountry() +
                " - getDisplayName: " + localeActual.getDisplayName()+
                "\n - getDisplayVariant: " + localeActual.getDisplayVariant()+
                " - getDisplayCountry: " + localeActual.getDisplayCountry()+
                " - getDisplayLanguage: " + localeActual.getDisplayLanguage() +
                "\n - getISO3Country: " + localeActual.getISO3Country() + 
                " - getISO3Languaje: " + localeActual.getISO3Language() + 
                " - getDisplayScript: " + localeActual.getDisplayScript());
        
        //Crear un ToggleGroup para agrupar los ToggleButton
        //Sólo puede haber uno seleccionado cada vez.
        ToggleGroup tg= new ToggleGroup();
        tg.getToggles().addAll(tgpSp,tgpEn,tgpFr);
        
        /**
         * Listener para cambiar el idioma
         */        
        tg.selectedToggleProperty().addListener((obs,oldValue,newValue)->{
            if (newValue != null ){
               ToggleButton tb = (ToggleButton) newValue.getToggleGroup()
                       .getSelectedToggle();               
               //Se comprueba el valor del Texto del ToggleButton        
               switch (tb.getText()){
                   case "Inglés":
                       Locale.setDefault(Locale.ENGLISH);
                       System.out.println("Has seleccionado idioma INGLÉS");
                       
                       break;  
                   case "Frances":
                       Locale.setDefault(Locale.FRENCH);
                       System.out.println("Has seleccionado idioma FRANCÉS");
                       break;
                   default:
                       Locale.setDefault(new Locale("es"));
                       System.out.println("Has seleccionado idioma ESPAÑOL");                       
               }
               try{
                Parent pane = getFXMLLoader().load();
                App.getPrimaryStage().getScene().setRoot(pane);
               }catch(IOException ieo){                   
               }               
               App.getPrimaryStage().show();
            }
               
        });
    }
    
    private FXMLLoader getFXMLLoader(){
        FXMLLoader loader = new FXMLLoader();
        loader.setResources(ResourceBundle.getBundle("es.ideas.i18n/cadenas",
                Locale.getDefault()));
        loader.setLocation(getClass().getResource("primary.fxml"));
        return loader;
    }

}
