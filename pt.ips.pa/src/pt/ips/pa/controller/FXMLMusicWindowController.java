/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import pt.ips.pa.model.patterns.factory.GestorFactory;

/**
 * FXML Controller class
 *
 * @author Luís Mestre
 */
public class FXMLMusicWindowController implements Initializable {

    @FXML
    private Slider slider;

    @FXML
    private Label titulo;

    @FXML
    private Button buttonPlayPause;

    private MediaPlayer musica;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setMusica(MediaPlayer musica, String tema) {
        if (this.musica != null) {
            this.musica.stop();
        }
        this.musica = musica;
        this.musica.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                FXMLMusicWindowController.this.musica.seek(Duration.ZERO);
            }
        });
        this.musica.play();
        this.musica.volumeProperty().bindBidirectional(slider.valueProperty());
        this.titulo.setText(tema);
    }

    @FXML
    public void buttonAction() {
        if (musica.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            musica.pause();
            buttonPlayPause.setText("Play");
        } else {
            musica.play();
            buttonPlayPause.setText("Pause");
        }
    }
}
