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
public class PokemonFactory extends AbstractFactory implements Serializable{

    public PokemonFactory() {
        super("Pokémon");
    }
    
    @Override
    public void setStyle(Label label) {
        label.setTextFill(Color.BLUE);
        label.setEffect(new Glow());
    }
    
}
