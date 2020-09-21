package pt.ips.pa.view;

import javafx.scene.layout.HBox;
import pt.ips.pa.model.patterns.factory.AbstractFactory;
import pt.ips.pa.model.patterns.factory.GestorFactory;
import pt.ips.pa.model.jogo.LinhaJogo;
import pt.ips.pa.model.jogo.Peca;
import pt.ips.pa.model.patterns.IteratorDynamic;

public class LinhaJogoView{

    private HBox linhaJogo;
    
    private LinhaJogo linha;

    public LinhaJogoView(LinhaJogo linha) {
        this.linha = linha;
        linhaJogo = new HBox();
    }

    public void atualizar(String tema) {
        AbstractFactory factory = GestorFactory.getInstance().getFactory(tema);
        int posicao = preencherEspacos(0, linha.getCapacidade() / 2 - linha.getNrElemEsquerda(), factory);
        IteratorDynamic it = linha.getIterator();
        while (it.hasNext()) {
            linhaJogo.getChildren().add(factory.createPecaImage(((Peca)it.next()).toString()));
            posicao++;
        }
        preencherEspacos(posicao, linha.getCapacidade() / 2 - linha.getNrElemDireita(), factory);
    }

    public int preencherEspacos(int posicao, int nrEspacos, AbstractFactory factory) {
        for (int i = posicao; i < (posicao + nrEspacos); i++) {
            linhaJogo.getChildren().add(factory.createPecaImage("0"));
        }
        return posicao;
    }

    public HBox getLinhaJogo() {
        return linhaJogo;
    }

    public void setLinha(LinhaJogo linha) {
        this.linha = linha;
    }
}
