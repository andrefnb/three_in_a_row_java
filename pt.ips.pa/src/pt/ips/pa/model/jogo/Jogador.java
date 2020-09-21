package pt.ips.pa.model.jogo;

import java.io.Serializable;

/**
 * Classe Jogador que ira representar o jogador que for jogar.
 *
 * @author Luis Mestre e Andre Bastos
 */
public class Jogador implements Serializable {

    /**
     * Atributo do tipo String que guarda o username do jogador.
     */
    private String username;

    /**
     * Atributo do tipo int que guarda a ultima pontuacao do jogador.
     */
    private int pontuacao;

    /**
     * Atributo do tipo HistoricoJogador que guarda o historico do jogador.
     */
    private HistoricoJogador historico;

    /**
     * Atributo do tipo int que guarda a pontuacaoMaxima do jogador.
     */
    private int pontuacaoMaxima;

    /**
     * Atributo do tipo int que guarda o tempo de jogo total do jogador.
     */
    private int tempoTotal;

    /**
     * Atributo do tipo int que guarda o numero de jogos feitos pelo jogador.
     */
    private int numeroJogos;

    /**
     * Construtor da classe jogador que recebe como parametros uma String
     * e guarda-o no atributo username. Inicia tambem os restantes atributos a 0
     * e o historico com uma capacidade maxima de 30 registos.
     *
     * @param username
     */
    public Jogador(String username) {
        this.username = username;
        this.historico = new HistoricoJogador(30);
        this.pontuacao = this.numeroJogos = this.tempoTotal = this.pontuacaoMaxima = 0;
    }

    /**
     * Metodo que retorna o valor da pontuacao do jogador.
     *
     * @return a pontuacao
     */
    public int getPontuacao() {
        return pontuacao;
    }

    /**
     * Metodo que retorna o username do jogador.
     *
     * @return o username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Metodo que adiciona as informacoes que provêem do jogo para o historico.
     * De seguida atualiza os atributos tempoTotal e numeroJogos;
     * 
     * @param jogo
     */
    public void add(Jogo jogo) {
        historico.add(jogo);
        setPontuacao(jogo.getPontuacao());
        tempoTotal += (int) jogo.getTime();
        numeroJogos++;
    }

    /**
     * Metodo que retorna o historico do jogador.
     *
     * @return o historico
     */
    public HistoricoJogador getHistorico() {
        return historico;
    }

    /**
     * Metodo que atualiza a pontuacao do jogador e tambem a pontuacao maxima.
     *
     * @param pontuacao
     */
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
        setPontuacaoMaxima(pontuacao);
    }

    /**
     * Metodo que atualiza a pontuacao maxima do jogador, caso a nova pontuacao
     * seja superior a pontuacao maxima anterior.
     * 
     * @param pontuacao 
     */
    public void setPontuacaoMaxima(int pontuacao) {
        this.pontuacaoMaxima = (pontuacao > pontuacaoMaxima) ? pontuacao : pontuacaoMaxima;
    }

    public void setTempoTotal(int tempoTotal) {
        this.tempoTotal = tempoTotal;
    }

    public void setNumeroJogos(int numeroJogos) {
        this.numeroJogos = numeroJogos;
    }

    /**
     * Metodo que retorna a pontuacao maxima.
     * 
     * @return a pontuacao maxima
     */
    public int getPontuacaoMaxima() {
        return pontuacaoMaxima;
    }

    /**
     * Metodo que retorna o numero de jogos.
     * 
     * @return o numero de jogos 
     */
    public int getNumeroJogos() {
        return numeroJogos;
    }

    /**
     * Metodo que retorna o tempo medio do jogador ao dividir o tempoTotal pelo
     * numeroJogos.
     * 
     * @return o tempo medio 
     */
    public int getTempoMedio() {
        return tempoTotal / numeroJogos;
    }

    public int getTempoTotal() {
        return tempoTotal;
    }

    @Override
    public String toString() {
        return username;
    }
}
