/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.model.patterns.factory;

import java.io.Serializable;
import javafx.scene.control.Label;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;

/**
 *
 * @author Luís Mestre
 */
class StevenUniverseFactory extends AbstractFactory implements Serializable{

    public StevenUniverseFactory() {
        super("Steven Universe");
    }
    
    @Override
    public void setStyle(Label label) {
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        Blend blend = new Blend();
        blend.setMode(BlendMode.MULTIPLY);
        blend.setTopInput(shadow);
        blend.setBottomInput(new Glow(0.5));
        label.setEffect(blend);
        label.setTextFill(Color.LAVENDERBLUSH);
    }
}
