/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import pt.ips.pa.model.jogo.GestorJogo;
import pt.ips.pa.model.jogo.JogoRapido;
import pt.ips.pa.view.JogoView;

/**
 * FXML Controller class
 *
 * @author Luís Mestre
 */
public class FXMLGameWindowController implements Initializable, Observer {

    private JogoView jogoView;

    private Timer timer;

    private int tempo;

    private ControllerMainWindow controlador;

    private boolean jogoIniciou;

    @FXML
    private BorderPane zonaParaTabuleiro;
    @FXML
    private Label labelNome;
    @FXML
    private Label labelPontuacao;
    @FXML
    private Label labelTempo;
    @FXML
    private Label labelJogadasPossiveis;
    @FXML
    private Label labelNumJogadasPossiveis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tempo = 0;
        timer = new Timer();
        jogoIniciou = false;
    }

    public void setControladorPrincipal(ControllerMainWindow controlador) {
        this.controlador = controlador;
        this.controlador.setActionViews(labelNome, labelPontuacao, labelTempo);
        this.controlador.getItemNovoJogo().setDisable(true);
        this.controlador.getItemReturn().setDisable(true);
        this.controlador.getLoggerOptions().setDisable(true);
    }

    public void setJogo() {
        labelNome.setText(GestorJogo.getInstance().getJogador().getUsername());
        iniciarTabuleiro();
        jogoIniciou = true;
    }

    private void iniciarTabuleiro() {
        jogoView = new JogoView(GestorJogo.getInstance().getJogo(), labelPontuacao, controlador, zonaParaTabuleiro);
        GestorJogo.getInstance().getJogo().addObserver(this);
        labelJogadasPossiveis.setOpacity(0.0);
        labelNumJogadasPossiveis.setOpacity(0.0);
        if (GestorJogo.getInstance().getJogo() instanceof JogoRapido) {
            labelJogadasPossiveis.setOpacity(1.0);
            labelNumJogadasPossiveis.setOpacity(1.0);
            jogoView.setJogadas(labelNumJogadasPossiveis);
            jogoView.setLabelsStyle(labelJogadasPossiveis);
            jogoView.setLabelsStyle(labelNumJogadasPossiveis);
        }
        jogoView.setLabelsStyle(labelNome);
        jogoView.setLabelsStyle(labelPontuacao);
        jogoView.setLabelsStyle(labelTempo);
        zonaParaTabuleiro.setCenter(jogoView.getJanela());
        iniciarRelogio();
    }

    public void iniciarRelogio() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (tempo % 10 == 0) {
                            labelPontuacao.setText("" + GestorJogo.getInstance().getJogo().getPontuacao());
                        }
                        labelTempo.setText(String.format("%02d:%02d", tempo / 60, tempo % 60));
                        if (labelNumJogadasPossiveis.getOpacity() == 1.0) {
                            labelNumJogadasPossiveis.setText("" + (20 - ((JogoRapido) GestorJogo.getInstance().getJogo()).getContador()));
                        }
                        tempo++;
                        GestorJogo.getInstance().getJogo().atualizarPontuacao(0);
                    }
                });
            }
        }, 0, 1000);
    }

    public boolean isJogoIniciou() {
        return jogoIniciou;
    }

    public void reiniciar() {
        tempo = 0;
        timer = new Timer();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("Jogada") || arg.equals("Undo") || arg.equals("Fim")) {
            jogoView.atualizarJanela();
            if (arg.equals("Fim")) {
                this.timer.cancel();
                this.controlador.getItemNovoJogo().setDisable(false);
                this.controlador.enableMenu();
                try {
                    jogoView.iniciarJanelaPerdeu();
                } catch (IOException ex) {
                    Logger.getLogger(JogoView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
