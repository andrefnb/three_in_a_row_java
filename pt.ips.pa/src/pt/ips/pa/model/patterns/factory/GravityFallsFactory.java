/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.model.patterns.factory;

import java.io.Serializable;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;

/**
 *
 * @author Luís Mestre
 */
public class GravityFallsFactory extends AbstractFactory implements Serializable{

    public GravityFallsFactory() {
        super("Gravity Falls");
    }
    
    @Override
    public void setStyle(Label label) {
        label.setEffect(new Glow(0.5));
        label.setTextFill(Color.MEDIUMAQUAMARINE);
    }
}
