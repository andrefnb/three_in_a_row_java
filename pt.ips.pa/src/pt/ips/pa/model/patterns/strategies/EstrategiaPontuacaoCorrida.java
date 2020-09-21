package pt.ips.pa.model.patterns.strategies;

import java.io.Serializable;
import pt.ips.pa.model.interfaces.EstrategiaPontuacao;

/**
 * Classe implementada da interface EstrategiaPontuacao, onde ira fazer o
 * calculo da pontuacao tendo em conta o tempo que demorou a fazer a jogada,
 * tirando 1 ponto a cada 10 segundos que passou desde o inicio do jogo e
 * adiciona 10 por cada peca destruida.
 * 
 * @author Luis Mestre e Andre Bastos
 */
public class EstrategiaPontuacaoCorrida implements EstrategiaPontuacao , Serializable{

    /**
     * Constante que guarda o valor de cada peca destruida
     */
    private final static int pontuacaoPorPeca = 10;

    /**
     * Atributo do tipo long que guarda o tempo de quando comeca a contar a
     * penalidade
     */
    private long tempo;

    /**
     * Contrutor da classe que inicia o atributo tempo
     */
    public EstrategiaPontuacaoCorrida() {
        this.tempo = System.currentTimeMillis()/1000;
    }

    /**
     * Metodo que calcula a pontuacao das pecas destruidas juntamente com a
     * subtracao da penalidade caso ela exista. No caso de existir penalidade,
     * atualiza o valor do atributo tempo para ter uma recontagem da penalidade
     * a partir desse ponto.
     * 
     * @param pecasDestruidas
     * @return valor das pecas destruidas e juntamente com a penalidade
     */
    @Override
    public int calcularPontuacao(int pecasDestruidas) {
        int penalidade = (int)(System.currentTimeMillis()/1000 - tempo)/10;
        tempo +=  penalidade*10;
        return pecasDestruidas * pontuacaoPorPeca - penalidade;
    }
}
