package it.polito.tdp.libretto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import it.polito.tdp.libretto.model.Libretto;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader(App.class.getResource("main.fxml")) ;
    	Parent root = loader.load();
    	Scene scene = new Scene(root) ;
    	
    	Libretto model = new Libretto(); //collega Libretto e Controller
    	Controller controller = loader.getController();
    	controller.setModel(model);

    	stage.setScene(scene);
        stage.show();
        
    }

    public static void main(String[] args) {
        launch();
    }

}