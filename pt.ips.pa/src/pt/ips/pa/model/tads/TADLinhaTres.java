package pt.ips.pa.model.tads;

import java.io.Serializable;
import pt.ips.pa.model.exceptions.*;
import pt.ips.pa.model.interfaces.LinhaTres;
import pt.ips.pa.model.patterns.IteratorDynamic;

/**
 * Classe TADLinhaTres e uma TAD semelhante a uma TADDeque, que representa uma 
 * linha de jogo.
 * @author Luis Mestre e Andre Bastos
 */
public class TADLinhaTres<E> implements LinhaTres<E> , Serializable{
    

    /**
     * size - Atributo que guarda o número de elementos
     * capacidade - Atributo que guarda o número maximo de elementos
     */
    private int size, capacidade;
    
    /**
     * Atributos do tipo DNode que guardam os elementos à
     * esquerda e à direita respetivamente.
     */
    private DNode<E> header, trailer;

    /**
     * Construtor da classe TADLinhaTres que recebe como parametro a capacidade
     * maxima de elementos na classe e inicializa os restante atributos.
     * @param capacidade 
     */
    public TADLinhaTres(int capacidade) {
        this.capacidade = capacidade;
        size = 0;
        header = new DNode<>(null, null, null);
        trailer = new DNode<>(null, header, null);
        header.setNext(trailer);
    }

    /**
     * Metodo que adiciona um elemento à esquerda.
     * @param element
     * @throws TADFullException
     */
    @Override
    public void addFirst(E element) throws TADFullException {
        if (!isFull()) {
            DNode<E>next = header.getNext();
            DNode<E>newElem = new DNode<>(element,header,next);
            header.setNext(newElem);
            next.setPrevious(newElem);
        }
        size++;
    }

    /**
     * Metodo que adiciona um elemento à direita
     * @param element
     * @throws TADFullException
     */
    @Override
    public void addLast(E element) throws TADFullException {
        if (!isFull()) {
            DNode<E>previous = trailer.getPrevious();
            DNode<E>newElem = new DNode<>(element,previous,trailer);
            trailer.setPrevious(newElem);
            previous.setNext(newElem);
        }
        size++;
    }

    /**
     * Metodo que verifica se a linha esta cheia.
     * @return true caso consiga adicionar ou false caso nao consiga
     * @throws TADFullException
     */
    private boolean isFull() throws TADFullException {
        if (size == capacidade) {
            throw new TADFullException("A linha esta cheia.");
        }
        return false;
    }

    /**
     * Metodo que remove os primeiros 3 elementos à esquerda e retorna os
     * elementos removidos.
     * @return os elementos removidos
     * @throws TADEmptyException
     */
    @Override
    public E[] removeFirst() throws TADEmptyException {
        E[] elementsRemoved = getFirst();
        for (int i = 0; i < 3; i++) {
            DNode<E>next = header.getNext().getNext();
            header.setNext(next);
            next.setPrevious(header);
            size--;
        }
        return elementsRemoved;
    }
    
    /**
     * Metodo que remove os primeiros 3 elementos à direita e retorna os
     * elementos removidos.
     * @return os elementos removidos
     * @throws TADEmptyException 
     */
    @Override
    public E[] removeLast() throws TADEmptyException {
        E[] elementsRemoved = getLast();
        for (int i = 0; i < 3; i++) {
            DNode<E>previous = trailer.getPrevious().getPrevious();
            trailer.setPrevious(previous);
            previous.setNext(trailer);
            size--;
        }
        return elementsRemoved;
    }

    /**
     * Metodo que retorna os primeiros 3 elementos que estao à esquerda
     * @return os 3 primeiros elementos à esquerda
     * @throws TADEmptyException 
     */
    @Override
    public E[] getFirst() throws TADEmptyException {
        if (isEmpty()) {
            throw new TADEmptyException("A linha esta vazia.");
        } else if (size < 3) {
            throw new TADEmptyException("A linha tem menos de 3 elementos.");
        }
        E[] tresElementos = (E[]) new Object[3];
        DNode<E> aux = header.getNext();
        for (int i = 0; i < 3; i++) {
            tresElementos[i] = aux.getElement();
            aux = aux.getNext();
        }
        return tresElementos;
    }

    /**
     * Metodo que retorna os primeiros 3 elementos que estao à direita
     * @return os 3 primeiros elementos à direita
     * @throws TADEmptyException
     */
    @Override
    public E[] getLast() throws TADEmptyException {
        if (isEmpty()) {
            throw new TADEmptyException("A linha esta vazia.");
        } else if (size < 3) {
            throw new TADEmptyException("A linha tem menos de 3 elementos.");
        }
        E[] tresElementos = (E[]) new Object[3];
        DNode<E> aux = trailer.getPrevious();
        for (int i = 0; i < 3; i++) {
            tresElementos[i] = aux.getElement();
            aux = aux.getPrevious();
        }
        return tresElementos;
    }

    /**
     * Metodo que retorna um boolean.
     * true quando a TAD nao tem elementos e false quando tem pelo menos 1
     * @return true se nao tem elementos, false se tem elementos
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Metodo que retorna um inteiro que representa o numero de elementos na TAD
     * @return o numero de elementos
     */
    @Override
    public int size() {
        return size;
    }
    
    /**
     * Metodo que retorna um Iterator que fara a leitura dos elementos da classe
     * @return um Iterator para fazer a leitura dos elementos
     */
    @Override
    public IteratorDynamic<E> getIterator() {
        return new IteratorDynamic(header.getNext());
    }
}
