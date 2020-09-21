/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.controller;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import pt.ips.pa.model.jogo.GestorJogo;
import pt.ips.pa.model.jogo.Jogador;
import pt.ips.pa.model.jogo.ListaJogadores;

/**
 * FXML Controller class
 *
 * @author Luís Mestre
 */
public class FXMLLoginController implements Initializable, Observer {

    private ControllerMainWindow controlador;

    @FXML // fx:id="listViewJogadores"
    private ListView<Jogador> listViewJogadores; // Value injected by FXMLLoader
    @FXML // fx:id="nomeJogador"
    private TextField nomeJogador; // Value injected by FXMLLoader

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void createJogador() {
        String nome = this.nomeJogador.getText();
        GestorJogo.getInstance().getLista().criarJogador(nome);
        atualizarListaDeJogadores();
        controlador.gravarGestor(GestorJogo.getInstance());
        listViewJogadores.getSelectionModel().selectLast();
    }

    @FXML
    private void continuar() {
        if (!listViewJogadores.getSelectionModel().isEmpty()) {
            controlador.getZonaJogo().getChildren().remove(controlador.getZonaJogo().getChildren().size() - 1);
            GestorJogo.getInstance().setJogador(listViewJogadores.getSelectionModel().getSelectedItem());
            controlador.loadPaneOptions();
        }
    }

    public void atualizarListaDeJogadores() {
        this.listViewJogadores.setItems(FXCollections.observableArrayList(GestorJogo.getInstance().getLista().getListaJogadores()));
        this.nomeJogador.setText("");
    }

    public ListView<Jogador> getListViewJogadores() {
        return listViewJogadores;
    }

    public void setControladorPrincipal(ControllerMainWindow controlador) {
        this.controlador = controlador;
        atualizarListaDeJogadores();
        GestorJogo.getInstance().getLista().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        GestorJogo.getInstance().setLista((ListaJogadores) o);
    }
}
