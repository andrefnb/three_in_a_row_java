package pt.ips.pa.model.interfaces;

/**
 * Interface que origina as classes EstrategiaPontuacaoBase e
 * EstrategiaPontuacaoCorrida
 * @author Luis Mestre e Andre Bastos
 */
public interface EstrategiaPontuacao {

    /**
     * Implementa o metodo que fara o calculo da pontuacao do jogador de acordo
     * com as pecas destruidas
     * @param pecasDestruidas
     * @return Pontucacao atual com o valor das pecas destruidas no momento 
     */
    public int calcularPontuacao(int pecasDestruidas);
}
