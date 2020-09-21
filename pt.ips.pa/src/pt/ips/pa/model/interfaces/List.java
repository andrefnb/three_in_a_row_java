package pt.ips.pa.model.interfaces;

import pt.ips.pa.model.patterns.IteratorDynamic;

/**
 * Interface que origina a classe TADListLinked
 * @author Luis Mestre e Andre Bastos
 * @param <E>
 */
public interface List <E>{
    
    /**
     * Implementa o metodo isEmpty() que retorna um boolean.
     * @return true se nao tem elementos, false se tem elementos
     */
    public boolean isEmpty();
    
    /**
     * Implementa o metodo size() que devolve o tamanho do conjunto.
     * @return o numero de elementos
     */
    public int size();
    
    /**
     * Implementa o metodo add(int rank, E elem) que recebe como parametros um
     * elemento para adicionar no rank tambem enviado como parametro.
     * @param rank
     * @param elem 
     */
    public void add(int rank, E elem);
    
    /**
     * Implementa o metodo get(int rank) que retorna um elemento de acordo com o
     * parametro rank recebido
     * @param rank 
     * @return o elemento
     */
    public E get(int rank);
    
    /**
     * Implementa o metodo set(int rank, E elem) que recebe como parametros o
     * rank de um elemento e o elemento para o qual ira ser alterado.
     * @param rank 
     * @param elem 
     * @return o elemento que foi alterado
     */
    public E set(int rank, E elem);
    
    /**
     * Implementa o metodo remove(int rank) que recebe como parametro o rank e
     * caso o rank seja valido, elimina o elemento que esta nesse rank
     * @param rank
     * @return o elemento removido
     */
    public E remove(int rank);
    
    /**
     * Implementa o metodo que retorna um Iterator que fara a leitura dos
     * elementos da classe
     * @return um Iterator para fazer a leitura dos elementos
     */
    public IteratorDynamic getIterator();
}
