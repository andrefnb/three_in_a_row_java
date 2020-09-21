package pt.ips.pa.model.patterns.strategies;

import java.io.Serializable;
import java.util.Comparator;
import pt.ips.pa.model.jogo.Jogador;

/**
 * Classe ComparadorJogadorPontuacao que implementa a interface Comparator (que
 * ja provem do Java) para comparar duas pontuacoes. 
 * 
 * @author Luis Mestre e Andre Bastos
 */
public class ComparadorJogadorPontuacao implements Comparator <Jogador>, Serializable{

    /**
     * Metodo que compara duas pontuacoes e devolve a sua diferenca
     * @param jogador1
     * @param jogador2
     * @return diferenca entre duas pontuacoes
     */
    @Override
    public int compare(Jogador jogador1, Jogador jogador2) {
        return jogador1.getPontuacao() - jogador2.getPontuacao();
    }
}
