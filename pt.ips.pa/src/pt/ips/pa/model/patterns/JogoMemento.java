package pt.ips.pa.model.patterns;

import java.io.Serializable;
import pt.ips.pa.model.interfaces.EstrategiaPontuacao;
import pt.ips.pa.model.jogo.LinhaJogo;
import pt.ips.pa.model.jogo.Peca;
import pt.ips.pa.model.patterns.strategies.EstrategiaPontuacaoBase;
import pt.ips.pa.model.patterns.strategies.EstrategiaPontuacaoCorrida;

/**
 * Classe que ira guardar todos os atributos do Tabuleiro num momento.
 *
 * @author Luis Mestre e Andre Bastos
 */
public class JogoMemento implements Serializable {

    /**
     * Atributo do tipo LinhaJogo que guarda as linhas do jogo
     */
    private LinhaJogo[] linhas;

    /**
     * Atributos do tipo Peca que irao guardar as pecas que irao ser adicionadas
     * a linha, pela esquerda e pela direita
     */
    private Peca elemEsquerdaAJogar, elemDireitaAJogar;

    /**
     * Atributos do tipo Peca que irao guardar as pecas que irao a jogo apos se
     * adicionar as pecas anteriormente referidas a linha escolhida.
     */
    private Peca proxElemEsquerda, proxElemDireita;

    /**
     * Atributo do tipo int que guarda a pontuacao do jogador que esta a jogar.
     */
    private int pontuacao;

    /**
     * Atributo do tipo EstrategiaPontuacao que ira guardar a maneira de fazer a
     * pontuacao.
     */
    private EstrategiaPontuacao estrategia;

    /**
     * Construtor da classe TabuleiroMemento que recebe como parametros os
     * atributos da classe Tabuleiro num determinado momento.
     *
     * @param linhas
     * @param elemEsquerdaAJogar
     * @param elemDireitaAJogar
     * @param proxElemEsquerda
     * @param proxElemDireita
     * @param pontuacao
     * @param estrategia
     */
    public JogoMemento(LinhaJogo[] linhas, Peca elemEsquerdaAJogar, Peca elemDireitaAJogar,
            Peca proxElemEsquerda, Peca proxElemDireita, int pontuacao, EstrategiaPontuacao estrategia) {
        this.linhas = copiaLinhas(linhas);
        this.elemDireitaAJogar = elemDireitaAJogar;
        this.elemEsquerdaAJogar = elemEsquerdaAJogar;
        this.proxElemDireita = proxElemDireita;
        this.proxElemEsquerda = proxElemEsquerda;
        this.pontuacao = pontuacao;
        this.estrategia = (estrategia instanceof EstrategiaPontuacaoBase) ? new EstrategiaPontuacaoBase() : new EstrategiaPontuacaoCorrida();
    }

    /**
     * Metodo que faz uma copia das linhas do tabuleiro e devolve um array do
     * tipo LinhaJogo com essas linhas.
     *
     * @param linhas
     * @return array com as linhas do jogo
     */
    public LinhaJogo[] copiaLinhas(LinhaJogo[] linhas) {
        LinhaJogo[] linhasNovo = new LinhaJogo[linhas.length];
        for (int i = 0; i < linhasNovo.length; i++) {
            linhasNovo[i] = linhas[i].linhaCopia();
        }
        return linhasNovo;
    }

    /**
     * Metodo que retorna as linhas de jogo guardadas no momento.
     *
     * @return as linhas do jogo guardadas
     */
    public LinhaJogo[] getLinhas() {
        return linhas;
    }

    /**
     * Metodo que retorna a peca que ia adicionar a esquerda da linha no momento
     *
     * @return a peca que ia adicionar a esquerda
     */
    public Peca getElemEsquerdaAJogar() {
        return elemEsquerdaAJogar;
    }

    /**
     * Metodo que retorna a peca que ia adicionar a direita da linha no momento
     *
     * @return a peca que ia adicionar a direita
     */
    public Peca getElemDireitaAJogar() {
        return elemDireitaAJogar;
    }

    /**
     * Metodo que retorna a peca que viria a jogo para adicionar a esquerda no
     * momento
     *
     * @return a peca que viria a jogo para adicionar a esquerda
     */
    public Peca getProxElemEsquerda() {
        return proxElemEsquerda;
    }

    /**
     * Metodo que retorna a peca que viria a jogo para adicionar a direita no
     * momento
     *
     * @return a peca que viria a jogo para adicionar a direita
     */
    public Peca getProxElemDireita() {
        return proxElemDireita;
    }

    /**
     * Metodo que retorna a pontuacao guardada no momento
     *
     * @return a pontuacao do momento
     */
    public int getPontuacao() {
        return pontuacao;
    }

    /**
     * Metodo que devolva a estrategia de pontuacao do jogo no momento
     *
     * @return estrategia de pontuacao do jogo
     */
    public EstrategiaPontuacao getEstrategia() {
        return estrategia;
    }
}
