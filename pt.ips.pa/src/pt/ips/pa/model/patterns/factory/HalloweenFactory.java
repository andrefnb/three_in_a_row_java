package pt.ips.pa.model.patterns.factory;

import java.io.Serializable;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;

public class HalloweenFactory extends AbstractFactory implements Serializable{

    public HalloweenFactory() {
        super("Halloween");
    }

    @Override
    public void setStyle(Label label) {
        label.setEffect(new Glow(0.5));
        label.setTextFill(Color.ORANGE);
    }
}
