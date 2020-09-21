package pt.ips.pa.model.tads;

import java.io.Serializable;
import java.util.Date;
import pt.ips.pa.model.exceptions.TADEmptyException;
import pt.ips.pa.model.interfaces.Historico;
import pt.ips.pa.model.patterns.IteratorDynamic;

/**
 * Classe TADHistorico que guarda os elementos do Histerico e que apenas elimina
 * quando este estiver cheio.
 *
 * @author Luis Mestre e Andre Bastos
 * @param <E>
 */
public class TADHistorico<E> implements Historico<E>, Serializable {

    /**
     * Atributos do tipo int que guardam o numero de elementos no Historico e a
     * capacidade maxima do Historico, respetivamente.
     */
    private int size, capacidade;

    /**
     * Atributo do tipo DNode que representa um no duplo que serve como
     * sentinela do conjunto.
     */
    private DNode<ElementDate> header;

    /**
     * Construtor do TADHistorico que recebe a capacidade desejada do Historico.
     *
     * @param capacidade
     */
    public TADHistorico(int capacidade) {
        this.capacidade = capacidade;
        this.size = 0;
        header = new DNode<>(null, null, null);
    }

    /**
     * Metodo que devolve o tamanho do Historico.
     *
     * @return tamanho do historico
     */
    @Override
    public int size() {
        return size;
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
     * Metodo que adiciona um elemento ao Historico.
     *
     * @param element
     */
    @Override
    public void add(E element) {
        if (isEmpty()) {
            DNode<ElementDate> node = new DNode<>(new ElementDate(element), header, header);
            header.setNext(node);
            header.setPrevious(node);
        } else {
            if (size() == capacidade) {
                header.getPrevious().getPrevious().setNext(header);
                header.setPrevious(header.getPrevious().getPrevious());
                size--;
            }
            DNode<ElementDate> node = new DNode<>(new ElementDate(element), header, header.getNext());
            header.getNext().setPrevious(node);
            header.setNext(node);
        }
        size++;
    }

    /**
     * Metodo que devolve o elemento que se encontra no inicio do Historico.
     *
     * @return elemento no inicio do historico
     * @throws TADEmptyException
     */
    @Override
    public E peek() throws TADEmptyException {
        if (isEmpty()) {
            throw new TADEmptyException("O Histórico esta vazio.");
        }
        return header.getNext().getElement().element;
    }

    /**
     * Metodo que devolve os elementos do historico de acordo com o número de
     * dias enviado
     *
     * @param days
     * @return os elementos de n dias atras ate agora
     * @throws TADEmptyException
     */
    public TADStackLinked<E> getHistoricoDays(int days) throws TADEmptyException {
        if (isEmpty()) {
            throw new TADEmptyException("O Histórico esta vazio.");
        }
        Date data = new Date();
        data.setDate(data.getDate() - days);
        TADStackLinked<E> conjunto = new TADStackLinked<>();
        DNode<ElementDate> atual = header.getNext();
        while (atual.getElement().data.after(data)) {
            conjunto.push(atual.getElement().element);
            atual = atual.getNext();
        }
        return conjunto;
    }

    /**
     * Metodo que devolve os elementos do historico de acordo com a data em
     * especifico enviado
     *
     * @param date
     * @return os elementos que foram adicionados na data enviada
     * @throws TADEmptyException
     */
    private TADStackLinked<E> getHistoricoFromDate(Date date) throws TADEmptyException {
        if (isEmpty()) {
            throw new TADEmptyException("O Histórico esta vazio.");
        }
        DNode<ElementDate> atual = header.getNext();
        TADStackLinked<E> conjunto = new TADStackLinked<>();
        for (int i = 0; i < size(); i++) {
            if (atual.getElement().data.equals(date)) {
                conjunto.push(atual.getElement().element);
            }
        }
        return conjunto;
    }

    /**
     * Metodo que devolve os elementos do historico de acordo com o mês em
     * especifico enviado
     *
     * @param month
     * @return os elementos que foram adicionados no mês enviado
     */
    public TADStackLinked<E> getHistoricoFromMonth(int month) {
        if (isEmpty()) {
            throw new TADEmptyException("O Histórico esta vazio.");
        }
        DNode<ElementDate> atual = header.getNext();
        TADStackLinked<E> conjunto = new TADStackLinked<>();
        for (int i = 0; i < size(); i++) {
            if (atual.getElement().data.getMonth() + 1 == month) {
                conjunto.push(atual.getElement().element);
            }
        }
        return conjunto;
    }

    /**
     * Metodo que devolve os elementos do histórico de acordo com uma data em
     * especifico (dia, mes e ano).
     *
     * @param dia
     * @param mes
     * @param ano
     * @return os elementos adicionados nessa data
     * @throws TADEmptyException
     */
    public TADStackLinked<E> getHistoricoFromDate(int dia, int mes, int ano) throws TADEmptyException {
        return getHistoricoFromDate(new Date(ano, mes - 1, dia));
    }

    /**
     * Metodo que retorna um Iterator que fara a leitura dos elementos da classe
     *
     * @return um IteratorDynamic para fazer a leitura dos elementos.
     */
    @Override
    public IteratorDynamic<E> getIterator() {
        return new IteratorDynamic(header.getNext());
    }

    /**
     * Classe privada que representa o elemento do DNode. Nesta classe irá ser
     * contido o elemento da classe TADHistorico e a data de quando foi criado
     * uma instancia do tipo ElementDate.
     *
     * @author Andre Bastos e Luis Mestre
     */
    private class ElementDate implements Serializable {

        /**
         * Atributo do tipo E que representa o elemento do TADHistorico.
         */
        private E element;

        /**
         * Atributo do tipo Date que representa a data de quando foi criado uma
         * instancia do ElementDate.
         */
        private Date data;

        /**
         * Construtor da classe privada ElementDate, que recebe como parametro o
         * elemento do TADHistorico e guarda-o no atributo e atualiza o valor do
         * atributo data para a data atual.
         *
         * @param element
         */
        public ElementDate(E element) {
            this.element = element;
            this.data = new Date();
        }

        /**
         * Metodo que ira devolver a data no formato dd/mm/aaaa.
         *
         * @param data
         * @return String da data no formato dd/mm/aaaa
         */
        private String data(Date data) {
            return String.format("%02d/%02d/%d", data.getDate(), (data.getMonth() + 1), (data.getYear() + 1900));
        }

        @Override
        public String toString() {
            return "" + element + "," + data(data);
        }
    }
}
