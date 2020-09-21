/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import pt.ips.pa.model.interfaces.EstrategiaPontuacao;
import pt.ips.pa.model.jogo.GestorJogo;
import pt.ips.pa.model.jogo.Tabuleiro;
import pt.ips.pa.model.patterns.strategies.EstrategiaPontuacaoBase;
import pt.ips.pa.model.patterns.strategies.EstrategiaPontuacaoCorrida;

/**
 * FXML Controller class
 *
 * @author Luís Mestre
 */
public class FXMLOptionsWindowController implements Initializable {

    private static EstrategiaPontuacao estrategia;

    private static Tabuleiro tabuleiro;

    private static boolean jogoNormal;
    
    private ControllerMainWindow controlador;
    
    private FXMLLoggerOptionsController controladorLogger;
    
    @FXML // fx:id="radioButtonBase"
    private RadioButton radioButtonBase; // Value injected by FXMLLoader

    @FXML // fx:id="radioButtonNormal"
    private RadioButton radioButtonNormal; // Value injected by FXMLLoader

    @FXML // fx:id="numLinhas"
    private ComboBox<Integer> numLinhas; // Value injected by FXMLLoader

    @FXML // fx:id="temaEscolhido"
    private ComboBox<String> temaEscolhido; // Value injected by FXMLLoader

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void comecarJogo(ActionEvent event) {
        tabuleiro = new Tabuleiro(numLinhas.getValue(), 10);
        controlador.setTema(temaEscolhido.getSelectionModel().getSelectedItem());
        estrategia = (radioButtonBase.isSelected()) ? new EstrategiaPontuacaoBase() : new EstrategiaPontuacaoCorrida();
        jogoNormal = (radioButtonNormal.isSelected());
        GestorJogo.getInstance().iniciarJogo(tabuleiro, estrategia, jogoNormal);
        controladorLogger.addObservers();
        controlador.getZonaJogo().getChildren().remove(controlador.getZonaJogo().getChildren().size()-1);
        controlador.loadPaneGame();
    }
    
    public void setControladorPrincipal(ControllerMainWindow controlador){
        this.controlador = controlador;
    }
    
    public void setControladorLogger(FXMLLoggerOptionsController controladorLogger){
        this.controladorLogger = controladorLogger;
    }
}
