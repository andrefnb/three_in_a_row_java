package pt.ips.pa.view;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import pt.ips.pa.controller.ControllerMainWindow;
import pt.ips.pa.controller.FXMLPerdeuWindowController;
import pt.ips.pa.model.jogo.GestorJogo;
import pt.ips.pa.model.patterns.factory.AbstractFactory;
import pt.ips.pa.model.patterns.factory.GestorFactory;
import pt.ips.pa.model.jogo.Jogo;
import pt.ips.pa.model.jogo.JogoRapido;

public class JogoView {

    private final BorderPane janela;
    private final Jogo jogo;
    private TabuleiroView tabuleiroView;
    private final String tema;
    private final ControllerMainWindow controlador;
    private final Label pontuacao;
    private Label jogadas;
    private ImageView im1;
    private ImageView im2;
    private int elemEsquerda;
    private int elemDireita;
    private final AnchorPane rootPane;

    public JogoView(Jogo jogo, Label pontuacao, ControllerMainWindow controlador, BorderPane zona) {
        this.jogo = jogo;
        this.tema = controlador.getTema();
        this.tabuleiroView = new TabuleiroView(jogo.getTabuleiro());
        this.controlador = controlador;
        this.pontuacao = pontuacao;
        this.controlador.getMusicController().setMusica(GestorFactory.getInstance().getFactory(tema).createMusic(), tema);
        this.janela = new BorderPane();
        BorderPane.setMargin(janela, new Insets(10, 10, 10, 10));
        iniciarJogoView();
        zona.getParent().setStyle(GestorFactory.getInstance().getFactory(tema).createFundo());
        this.rootPane = controlador.getAnchorPane();
    }

    private void iniciarJogoView() {
        tabuleiroView.atualizar(tema);
        janela.setCenter(tabuleiroView);
        atualizar();
        setOnActionLinhasJogo();
    }
    
    public final void atualizar() {
        VBox vbox = new VBox(10);

        VBox v1 = pecasParaJogar("Peças a serem jogadas", jogo.getElemEsquerdaAJogar().toString(), jogo.getElemDireitaAJogar().toString(), false);
        VBox v2 = pecasParaJogar("Próximas Peças", jogo.getProxElemEsquerda().toString(), jogo.getProxElemDireita().toString(), true);

        vbox.getChildren().addAll(v1, v2);

        janela.setRight(vbox);
    }

    private VBox pecasParaJogar(String texto, String pecaEsq, String pecaDir, boolean opacity) {
        AbstractFactory factory = GestorFactory.getInstance().getFactory(tema);

        Label label = new Label(texto);
        VBox vbox = new VBox();
        Font f1 = label.getFont();
        label.setFont(Font.font(f1.getName(), FontWeight.BOLD, f1.getSize()));
        factory.setStyle(label);

        HBox hbox = new HBox();
        hbox.getChildren().add(factory.createPecaImage(pecaEsq));
        hbox.getChildren().add(factory.createPecaImage(pecaDir));
        if (opacity) {
            hbox.setOpacity(0.5);
        } else {
            im1 = factory.createPecaImage(pecaEsq);
            im2 = factory.createPecaImage(pecaDir);
        }
        vbox.getChildren().addAll(label, hbox);

        return vbox;
    }

    public void iniciarJanelaPerdeu() throws IOException {
        FXMLLoader fl = controlador.createPane("FXMLPerdeuWindow");
        controlador.getZonaJogo().getChildren().add(fl.getRoot());
        ((FXMLPerdeuWindowController) fl.getController()).setControladorPrincipal(controlador);
        controlador.getItemReturn().setDisable(false);
    }

    private Timeline setAnimation(ImageView image, int comeco, int fim, int jogada) {
        AnchorPane.setTopAnchor(image, 73d + (50 * jogada));
        rootPane.getChildren().add(image);
        image.toFront();
        final Timeline timeline = new Timeline();
        final KeyValue kv1 = new KeyValue(image.xProperty(), comeco);
        final KeyValue kv2 = new KeyValue(image.xProperty(), fim);
        final KeyFrame kf1 = new KeyFrame(Duration.ZERO, kv1);
        final KeyFrame kf2 = new KeyFrame(Duration.millis(500), kv2);
        timeline.getKeyFrames().addAll(kf1, kf2);
        timeline.play();
        return timeline;
    }

    public void atualizarJanela() {
        tabuleiroView = new TabuleiroView(jogo.getTabuleiro());
        tabuleiroView.atualizar(tema);
        janela.setCenter(tabuleiroView);
        atualizar();
        setOnActionLinhasJogo();
        pontuacao.setText("" + GestorJogo.getInstance().getJogo().getPontuacao());
        if (jogadas != null) {
            jogadas.setText("" + (20 - ((JogoRapido) GestorJogo.getInstance().getJogo()).getContador()));
        }
        if (jogo.isPerdeu()) {
            setDisableLinhas(true);
        }
    }

    private void setOnActionLinhasJogo() {
        for (int i = 0; i < getNumeroLinhas(); i++) {
            setOnMouseClicked(tabuleiroView.getLinhasView()[i].getLinhaJogo(), i);
            setOnMouseEntered(tabuleiroView.getLinhasView()[i].getLinhaJogo());
            setOnMouseExited(tabuleiroView.getLinhasView()[i].getLinhaJogo());
        }
    }

    private void setOnMouseClicked(HBox linha, int numero) {
        linha.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                elemDireita = jogo.getTabuleiro().getLinhas()[numero].getNrElemDireita();
                elemEsquerda = jogo.getTabuleiro().getLinhas()[numero].getNrElemEsquerda();
                GestorJogo.getInstance().getCareTaker().saveState(GestorJogo.getInstance().getJogo());
                setAnimation(im1, 0, 213 - ((elemEsquerda) * 50), numero);
                Timeline time2 = setAnimation(im2, 500, 257 + ((elemDireita) * 50), numero);
                setDisableLinhas(true);
                time2.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        rootPane.getChildren().removeAll(im1, im2);
                        setDisableLinhas(false);
                        GestorJogo.getInstance().getJogo().jogada(numero);
                    }
                });
            }
        });
    }

    private void setOnMouseEntered(HBox linha) {
        linha.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                linha.setEffect(new Glow(0.8));
            }
        });
    }

    private void setOnMouseExited(HBox linha) {
        linha.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                linha.setEffect(new Glow(0.0));
            }
        });
    }

    private void setDisableLinhas(boolean disable) {
        for (LinhaJogoView jogoView : tabuleiroView.getLinhasView()) {
            jogoView.getLinhaJogo().setDisable(disable);
        }

    }

    public void setLabelsStyle(Label label) {
        GestorFactory.getInstance().getFactory(tema).setStyle(label);
    }

    public void setJogadas(Label jogadas) {
        this.jogadas = jogadas;
    }

    public BorderPane getJanela() {
        return janela;
    }

    public String getTema() {
        return tema;
    }

    public int getNumeroLinhas() {
        return tabuleiroView.getNumeroLinhas();
    }
}
