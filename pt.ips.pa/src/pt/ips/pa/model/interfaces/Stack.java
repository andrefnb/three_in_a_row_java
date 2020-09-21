package pt.ips.pa.model.interfaces;

import pt.ips.pa.model.exceptions.TADEmptyException;

/**
 * Interface que origina a classe TADStackLinked
 * @author Luis Mestre e Andre Bastos
 * @param <E>
 */
public interface Stack <E>{
    
    /**
     * Implementa o metodo que devolve o numero de elementos adicionados
     * @return numero de elementos
     */
    public int size(); 
    
    /**
     * Implementa o metodo que devolve um boolean. True caso não haja elementos
     * no conjunto false caso haja
     * @return true quando não tem elementos, false quando tem
     */
    public boolean isEmpty(); 
    
    /**
     * Implementa o metodo que devolve o elemento do topo
     * @return o elemento do topo
     * @throws TADEmptyException 
     */
    public E peek() throws TADEmptyException; 
    
    /**
     * Implementa o metodo que adiciona um elemento ao conjunto
     * @param element 
     */
    public void push(E element) ;			
    
    /**
     * Implementa o metodo que devolve o elemento do topo e retira-o do conjunto
     * @return o elemento do topo
     * @throws TADEmptyException 
     */
    public E pop() throws TADEmptyException; 
}
