/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.model.jogo;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import pt.ips.pa.model.patterns.IteratorDynamic;
import pt.ips.pa.model.patterns.strategies.ComparadorJogadorNome;
import pt.ips.pa.model.patterns.strategies.ComparadorJogadorPontuacao;
import pt.ips.pa.model.tads.TADRanking;

/**
 *
 * @author Luís Mestre
 */
public class RankingJogador implements Observer, Serializable {

    private TADRanking<Jogador> ranking;

    /**
     * Atributo do tipo ComparadorJogadorNome que ira fazer as comparacoes dos
     * jogadores nos rankings
     */
    private ComparadorJogadorNome comparadorNome;

    public RankingJogador(ComparadorJogadorPontuacao comparatorPontuacao, ComparadorJogadorNome comparatorNome) {
        this.ranking = new TADRanking(comparatorPontuacao, comparatorNome);
        this.comparadorNome = comparatorNome;
    }

    /**
     * Metodo que retorna um boolean. true quando a TAD nao tem elementos e
     * false quando tem pelo menos 1
     *
     * @return true se nao tem elementos, false se tem elementos
     */
    public boolean isEmpty() {
        return ranking.isEmpty();
    }

    /**
     * Metodo que retorna um inteiro que representa o numero de elementos na TAD
     *
     * @return o numero de elementos
     */
    public int size() {
        return ranking.size();
    }

    public void add(Jogador jogador) {
        ranking.add(jogadorCopy(jogador));
    }

    public Jogador get(int rank) {
        return ranking.get(rank);
    }

    /**
     * Metodo que faz uma copia do jogador que jogou
     *
     * @param jogador
     * @return uma copia do jogador
     */
    private Jogador jogadorCopy(Jogador jogador) {
        Jogador copia = new Jogador(jogador.getUsername());
        copia.setPontuacao(jogador.getPontuacao());
        copia.setPontuacaoMaxima(jogador.getPontuacaoMaxima());
        copia.setNumeroJogos(jogador.getNumeroJogos());
        copia.setTempoTotal(jogador.getTempoTotal());
        return copia;
    }

    public IteratorDynamic getIterator() {
        return ranking.getIterator();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!isEmpty()) {
            Jogo jogo = ((GestorJogo) o).getJogo();
            Jogador jogador = jogo.getJogador();
            System.out.println(jogador.getPontuacaoMaxima());
            for (int i = 0; i < size(); i++) {
                if (comparadorNome.compare(jogador, get(i)) == 0) {
                    get(i).setPontuacaoMaxima(jogador.getPontuacaoMaxima());
                    get(i).setNumeroJogos(jogador.getNumeroJogos());
                    break;
                }
            }
        }
    }
}
