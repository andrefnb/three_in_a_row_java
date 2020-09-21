package pt.ips.pa.model.interfaces;

import pt.ips.pa.model.exceptions.TADOutofBoundsException;
import pt.ips.pa.model.patterns.IteratorDynamic;

/**
 * Interface que origina a classe TADRanking
 * @author Luis Mestre e Andre Bastos
 * @param <E>
 */
public interface Ranking <E>{
    
    /**
     * Implementa o metodo isEmpty() que retorna um boolean.
     * true quando a TAD nao tem elementos e false quando tem pelo menos 1
     * @return true se nao tem elementos, false se tem elementos
     */
    public boolean isEmpty();
    
    /**
     * Implementa o metodo size() que devolve o tamanho do conjunto.
     * @return o numero de elementos
     */
    public int size();
    
    /**
     * Implementa o metodo add(E elem) que recebe como parametros um elemento
     * para adicionar, e coloca-o na sua respetiva posicao, caso seja possível.
     * @param element o elemento que se adiciona
     * @throws TADOutofBoundsException
     */
    public void add(E element)throws TADOutofBoundsException;
    
    /**
     * Implementa o metodo get(int rank) que retorna um elemento de acordo com o
     * parametro rank recebido
     * @param rank o rank do elemento
     * @return elemento
     * @throws TADOutofBoundsException 
     */
    public E get(int rank)throws TADOutofBoundsException;
    
    /**
     * Metodo que apaga todos os elementos existentes na TAD
     */
    public void reset();
    
    /**
     * Implementa o metodo que retorna um Iterator que fara a leitura dos
     * elementos da classe
     * @return um Iterator para fazer a leitura dos elementos
     */
    public IteratorDynamic getIterator();
}
