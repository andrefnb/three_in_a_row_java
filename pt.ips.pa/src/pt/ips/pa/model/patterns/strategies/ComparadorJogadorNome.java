package pt.ips.pa.model.patterns.strategies;

import java.io.Serializable;
import java.util.Comparator;
import pt.ips.pa.model.jogo.Jogador;

/**
 * Classe que implementa a interface Comparator (que ja provem do Java) para
 * comparar dois nomes. 
 * 
 * @author Luis Mestre e Andre Bastos
 */
public class ComparadorJogadorNome implements Comparator<Jogador>, Serializable{

    /**
     * Metodo que verifica se os nomes dos jogadores sao iguais. Caso o sejam
     * retorna 0, caso contrario devolve um número superior ou inferior a 0
     * (depende dos nomes que se comparam)
     * @param jogador1
     * @param jogador2
     * @return o valor da comparacao entre dois nomes
     */
    @Override
    public int compare(Jogador jogador1, Jogador jogador2) {
        return jogador1.getUsername().compareTo(jogador2.getUsername());
    } 
}
