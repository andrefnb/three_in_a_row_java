/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.model.tads;

import java.io.Serializable;
import pt.ips.pa.model.exceptions.TADOutofBoundsException;
import pt.ips.pa.model.interfaces.List;
import pt.ips.pa.model.patterns.IteratorDynamic;

/**
 * Classe TADListLinked que ira ser o adaptee da classe Ranking
 *
 * @author Luis Mestre e Andre Bastos
 * @param <E>
 */
public class TADListLinked<E> implements List<E> , Serializable{

    /**
     * Atributos do tipo DNode que irao ser as sentinelas da lista
     */
    private DNode<E> header, trailer;

    /**
     * Atributo do tipo int que guarda o numero de elementos na lista
     */
    private int size;

    /**
     * Construtor da classe TADListLinked que inicia os atributos, criando novos
     * DNodes para as sentinelas com as suas respetivas ligacoes.
     */
    public TADListLinked() {
        size = 0;
        header = new DNode<>(null, null, null);
        trailer = new DNode<>(null, header, null);
        header.setNext(trailer);
    }

    /**
     * Metodo que devolve true de estiver vazio ou false caso contrario.
     *
     * @return true se estiver vazio ou false se nao estiver vazio
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Metodo que devolve o numero de elementos.
     *
     * @return o numero de elementos
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Metodo que adiciona um elemento para um rank especifico. Recebe como
     * parametros o rank do tipo int e o elem do tipo generico E.
     *
     * @param rank
     * @param elem
     */
    @Override
    public void add(int rank, E elem) {
        DNode<E> position = nodeAtRank(rank);
        DNode<E> previous = position.getPrevious();
        DNode<E> newNode = new DNode<>(elem, previous, position);
        previous.setNext(newNode);
        position.setPrevious(newNode);
        size++;
    }

    /**
     * Metodo que remove um elemento da lista na posicao que recebe como
     * parametro.
     *
     * @param rank
     * @return o elemento que foi removido 
     * @throws TADOutofBoundsException
     */
    @Override
    public E remove(int rank) throws TADOutofBoundsException {
        DNode<E> positionNode = nodeAtRank(rank);
        DNode<E> previousNode = positionNode.getPrevious();
        DNode<E> nextNode = positionNode.getNext();
        previousNode.setNext(nextNode);
        nextNode.setPrevious(previousNode);
        size--;
        return positionNode.getElement();
    }

    /**
     * Metodo que retorna o elemento que se encontra na posicao rank. Recebe
     * como parametro o rank do tipo int.
     *
     * @param rank
     * @return o elemento que esta na posicao rank
     * @throws TADOutofBoundsException 
     */
    @Override
    public E get(int rank) throws TADOutofBoundsException {
        return nodeAtRank(rank).getElement();
    }

    /**
     * Metodo que altera o valor do elemento na respetiva posicao rank. Recebe
     * como parametros o rank do tipo int e o elem do tipo generico E.
     *
     * @param rank
     * @param elem
     * @return o elemento que foi alterado
     * @throws TADOutofBoundsException excecao lancada quando nao for possivel
     * adicionar o elemento
     */
    @Override
    public E set(int rank, E elem) throws TADOutofBoundsException {
        DNode<E> position = nodeAtRank(rank);
        E elemReplaced = position.getElement();
        position.setElement(elem);
        return elemReplaced;
    }

    /**
     * Metodo que retorna o no que se encontra na posicao rank
     *
     * @param rank
     * @return o nó que esta na posicao rank
     */
    private DNode<E> nodeAtRank(int rank) {
        DNode<E> position = header.getNext();
        for (int i = 0; i < rank; i++) {
            position = position.getNext();
        }
        return position;
    }
    
    /**
     * Metodo que retorna um Iterator que fara a leitura dos elementos da classe
     * 
     * @return um IteratorDynamic para fazer a leitura dos elementos
     */
    @Override
    public IteratorDynamic getIterator(){
        return new IteratorDynamic(header.getNext());
    }
}
