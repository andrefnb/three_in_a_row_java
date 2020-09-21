package pt.ips.pa.model.jogo;

import java.io.Serializable;

/**
 * Classe Peca que ira representar as pecas que vao estar nas linhas do
 * tabuleiro.
 * 
 * @author Luis Mestre e Andre Bastos
 * @param <E>
 */
public class Peca <E> implements Serializable{
    
    /**
     * Atributo do tipo generico que guarda a identificacao da peca
     */
    private E identificacao;

    /**
     * Construtor da classe que recebe como parametro um Object que ira ser
     * guardado no atributo da classe.
     * 
     * @param identificacao
     */
    public Peca(E identificacao) {
        this.identificacao = identificacao;
    }

    /**
     * Metodo que retorna o valor do atributo da classe.
     * 
     * @return o valor do atributo
     */
    public E getIdentificacao() {
        return identificacao;
    }
    
    /**
     * Metodo que faz uma deepCopy da peca
     * 
     * @return copia da peca
     */
    public Peca copiaPeca(){
        return new Peca(identificacao);
    }

    /**
     * Metodo que devola uma string com a identificacao da peca
     * 
     * @return identificacao da peca em String 
     */
    @Override
    public String toString() {
        return identificacao +"";
    }
}
