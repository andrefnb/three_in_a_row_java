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
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import pt.ips.pa.model.interfaces.Iterator;
import pt.ips.pa.model.jogo.GestorJogo;
import pt.ips.pa.model.jogo.Jogador;
import pt.ips.pa.model.jogo.RankingJogador;

/**
 * FXML Controller class
 *
 * @author Asuspc
 */
public class FXMLRankingWindowController implements Initializable {

    @FXML
    private ListView<String> ListBaseRapido;

    @FXML
    private ListView<String> ListCorridaRapido;

    @FXML
    private ListView<String> ListCorridaNormal;

    @FXML
    private ListView<String> ListBaseNormal;
    
    @FXML
    private AnchorPane sceneRanking;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sceneRanking.setStyle("-fx-background-image: url('" + getClass().getResource("/temas/background.png").toExternalForm() + "')");
    }
    
    public void iniciarRankings(){
        iniciarRanking(ListBaseRapido, GestorJogo.getInstance().getRankingBaseRapido());
        iniciarRanking(ListCorridaRapido, GestorJogo.getInstance().getRankingCorridaRapido());
        iniciarRanking(ListCorridaNormal, GestorJogo.getInstance().getRankingCorridaNormal());
        iniciarRanking(ListBaseNormal, GestorJogo.getInstance().getRankingBaseNormal());
    }
    
    private void iniciarRanking(ListView<String> listaRanking, RankingJogador ranking){
        ArrayList <String> lista  = new ArrayList();
        Iterator<Jogador> it = ranking.getIterator();
        int posicao =1;
        while (it.hasNext()) {
            Jogador jog = it.next();
            lista.add(posicao+"º "+jog.getUsername()+" com "+jog.getPontuacao()+" pontos");
            posicao++;
        }
        listaRanking.setItems(FXCollections.observableArrayList(lista));
    }
}
