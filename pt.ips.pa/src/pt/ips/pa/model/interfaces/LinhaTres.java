package pt.ips.pa.model.interfaces;

import pt.ips.pa.model.exceptions.*;
import pt.ips.pa.model.patterns.IteratorDynamic;

/**
 * Interface que origina a classe TADLinhaTres.
 * @author Luis Mestre e Andre Bastos
 * @param <E>
 */
public interface LinhaTres <E>{
    
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
     * Implementa o metodo addFirst() que adiciona um elemento na primeira
     * posicao caso haja espaco.
     * @param element 
     * @throws TADFullException
     */
    public void addFirst(E element)throws TADFullException;
    
    /**
     * Implementa o metodo addLast() que adiciona um elemento na primeira
     * posicao caso haja espaco.
     * @param element 
     * @throws TADFullException 
     */
    public void addLast (E element)throws TADFullException;
    
    /**
     * Implementa o metodo removeFirst() que remove os primeiros 3 elementos
     * caso a TAD nao esteja vazia ou tenha 3 elementos no minimo. De seguida
     * devolve os 3 elementos removidos
     * @return um array com os 3 elementos removidos
     * @throws TADEmptyException
     */
    public E[] removeFirst()throws TADEmptyException;
    
    /**
     * Implementa o metodo removeLast() que remove os ultimos 3 elementos
     * caso a TAD nao esteja vazia ou tenha 3 elementos no minimo. De seguida
     * devolve os 3 elementos removidos
     * @return um array com os 3 elementos removidos
     * @throws TADEmptyException
     */
    public E[] removeLast()throws TADEmptyException;
    
    /**
     * Implementa o metodo getFirst() que devolve um array com os 3 primeiros
     * elementos caso a TAD nao esteja vazia ou tenha pelo menos 3 elementos.
     * @return os primeiros 3 elementos
     * @throws TADEmptyException
     */
    public E[] getFirst()throws TADEmptyException;
    
    /**
     * Implementa o metodo getFirst() que devolve um array com os 3 ultimos
     * elementos caso a TAD nao esteja vazia ou tenha pelo menos 3 elementos.
     * @return os ultimos 3 elementos
     * @throws TADEmptyException
     */
    public E[] getLast()throws TADEmptyException;
    
    /**
     * Implementa o metodo que retorna um Iterator que fara a leitura dos
     * elementos da classe
     * @return um Iterator para fazer a leitura dos elementos
     */
    public IteratorDynamic getIterator();
}
