/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.main;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pt.ips.pa.controller.ControllerMainWindow;
import pt.ips.pa.model.jogo.GestorJogo;

/**
 *
 * @author Luís Mestre
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        String url = "/pt/ips/pa/view/FXMLMain.fxml";
        FXMLLoader fl = new FXMLLoader();
        fl.setLocation(getClass().getResource(url));
        fl.load();
        Parent root = fl.getRoot();
        
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/temas/Halloween/4.png")));
        stage.setTitle("Tres Em Linha");
        stage.setScene(scene);
        stage.show();
        
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                ((ControllerMainWindow)fl.getController()).gravarGestor(GestorJogo.getInstance());
                System.exit(0);
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
