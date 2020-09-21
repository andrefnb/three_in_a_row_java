package pt.ips.pa.view;

import javafx.scene.layout.VBox;
import pt.ips.pa.model.jogo.Tabuleiro;

public class TabuleiroView extends VBox {

    private final Tabuleiro tabuleiro;

    private final LinhaJogoView[] linhasView;

    private final int numeroLinhas;

    public TabuleiroView(Tabuleiro tabuleiro) {
        super();
        this.tabuleiro = tabuleiro;
        this.numeroLinhas = tabuleiro.getLinhas().length;
        this.linhasView = new LinhaJogoView[tabuleiro.getLinhas().length];
        for (int i = 0; i < tabuleiro.getLinhas().length; i++) {
            linhasView[i] = new LinhaJogoView(tabuleiro.getLinhas()[i]);
            super.getChildren().add(linhasView[i].getLinhaJogo());
        }
    }

    public void atualizar(String tema) {
        for (int i = 0; i < tabuleiro.getLinhas().length; i++) {
            linhasView[i].atualizar(tema);
            super.getChildren().set(i, linhasView[i].getLinhaJogo());
        }
    }

    public int getNumeroLinhas() {
        return numeroLinhas;
    }

    public LinhaJogoView[] getLinhasView() {
        return linhasView;
    }
}
