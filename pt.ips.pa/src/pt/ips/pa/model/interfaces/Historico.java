package pt.ips.pa.model.interfaces;

import pt.ips.pa.model.exceptions.TADEmptyException;
import pt.ips.pa.model.patterns.IteratorDynamic;

/**
 * Interface que origina a classe TADHistorico.
 * @author Luis Mestre e Andre Bastos
 * @param <E>
 */
public interface Historico <E>{
    
    /**
     * Implementa o metodo size() que devolve o tamanho do conjunto.
     * @return numero de elementos
     */
    public int size();

    /**
     * Implementa o metodo isEmpty() que devolve true se estiver vazio ou false
     * caso contrario.
     * @return true se estiver vazio, false se nao estiver vazio
     */
    public boolean isEmpty();

    /**
     * Implementa o metodo add(E element) que adiciona um elemento ao Historico.
     * @param element
     */
    public void add(E element);

    /**
     * Implementa o metodo que devolve o ultimo elemento a ser adicionado
     * @return o ultimo elemento a ser adicionado
     * @throws TADEmptyException
     */
    public E peek() throws TADEmptyException;
    
    /**
     * Implementa o metodo que retorna um Iterator que fara a leitura dos
     * elementos da classe
     * @return um Iterator para fazer a leitura dos elementos
     */
    public IteratorDynamic getIterator();
}
