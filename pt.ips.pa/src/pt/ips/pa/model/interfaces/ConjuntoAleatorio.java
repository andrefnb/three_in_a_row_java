package pt.ips.pa.model.interfaces;

/**
 * Interface que origina a classe TADConjuntoAleatorio.
 * @author Luis Mestre e Andre Bastos
 * @param <E>
 */
public interface ConjuntoAleatorio<E>{
    
    /**
     * Implementa  o metodo size() que devolve o tamanho do conjunto.
     * @return o numero de elementos do conjunto
     */
    public int size();

    /**
     * Implementa o metodo peek() que devolve um elemento aleatorio no conjunto.
     * @return um elemento aleatorio
     */
    public E peek ();
}
