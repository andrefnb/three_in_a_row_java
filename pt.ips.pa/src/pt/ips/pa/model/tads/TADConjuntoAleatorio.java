package pt.ips.pa.model.tads;

import java.io.Serializable;
import pt.ips.pa.model.interfaces.ConjuntoAleatorio;
import java.util.Random;

/**
 * Classe TADConjuntoAleatorio que representa um conjunto com um tipo generico
 * de elementos, onde nao podemos alterar o conteúdo mas sim aceder aos
 * elementos de forma aleatoria.
 * @author Luis Mestre e Andre Bastos
 * @param <E>
 */
public class TADConjuntoAleatorio<E> implements ConjuntoAleatorio<E>, Serializable{
    
    /**
     * Atributo do tipo Random
     */
    private static Random random = new Random(); 
    
    /**
     * Atributo do tipo int que guarda o tamanho do conjunto.
     */
    private int size;
    
    /**
     * Array de um tipo generico onde vao ser guardados os elementos do conjunto
     */
    private E[] array;

    /**
     * Construtor do conjunto onde recebe o array de elementos e guarda-o no
     * atributo array.
     * @param array
     */
    public TADConjuntoAleatorio(E[] array) {
        this.size = array.length;
        this.array = array;
    }

    /**
     * Metodo que devolve o numero de elementos.
     * @return o tamanho do conjunto
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Metodo que devolve um elemento do conjunto aleatoriamente.
     * @return um elemento aleatorio
     */
    @Override
    public E peek() {
        return array[calcAleatorio()];
    }
    
    /**
     * Metodo que devolve um inteiro aleatoriamente tendo em conta o tamanho do
     * conjunto.
     * @return inteiro aleatorio
     */
    private int calcAleatorio() {
        return random.nextInt(size());
    }
}
