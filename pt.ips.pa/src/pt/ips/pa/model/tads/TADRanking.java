package pt.ips.pa.model.tads;

import java.io.Serializable;
import java.util.Comparator;
import pt.ips.pa.model.exceptions.TADOutofBoundsException;
import pt.ips.pa.model.interfaces.Ranking;
import pt.ips.pa.model.patterns.IteratorDynamic;

/**
 * Classe TADRanking que e um adapter da classe TADListLinked. Esta classe ira
 * servir para guardar e ordenar elemento de uma ordem específica
 *
 * @author Luis Mestre e Andre Bastos
 * @param <E>
 */
public class TADRanking<E> implements Ranking<E>, Serializable {

    /**
     * Atributo do tipo TADListLinked que representa o adaptee da classe
     */
    private TADListLinked<E> lista;

    /**
     * Atributo do tipo Comparator que representa o comparador para a
     * pontuacao dos elementos
     */
    private Comparator<E> comparatorPontuacao;

    /**
     * Atributo do tipo Comparator que representa o comparador para o nome
     * dos elementos
     */
    private Comparator<E> comparatorNome;

    /**
     * Construtor da classe onde recebe como parametros dois comparadores e
     * guarda-os nos respetivos atributos.
     *
     * @param comparatorPontuacao
     * @param comparatorNome
     */
    public TADRanking(Comparator comparatorPontuacao, Comparator comparatorNome) {
        this.lista = new TADListLinked<>();
        this.comparatorPontuacao = comparatorPontuacao;
        this.comparatorNome = comparatorNome;
    }

    /**
     * Metodo que retorna um boolean. true quando a TAD nao tem elementos e
     * false quando tem pelo menos 1
     *
     * @return true se nao tem elementos, false se tem elementos
     */
    @Override
    public boolean isEmpty() {
        return lista.isEmpty();
    }

    /**
     * Metodo que retorna um inteiro que representa o numero de elementos na TAD
     *
     * @return o numero de elementos
     */
    @Override
    public int size() {
        return lista.size();
    }

    /**
     * Metodo que adiciona um elemento à TAD de forma a que fique ordenado
     *
     * @param element elemento que se adiciona
     * @throws TADOutofBoundsException excecao lancada no caso de nao ser
     * possível adicionar o elemento
     */
    @Override
    public void add(E element) throws TADOutofBoundsException {
        boolean jogador = false;
        for (int i = 0; i < size(); i++) {
            if (comparatorNome.compare(lista.get(i), element) == 0 && comparatorPontuacao.compare(lista.get(i), element) <= 0) {
                lista.remove(i);
            } else if (comparatorNome.compare(lista.get(i), element) == 0 && comparatorPontuacao.compare(lista.get(i), element) > 0) {
                jogador = true;
            }
        }
        if (jogador==false) {
            int newRank = size();
            for (int i = 0; i < size(); i++) {
                if (comparatorPontuacao.compare(lista.get(i), element) <= 0) {
                    newRank = i;
                    break;
                }
            }
            lista.add(newRank, element);
        }
    }

    /**
     * Metodo que retorna um elemento de acordo com o parâmetro rank recebido
     *
     * @param rank o rank do elemento
     * @return elemento
     * @throws TADOutofBoundsException excecao lancada no caso de o rank nao ser
     * valido
     */
    @Override
    public E get(int rank) throws TADOutofBoundsException {
        return lista.get(rankValid(rank, size() - 1));
    }

    /**
     * Metodo que recebe como parâmetros inteiros rank e o size que representam
     * uma posicao e a última posicao respetivamente. Verifica se o rank esta
     * valido, e caso nao esteja lanca uma excecao OutofBoundsException.
     *
     * @param rank uma posicao
     * @param limit a última posicao
     * @return rank
     * @throws TADOutofBoundsException excecao lancada no caso de o rank nao ser
     * valido
     */
    private int rankValid(int rank, int limit) throws TADOutofBoundsException {
        if (rank < 0 || rank > limit) {
            throw new TADOutofBoundsException(rank);
        }
        return rank;
    }

    /**
     * Metodo que apaga todos os elementos existentes na TAD
     */
    @Override
    public void reset() {
        this.lista = new TADListLinked<>();
    }

    /**
     * Metodo que retorna um Iterator que fara a leitura dos elementos da classe
     *
     * @return um IteratorDynamic para fazer a leitura dos elementos
     */
    @Override
    public IteratorDynamic getIterator() {
        return lista.getIterator();
    }
}
