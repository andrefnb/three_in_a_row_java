package pt.ips.pa.model.tads;

import java.io.Serializable;
import pt.ips.pa.model.exceptions.TADEmptyException;
import pt.ips.pa.model.interfaces.Stack;
import pt.ips.pa.model.tads.Node;

/**
 * Classe StackLinked que ira conter elementos dentro de nós. Esta classe ira
 * comportar-se como uma pilha onde os elementos irao sendo empilhados.
 * 
 * @author Luís Mestre e Andre Bastos
 * @param <E>
 */
public class TADStackLinked<E> implements Stack<E> , Serializable{

    /**
     * Atributo do tipo in que guarda o numero de elementos no conjunto.
     */
    private int size;
    
    /**
     * Atributo do tipo Node que guarda o elemento que fica no topo do conjunto.
     */
    private Node<E> top;

    /**
     * Construtor da classe StackLinked. Inicializa os atributos, o size a 0 e o
     * top a null.
     */
    public TADStackLinked() {
        this.size = 0;
        this.top = null;
    }

    /**
     * Metodo que retorna o número de elementos no conjunto.
     * 
     * @return o número de elementos do conjunto 
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Metodo que retorna um boolean. true caso nao tenha elementos no conjunto
     * false caso tenha elementos no conjunto.
     * 
     * @return true nao tem elementos, false tem elementos
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Metodo que retorna o elemento que esta no topo. E lancado uma excecao
     * caso o conjunto esteja vazio.
     * 
     * @return o elemento que esta no topo
     * @throws TADEmptyException 
     */
    @Override
    public E peek() throws TADEmptyException {
        if (isEmpty()) {
            throw new TADEmptyException("Está vazio");
        }
        return top.getElement();
    }

    /**
     * Metodo que adiciona um novo elemento ao conjunto, ficando o mesmo no
     * topo.
     * 
     * @param element 
     */
    @Override
    public void push(E element) {
        Node<E> newNode = new Node<>(top, element);
        top = newNode;
        size++;
    }

    /**
     * Metodo que retorna o elemento do topo, retirando o mesmo do conjunto.
     * E lancado uma excecao caso o conjunto esteja vazio.
     * 
     * @return o elemento que esta no topo
     * @throws TADEmptyException 
     */
    @Override
    public E pop() throws TADEmptyException {
        E elemRemoved = peek();
        this.top = this.top.getNext();
        this.size--;
        return elemRemoved;
    }
}