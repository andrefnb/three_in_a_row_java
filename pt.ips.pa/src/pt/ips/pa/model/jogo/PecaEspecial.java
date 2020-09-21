package pt.ips.pa.model.jogo;

import java.io.Serializable;

/**
 * Classe PecaEspecial que extende da classe Peca. Ira representar a peca que
 * ira eliminar uma linha de pecas do tabuleiro.
 * 
 * @author Luis Mestre e Andre Bastos
 * @param <E>
 */
public class PecaEspecial <E> extends Peca implements Serializable{

    /**
     * Construtor da classe que recebe como parametro um Object que ira ser
     * guardado no atributo da classe.
     * 
     * @param identificacao
     */
    public PecaEspecial(E identificacao) {
        super(identificacao);
    }

    /**
     * Metodo que retorna uma string com a identificacao da peca
     * 
     * @return identificacao da peca em String 
     */
    @Override
    public String toString() {
        return "*";
    }
}
