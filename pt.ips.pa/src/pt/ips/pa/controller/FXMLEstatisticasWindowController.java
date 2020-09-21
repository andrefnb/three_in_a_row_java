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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import pt.ips.pa.model.jogo.GestorJogo;
import pt.ips.pa.model.jogo.Jogador;
import pt.ips.pa.model.jogo.RankingJogador;
import pt.ips.pa.model.patterns.IteratorDynamic;

/**
 *
 * @author Luís Mestre
 */
public class FXMLEstatisticasWindowController implements Initializable {

    @FXML // fx:id="pieChartPM"
    private PieChart pieChartPM; // Value injected by FXMLLoader

    @FXML // fx:id="pieChartNumJogos"
    private PieChart pieChartNumJogos; // Value injected by FXMLLoader

    @FXML // fx:id="pieChartTMJ"
    private PieChart pieChartTMJ; // Value injected by FXMLLoader

    @FXML // fx:id="sceneStatistics"
    private AnchorPane sceneStatistics; // Value injected by FXMLLoader

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void setStatisticsNormalBase() {
        ArrayList<Jogador> top10 = getTop10(GestorJogo.getInstance().getRankingBaseNormal());
        addDataPM(top10);
        addDataTMJ(top10);
        addDataNumJogos(top10);
        //depois acrescentar as restantes informações
    }

    public void setStatisticsNormalCorrida() {
        ArrayList<Jogador> top10 = getTop10(GestorJogo.getInstance().getRankingCorridaNormal());
        addDataPM(top10);
        addDataTMJ(top10);
        addDataNumJogos(top10);
        //depois acrescentar as restantes informações
    }

    public void setStatisticsRapidoBase() {
        ArrayList<Jogador> top10 = getTop10(GestorJogo.getInstance().getRankingBaseRapido());
        addDataPM(top10);
        addDataTMJ(top10);
        addDataNumJogos(top10);
        //depois acrescentar as restantes informações
    }

    public void setStatisticsRapidoCorrida() {
        ArrayList<Jogador> top10 = getTop10(GestorJogo.getInstance().getRankingCorridaRapido());
        addDataPM(top10);
        addDataTMJ(top10);
        addDataNumJogos(top10);
        //depois acrescentar as restantes informações
    }

    private ArrayList<Jogador> getTop10(RankingJogador ranking) {
        ArrayList<Jogador> lista = new ArrayList();
        IteratorDynamic it = ranking.getIterator();
        int i = 0;
        while (it.hasNext() && i < 10) {
            Jogador jogador = (Jogador) it.next();
            lista.add(jogador);
            i++;
        }
        return lista;
    }

    private void addDataPM(ArrayList<Jogador> lista) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (int i = 0; i < lista.size(); i++) {
            pieChartData.add(new PieChart.Data(identificador(i + 1, lista.get(i)), lista.get(i).getPontuacaoMaxima()));
        }
        pieChartPM.setData(pieChartData);
        
        showValues(pieChartPM,"%d pts",false);
    }

    private void addDataTMJ(ArrayList<Jogador> lista) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (int i = 0; i < lista.size(); i++) {
            pieChartData.add(new PieChart.Data(identificador(i + 1, lista.get(i)), lista.get(i).getTempoMedio()));
        }
        pieChartTMJ.setData(pieChartData);
        showValues(pieChartTMJ,"%02d:%02d", true);
    }

    private void addDataNumJogos(ArrayList<Jogador> lista) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("");
            pieChartData.add(new PieChart.Data(identificador(i + 1, lista.get(i)), lista.get(i).getNumeroJogos()));
        }
        pieChartNumJogos.setData(pieChartData);
        showValues(pieChartNumJogos,"%d jogos", false);
    }

    private void showValues(PieChart pie, String valor, boolean tempo) {
        final Label caption = new Label("");
        caption.setOpacity(1.0);
        caption.setTextFill(Color.WHITE);
        caption.setStyle("-fx-font: 24 arial;");
        for (final PieChart.Data data : pie.getData()) {
            data.getNode().setOnMousePressed((MouseEvent e) -> {
                caption.setTranslateX(e.getSceneX());
                caption.setTranslateY(e.getSceneY());
                if (tempo) {
                    caption.setText(String.format(valor, (int) data.getPieValue() / 60, (int) data.getPieValue() % 60));
                }else{
                    caption.setText(String.format(valor, (int) data.getPieValue()));
                }
                sceneStatistics.getChildren().add(caption);
            });
            data.getNode().setOnMouseReleased((MouseEvent e) -> {
                sceneStatistics.getChildren().remove(sceneStatistics.getChildren().size()-1);
            });
        }
    }

    private String identificador(int pos, Jogador jogador) {
        return pos + "º " + jogador.getUsername();
    }

}
