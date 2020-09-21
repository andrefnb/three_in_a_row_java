package pt.ips.pa.model.jogo;

import java.io.Serializable;
import pt.ips.pa.model.exceptions.*;
import pt.ips.pa.model.patterns.IteratorDynamic;
import pt.ips.pa.model.tads.TADLinhaTres;

/**
 * Classe LinhaJogo ira ser as linhas que irao ser criadas no tabuleiro para
 * adicionar os elementos.
 *
 * @author Luis Mestre e Andre Bastos
 * @param <E>
 */
public class LinhaJogo<E> implements Serializable {

    /**
     * Atributo adaptee do tipo TADLinhaTres
     */
    private TADLinhaTres<E> linha;

    /**
     * Atributo do tipo int que guarda o numero de elementos adicionados
     * respetivamente a direita e a esquerda da linha.
     */
    private int nrElemDireita, nrElemEsquerda;

    /**
     * Atributo do tipo int que guarda a capacidade maxima da linha.
     */
    private int capacidade;

    /**
     * Construtor da classe que recebe como parametro um inteiro que representa
     * a capacidade maxima da linha. Inicia o adaptee e o atributo capacidade
     * com o parametro, enquanto que os outros dois atributos sao iniciados com
     * o valor 0.
     *
     * @param capacidade
     */
    public LinhaJogo(int capacidade) {
        this.linha = new TADLinhaTres<>(capacidade);
        this.capacidade = capacidade;
        this.nrElemDireita = this.nrElemEsquerda = 0;
    }

    /**
     * Metodo que adiciona os elementos a linha e devolve um inteiro que
     * representa o numero de pecas destruidas
     *
     * @param elementEsquerda
     * @param elementDireita
     * @return int do numero de pecas destruidas
     */
    public int add(E elementEsquerda, E elementDireita) {
        int nrPecasRemovidas = 0;
        addFirst(elementEsquerda);
        if (size() >= 3 && removeFirst()) {
            nrPecasRemovidas += 3;

        }
        addLast(elementDireita);
        if (size() >= 3 && removeLast()) {
            nrPecasRemovidas += 3;
        }
        return nrPecasRemovidas;
    }

    /**
     * Metodo que limpa a linha, ficando sem pecas e devolve o numero de pecas
     * destruidas
     *
     * @return o numero de pecas destruidas
     */
    public int clear() {
        int nrPecasRemovidas = linha.size();
        this.linha = new TADLinhaTres<>(capacidade);
        this.nrElemDireita = this.nrElemEsquerda = 0;
        return nrPecasRemovidas;
    }

    /**
     * Metodo que adiciona a esquerda da linha um elemento, e atualiza o valor
     * do atributo nrElemEsquerda. Lanca uma excecao do tipo TADFullException
     * caso a linha fique cheia.
     *
     * @param element
     * @throws TADFullException
     */
    private void addFirst(E element) throws TADFullException {
        if (nrElemEsquerda < getCapacidade() / 2) {
            linha.addFirst(element);
            nrElemEsquerda++;
        }
        if (nrElemEsquerda == getCapacidade() / 2 && !elementsEqual(linha.getFirst())) {
            throw new TADFullException("Perdes-te.");
        }
    }

    /**
     * Metodo que adiciona a direita da linha um elemento, e atualiza o valor do
     * atributo nrElemDireita. Lanca uma excecao do tipo TADFullException caso a
     * linha fique cheia.
     *
     * @param element
     * @throws TADFullException
     */
    private void addLast(E element) throws TADFullException {
        if (nrElemDireita < getCapacidade() / 2) {
            linha.addLast(element);
            nrElemDireita++;
        }
        if (nrElemDireita == getCapacidade() / 2 && !elementsEqual(linha.getLast())) {
            throw new TADFullException("Perdes-te.");
        }
    }

    /**
     * Metodo que remove os primeiros 3 elementos a esquerda da linha caso estes
     * sejam iguais. Retorna true caso tenha eliminado, retorna false caso
     * contrario.
     *
     * @return true caso tenha eliminado, retorna false caso contrario
     * @throws TADEmptyException
     */
    private boolean removeFirst() throws TADEmptyException {
        boolean removed = false;
        try {
            if (elementsEqual(linha.getFirst())) {
                removed = true;
                linha.removeFirst();
                nrElemEsquerda -= 3;
                if (nrElemEsquerda < 0) {
                    nrElemDireita += nrElemEsquerda;
                    nrElemEsquerda = 0;
                }
            }
        } catch (TADEmptyException e) {
            removed = false;
        }
        return removed;
    }

