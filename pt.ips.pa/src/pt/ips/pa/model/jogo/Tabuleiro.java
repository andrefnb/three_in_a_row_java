package pt.ips.pa.model.jogo;

import java.io.Serializable;

/**
 * Classe Tabuleiro e a classe que ira guardar tudo o que estiver relativo a
 * manipulacao das linhas que contem o jogo.
 *
 * @author Luís Mestre e Andre Bastos
 */
public class Tabuleiro implements Serializable{

    /**
     * Atributo do tipo LinhaJogo que e um array onde tera as linhas do jogo
     */
    private LinhaJogo[] linhas;

    /**
     * Construtor da classe Tabuleiro que recebe como parametros o numero de
     * linhas do jogo e o tamanho das mesmas. De seguida cria o numero de linhas
     * de acordo com a capacidade dada.
     * @param nrLinhas
     * @param tamanhoLinhas
     */
    public Tabuleiro(int nrLinhas, int tamanhoLinhas) {
        linhas = new LinhaJogo[nrLinhas];
        for (int i = 0; i < nrLinhas; i++) {
            linhas[i] = new LinhaJogo(tamanhoLinhas);
        }
    }
    
    /**
     * Metodo que recebe como parametro a posicao de uma linha no atributo
     * linhas e adiciona as pecas que estao guardadas nos atributos
     * elemEsquerdaAJogar e elemDireitaAJogar. Depois de as ter adicionado a
     * pontuacao do jogador e atualizada. Caso as pecas tenham sido adicionadas
     * com sucesso sera retornado true, caso nao tenham sido adicionadas sera
     * retornado false.
     *
     * @param indice
     * @param pecaEsquerda
     * @param pecaDireita
     * @return true caso tenha adicionado as pecas, false caso contrario
     */
    public int addElements(int indice, Peca pecaEsquerda, Peca pecaDireita) {
        int pecasDestruidas = 0;
        if (pecaEsquerda instanceof PecaEspecial || pecaDireita instanceof PecaEspecial) {
            pecasDestruidas += linhas[indice].clear();
        } else {
            pecasDestruidas += linhas[indice].add(pecaEsquerda, pecaDireita);
        }
        return pecasDestruidas;
    }

    /**
     * Metodo que devolve as linhas do tabuleiro.
     *
     * @return as linhas do tabuleiro
     */
    public LinhaJogo[] getLinhas() {
        return linhas;
    }

    /**
     * Metodo que muda as linhas todas do tabuleiro. Mais usada para quando se
     * faz um undo de jogada.
     * @param linhas 
     */
    public void setLinhas(LinhaJogo[] linhas) {
        this.linhas = linhas;
    }
}
