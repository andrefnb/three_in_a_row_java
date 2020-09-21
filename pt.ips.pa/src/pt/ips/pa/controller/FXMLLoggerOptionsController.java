/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import pt.ips.pa.model.jogo.GestorJogo;
import pt.ips.pa.model.patterns.observers.FimJogo;
import pt.ips.pa.model.patterns.observers.IniciarJogo;
import pt.ips.pa.model.patterns.observers.Jogada;

/**
 * FXML Controller class
 *
 * @author Luís Mestre
 */
public class FXMLLoggerOptionsController implements Initializable {

    @FXML
    private Button buttonConfirmar;

    @FXML
    private CheckBox opcaoJogadas;

    @FXML
    private CheckBox opcaoFim;

    @FXML
    private CheckBox opcaoInicio;
    
    private ControllerMainWindow controlador;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private ArrayList<Observer> listaObservers() {
        ArrayList<Observer> lista = new ArrayList();
        if (opcaoJogadas.isSelected()) {
            lista.add(new Jogada());
        }
        if (opcaoFim.isSelected()) {
            lista.add(new FimJogo());
        }
        if (opcaoInicio.isSelected()) {
            lista.add(new IniciarJogo());
        }
        return lista;
    }

    public void addObservers() {
        ArrayList<Observer> lista = listaObservers();
        for (Observer obs : lista) {
            GestorJogo.getInstance().getJogo().addObservador(obs);
        }
        GestorJogo.getInstance().getJogo().notifyInicio();
    }

    @FXML
    private void closeLoggerOptions() {
        controlador.voltarAtras();
    }

    public void setControladorPrincipal(ControllerMainWindow controlador) {
        this.controlador = controlador;
    }
}