    /**
     * Metodo que remove os primeiros 3 elementos a direita da linha caso estes
     * sejam iguais. Retorna true caso tenha eliminado, retorna false caso
     * contrario.
     *
     * @return true caso tenha eliminado, retorna false caso contrario
     * @throws TADEmptyException
     */
    private boolean removeLast() throws TADEmptyException {
        boolean removed = false;
        try {
            if (elementsEqual(linha.getLast())) {
                removed = true;
                linha.removeLast();
                nrElemDireita -= 3;
                if (nrElemDireita < 0) {
                    nrElemEsquerda += nrElemDireita;
                    nrElemDireita = 0;
                }
            }
        } catch (TADEmptyException e) {
            removed = false;
        }
        return removed;
    }

    /**
     * Metodo que retorna o numero de elementos na linha.
     *
     * @return o numero de elementos na linha
     */
    public int size() {
        return linha.size();
    }

    /**
     * Metodo que imprime a linha.
     */
    public void mostrarLinha() {
        IteratorDynamic it = linha.getIterator();
        System.out.print(preencherEspacos((getCapacidade() / 2) - getNrElemEsquerda()));
        while (it.hasNext()) {
            System.out.print(" " + it.next().toString());
        }
        System.out.print(preencherEspacos((getCapacidade() / 2) - getNrElemDireita()));
    }

    /**
     * Metodo que preenche os espacos em vazio quando se vai imprimir a linha.
     *
     * @param nrEspaco
     * @return os espacos necessarios a adicionar na impressao da linha
     */
    public String preencherEspacos(int nrEspaco) {
        String espacos = "";
        for (int i = 0; i < nrEspaco; i++) {
            espacos += " -";
        }
        return espacos;
    }

    /**
     * Metodo que verifica se os elementos que recebe como parametro sao todos
     * iguais. Retorna true caso o sejam e false caso nao o sejam.
     *
     * @param elements
     * @return true caso sejam iguais e false caso nao o sejam.
     */
    private boolean elementsEqual(E[] elements) {
        return elements[0].toString().equals(elements[1].toString()) && elements[1].toString().equals(elements[2].toString());
    }

    /**
     * Metodo que retorna a capacidade maxima da linha.
     *
     * @return a capacidade maxima da linha
     */
    public int getCapacidade() {
        return capacidade;
    }

    /**
     * Metodo que retorna o numero de elementos adicionados a direita da linha.
     *
     * @return o numero de elementos adicionados a direita
     */
    public int getNrElemDireita() {
        return nrElemDireita;
    }

    /**
     * Metodo que retorna o numero de elementos adicionados a esquerda da linha.
     *
     * @return o numero de elementos adicionados a esquerda
     */
    public int getNrElemEsquerda() {
        return nrElemEsquerda;
    }

    /**
     * Metodo que altera o numero de elementos adicionados a direita da linha.
     *
     * @param nrElemDireita
     */
    public void setNrElemDireita(int nrElemDireita) {
        this.nrElemDireita = nrElemDireita;
    }

    /**
     * Metodo que altera o numero de elementos adicionados a esquerda da linha.
     *
     * @param nrElemEsquerda
     */
    public void setNrElemEsquerda(int nrElemEsquerda) {
        this.nrElemEsquerda = nrElemEsquerda;
    }

    /**
     * Metodo que retorna uma deepCopy da linha
     *
     * @return uma deepCopy da linha
     */
    public LinhaJogo linhaCopia() {
        LinhaJogo linhaNova = new LinhaJogo(getCapacidade());
        IteratorDynamic it = linha.getIterator();
        while (it.hasNext()) {
            linhaNova.addElem(((Peca) it.next()).copiaPeca());
        }
        linhaNova.setNrElemDireita(this.nrElemDireita);
        linhaNova.setNrElemEsquerda(this.nrElemEsquerda);
        return linhaNova;
    }

    /**
     * Metodo que adiciona um elemento a linha.
     *
     * @param element
     * @throws TADFullException
     */
    private void addElem(E element) throws TADFullException {
        linha.addFirst(element);
    }

    public IteratorDynamic<E> getIterator() {
        return linha.getIterator();
    }
}
