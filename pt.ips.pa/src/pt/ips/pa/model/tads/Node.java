package pt.ips.pa.model.tads;

import java.io.Serializable;

/**
 * Classe Node que ira ser um no simples que ira conter um elemento e o seu
 * proximo no para garantir uma boa pesquisa.
 * 
 * @author Luis Mestre e Andre Bastos
 */
public class Node <E> implements Serializable{

    /**
     * Atributo do tipo Node que guarda o proximo no.
     */
    private Node <E> next;
    
    /**
     * Atributo do tipo E que guarda o elemento.
     */
    private E element;

    /**
     * Construtor da classe Node que inicia os valores dos atributos.
     * @param next
     * @param element 
     */
    public Node(Node<E> next, E element) {
        this.next = next;
        this.element = element;
    }

    /**
     * Metodo que devolve o proximo no.
     * @return proximo no
     */
    public Node<E> getNext() {
        return next;
    }

    /**
     * Metodo que altera o valor do proximo no.
     * @param next 
     */
    public void setNext(Node<E> next) {
        this.next = next;
    }

    /**
     * Metodo que devolve o elemento.
     * @return elemento
     */
    public E getElement() {
        return element;
    }

    /**
     * Metodo que altera o valor do no.
     * @param element 
     */
    public void setElement(E element) {
        this.element = element;
    }
}
