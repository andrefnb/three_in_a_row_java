/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.model.tads;

import java.io.Serializable;

/**
 * Classe DNode que ira ser um no duplo que ira conter um elemento o proximo no
 * e um anterior no para garantir uma boa pesquisa e eliminacao de elementos
 * caso o seja necessario.
 *
 * @author Luis Mestre e Andre Bastos
 * @param <E>
 */
public class DNode<E> implements Serializable{

    /**
     * Atributo do tipo generico E que guarda o elemento.
     */
    private E element;

    /**
     * Atributos do tipo DNode que guardam o proximo no e o anterior.
     * next - proximo no e previous - no anterior.
     */
    private DNode<E> next, previous;

    /**
     * Construtor da classe DNode que recebe o elemento que vai guardar, os nos
     * anterior e proximo, e ainda guarda a data em que este foi criado.
     *
     * @param element
     * @param previous
     * @param next
     */
    public DNode(E element, DNode<E> previous, DNode<E> next) {
        this.element = element;
        this.previous = previous;
        this.next = next;
    }

    /**
     * Metodo que devolve o proximo no.
     *
     * @return o DNode proximo
     */
    public DNode<E> getNext() {
        return next;
    }

    /**
     * Metodo que altera o proximo no.
     *
     * @param next
     */
    public void setNext(DNode<E> next) {
        this.next = next;
    }

    /**
     * Metodo que devolve o no anterior.
     *
     * @return o DNode anterior
     */
    public DNode<E> getPrevious() {
        return previous;
    }

    /**
     * Metodo que altera o no anterior.
     *
     * @param previous
     */
    public void setPrevious(DNode<E> previous) {
        this.previous = previous;
    }

    /**
     * Metodo que devolve o elemento deste no.
     *
     * @return o elemento contido no DNode
     */
    public E getElement() {
        return element;
    }

    /**
     * Metodo que altera o elemento deste no.
     *
     * @param element
     */
    public void setElement(E element) {
        this.element = element;
    }
}