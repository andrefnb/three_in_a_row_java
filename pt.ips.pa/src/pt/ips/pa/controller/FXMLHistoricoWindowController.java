/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import pt.ips.pa.model.jogo.GestorJogo;
import pt.ips.pa.model.jogo.HistoricoJogador;
import pt.ips.pa.model.jogo.Jogador;
import pt.ips.pa.model.patterns.IteratorDynamic;

/**
 * FXML Controller class
 *
 * @author Asuspc
 */
public class FXMLHistoricoWindowController implements Initializable {

    @FXML
    private ListView<String> listView;

    @FXML
    private ComboBox<Jogador> comboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    public void iniciarLista() {
        Jogador jogador = comboBox.getSelectionModel().getSelectedItem();
        listView.setItems(FXCollections.observableArrayList(devolveHistorico(jogador.getHistorico())));

    }

    private ArrayList devolveHistorico(HistoricoJogador historico) {
        IteratorDynamic it = historico.getIterator();
        ArrayList<String> lista = new ArrayList();
        while (it.hasNext()) {
            lista.add(it.next().toString());
        }
        return lista;
    }

    public void iniciarCombo() {
        comboBox.setItems(FXCollections.observableArrayList(GestorJogo.getInstance().getLista().getListaJogadores()));
        comboBox.getSelectionModel().selectFirst();
    }

}
