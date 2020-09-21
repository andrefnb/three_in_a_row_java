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
import javafx.scene.control.Label;
import pt.ips.pa.model.jogo.GestorJogo;
import pt.ips.pa.model.jogo.Jogo;

/**
 * FXML Controller class
 *
 * @author Asuspc
 */
public class FXMLPerdeuWindowController implements Initializable {

    @FXML
    private Label labelSpecs;

    private ControllerMainWindow controlador;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Jogo jogo = GestorJogo.getInstance().getJogo();
        labelSpecs.setText("O jogo acabou com " + jogo.getPontuacao() + " pontos!");
    }

    @FXML
    private void voltarJanelaPrincipal() {
        int size = controlador.getZonaJogo().getChildren().size();
        for (int i = size - 1; i > 0; i--) {
            controlador.getZonaJogo().getChildren().remove(i);
        }
        controlador.getButtonIniciarJogo().setDisable(false);
        controlador.getButtonIniciarJogo().setOpacity(1.0);
        controlador.getLoggerOptions().setDisable(false);
    }

    public void setControladorPrincipal(ControllerMainWindow controlador) {
        this.controlador = controlador;
    }
}
