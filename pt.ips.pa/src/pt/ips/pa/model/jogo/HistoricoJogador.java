package pt.ips.pa.model.jogo;

import java.io.Serializable;
import java.util.Observable;
import pt.ips.pa.model.exceptions.TADEmptyException;
import pt.ips.pa.model.patterns.IteratorDynamic;
import pt.ips.pa.model.patterns.strategies.EstrategiaPontuacaoBase;
import pt.ips.pa.model.tads.TADHistorico;
import pt.ips.pa.model.tads.TADStackLinked;

/**
 * Classe HistoricoJogador que guarda as informacoes do jogador quando acaba
 * um jogo
 * 
 * @author Luis Mestre e Andre Bastos
 */
public class HistoricoJogador implements Serializable{

    /**
     * Atributo do tipo TADHistorico que vai guardar elementos do tipo
     * ElementoHistorico 
     */
    private TADHistorico<ElementoHistorico> historico;

    /**
     * Construtor da classe que inicia o atributo com o parametro que recebe.
     * @param capacidade
     */
    public HistoricoJogador(int capacidade) {
        historico = new TADHistorico<>(capacidade);
    }

    /**
     * Metodo que devolve o numeros de elementos que estao guardados no 
     * historico
     * @return numero de elementos do historico
     */
    public int size() {
        return historico.size();
    }

    /**
     * Metodo que indica se o historico esta vazio
     * @return true se estiver vazio e false se verificar o contrario
     */
    public boolean isEmpty() {
        return historico.isEmpty();
    }

    /**
     * Metodo que adiciona ao historico um elemento do tipo Jogo
     * @param jogo
     */
    public void add(Jogo jogo) {
        historico.add(new ElementoHistorico(jogo));
    }

    /**
     * Metodo que devolve o ultimo elemento adicionado no historico.
     * @return do ultimo ElementoHistorico adicionado
     */
    public ElementoHistorico peek() {
        return historico.peek();
    }

    /**
     * Metodo que devolve o tipo de jogo guardado no ultimo ElementoHistorico
     * adicionado
     * @return uma String que representa o tipo de jogo
     */
    public String getTipoDeJogo() {
        return peek().tipoDeJogo;
    }

    /**
     * Metodo que devolve a estrategia usada para calcular a pontuacao
     * @return String que representa a estrategia usada
     */
    public String getEstrategiaPontuacao() {
        return peek().estrategiaPontuacao;
    }

    /**
     * Metodo que devolve a duracao do ultimo jogo jogado
     * @return duracao do ultimo jogo
     */
    public String getDuracaoDoJogo() {
        return peek().duracaoDoJogo;
    }

    /**
     * Metodo que devolve os elementos do historico de acordo com o número de
     * dias enviado
     *
     * @param days
     * @return os elementos de n dias atras ate agora
     * @throws TADEmptyException
     */
    public TADStackLinked<ElementoHistorico> getHistoricoDays(int days) throws TADEmptyException {
        return historico.getHistoricoDays(days);
    }

    /**
     * Metodo que devolve os elementos do historico de acordo com o mês em
     * especifico enviado
     *
     * @param month
     * @return os elementos que foram adicionados no mês enviado
     */
    public TADStackLinked<ElementoHistorico> getHistoricoFromMonth(int month) {
        return historico.getHistoricoFromMonth(month);
    }

    /**
     * Metodo que devolve os elementos do historico de acordo com uma data em
     * especifico (dia, mes e ano).
     *
     * @param dia
     * @param mes
     * @param ano
     * @return os elementos adicionados nessa data
     * @throws TADEmptyException
     */
    public TADStackLinked<ElementoHistorico> getHistoricoFromDate(int dia, int mes, int ano) throws TADEmptyException {
        return historico.getHistoricoFromDate(dia, mes, ano);
    }

    /**
     * Metodo que retorna um Iterator que fara a leitura dos elementos da classe
     *
     * @return um Iterator para fazer a leitura dos elementos.
     */
    public IteratorDynamic getIterator() {
        return historico.getIterator();
    }

    /**
     * Inner classe protected ElementoHistorico que guarda todas as informacoes necessarias 
     * num elemento do historico
     */
    protected  class ElementoHistorico implements Serializable{

        /**
         * Atributo que guarda o jogador que jogou
         */
        private int pontuacao;

        /**
         * Atributo que guarda o tipo de jogo jogado
         */
        private String tipoDeJogo;

        /**
         * Atributo que devolve a estrategia de pontuacao usada no jogo
         */
        private String estrategiaPontuacao;

        /**
         * Atributo que guarda a duracao do jogo jogado
         */
        private String duracaoDoJogo;

        /**
         * Construtor da classe que recebe um jogo e guarda todas as informacoes 
         * necessarias para se guardar num elemento de historico
         * @param jogo
         */
        public ElementoHistorico(Jogo jogo) {
            this.pontuacao = jogo.getPontuacao();
            this.tipoDeJogo = (jogo instanceof JogoRapido) ? "Jogo Rapido" : "Jogo Normal";
            this.estrategiaPontuacao = (jogo.getEstrategia() instanceof EstrategiaPontuacaoBase) ? "Estrategia Pontuacao Base" : "Estrategia Pontuacao Corrida";
            this.duracaoDoJogo = String.format("%02d:%02d", jogo.getTime() / 60, jogo.getTime() % 60);
        }

        /**
         * Metodo toString que devolve uma string com as informacoes sobre o 
         *  adicionado
         * @return String com informacoes sobre o jogo
         */
        @Override
        public String toString() {
            return String.format("O jogador fez %d pontos no %s atraves da %s e demorou %s", pontuacao, tipoDeJogo, estrategiaPontuacao, duracaoDoJogo);
        }
    }
}
