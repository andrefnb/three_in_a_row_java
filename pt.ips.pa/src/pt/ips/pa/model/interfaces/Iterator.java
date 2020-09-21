package pt.ips.pa.model.interfaces;

/**
 * Interface que origina a classe IteratorDynamic
 * @author Luis Mestre e Andre Bastos
 * @param <E>
 */
public interface Iterator <E>{
    
    /**
     * Implementa o metodo que retorna um boolean. true caso haja um elemento a
     * seguir, false caso nao haja
     * @return true quando ha um elemento seguinte, false quando nao ha
     */
    public boolean hasNext();
    
    /**
     * Implementa o metodo que devolve o elemento seguinte
     * @return elemento seguinte
     */
    public E next();
}
