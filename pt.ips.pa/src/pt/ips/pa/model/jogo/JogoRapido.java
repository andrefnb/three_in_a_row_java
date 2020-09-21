package pt.ips.pa.model.jogo;

import java.io.Serializable;
import pt.ips.pa.model.exceptions.TADFullException;
import pt.ips.pa.model.interfaces.EstrategiaPontuacao;
import pt.ips.pa.model.tads.TADConjuntoAleatorio;

/**
 * Classe JogoRapido que extende da classe Jogo. Irá representar o jogo que irá
 * ter um limite de jogadas para se fazer.
 *
 * @author Luís Mestre e André Bastos
 */
public class JogoRapido extends Jogo implements Serializable {

    /**
     * Atributo do tipo int que guarda o limite de jogadas que se pode fazer.
     */
    public static final int LIMITEJOGADAS = 20;

    /**
     * Atributo do tipo int que irá guardar o número da jogada feita.
     */
    private int contador;

    /**
     * Construtor da classe que recebe como parâmetros o jogador e o tabuleiro
     * do jogo. Inicia também o contador a 0.
     *
     * @param jogador
     * @param tabuleiro
     * @param conjunto
     * @param estrategia
     */
    public JogoRapido(Jogador jogador, Tabuleiro tabuleiro, TADConjuntoAleatorio conjunto, EstrategiaPontuacao estrategia) {
        super(jogador, tabuleiro, conjunto, estrategia);
        this.contador = 0;
    }

    /**
     * Método jogada redefinada para conseguir adaptar o jogo à nova regra (o
     * limite de jogadas).
     *
     * @param indice
     */
    @Override
    public void jogada(int indice) {
        System.out.println("Jogada número - " + ++contador);
        super.jogada(indice);
        if (contador == LIMITEJOGADAS) {
            setPerdeu(true);
        }
    }
    
    public void undo(){
        contador--;
    }

    public int getContador() {
        return contador;
    }
}
