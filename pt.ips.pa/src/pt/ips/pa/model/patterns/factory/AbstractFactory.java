/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.model.patterns.factory;

import java.io.Serializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Asuspc
 */
public abstract class AbstractFactory implements Serializable{

    private String tema;

    public AbstractFactory(String tema) {
        this.tema = tema;
    }

    public ImageView createPecaImage(String id) {
        switch (id) {
            case "0":
                return new ImageView(new Image(getClass().getResourceAsStream("/temas/0.png")));
            case "*":
                return new ImageView(new Image(getClass().getResourceAsStream("/temas/" + tema + "/4.png")));
            default:
                return new ImageView(new Image(getClass().getResourceAsStream("/temas/" + tema + "/" + id + ".png")));
        }
    }

    public String createFundo() {
        return "-fx-background-image: url('" + getClass().getResource("/temas/" + tema + "/background.png").toExternalForm() + "')";
    }

    public MediaPlayer createMusic() {
        return new MediaPlayer(new Media(getClass().getResource("/temas/Musica/" + tema + ".mp3").toString()));
    }

    public abstract void setStyle(Label label);
}
