package pt.ips.pa.model.patterns;

import java.io.Serializable;
import pt.ips.pa.model.interfaces.Iterator;
import pt.ips.pa.model.tads.DNode;

/**
 * Classe IteratorDynamic que implementa a interface Iterator para fazer a
 * leitura dos elementos de um conjunto sem os alterar.
 * 
 * @author Luis Mestre e Andre Bastos
 */
public class IteratorDynamic<E> implements Iterator<E>, Serializable{

    /**
     * Atributo do tipo DNode que guarda o no que se vai ler.
     */
    private DNode <E> pos;

    /**
     * Construtor da classe que recebe como parametro o primeiro no a ser lido e
     * guarda-o no atributo.
     * 
     * @param pos 
     */
    public IteratorDynamic(DNode <E> pos) {
        this.pos = pos;
    }
    
    /**
     * Metodo que retorna um boolean. true caso haja um no a seguir, false caso
     * nao haja.
     * 
     * @return true caso haja um no a seguir, false caso nao haja
     */
    @Override
    public boolean hasNext() {
        if (pos == null) {
            return false;
        }else{
            return pos.getElement() != null;
        }
    }

    /**
     * Metodo que retorna o elemento que esta contido no no que esta guardado no
     * atributo e o atributo passa a guardar o proximo no.
     * 
     * @return o elemento que esta contido no no 
     */
    @Override
    public E next() {
        E element = pos.getElement();
        pos = pos.getNext();
        return element;
    }
}
