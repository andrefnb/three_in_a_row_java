package pt.ips.pa.model.patterns.factory;

import java.io.Serializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class WarFactory extends AbstractFactory implements Serializable{

    public WarFactory() {
        super("War");
    }

    @Override
    public void setStyle(Label label) {
        DropShadow shadow = new DropShadow();
        shadow.setOffsetY(3.0f);
        shadow.setColor(Color.color(0.4f, 0.4f, 0.4f));
        label.setEffect(shadow);
        label.setTextFill(Color.LAVENDER);
    }
}
