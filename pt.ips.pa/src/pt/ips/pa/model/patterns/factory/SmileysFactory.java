package pt.ips.pa.model.patterns.factory;

import java.io.Serializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class SmileysFactory extends AbstractFactory implements Serializable{

    public SmileysFactory() {
        super("Smileys");
    }

    @Override
    public void setStyle(Label label) {
        //label.setEffect(glow);
        label.setTextFill(Color.ROYALBLUE);
    }
}
