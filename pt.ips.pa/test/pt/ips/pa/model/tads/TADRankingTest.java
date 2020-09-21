/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.model.tads;

import org.junit.Test;
import static org.junit.Assert.*;
import pt.ips.pa.model.jogo.Jogador;
import pt.ips.pa.model.patterns.strategies.ComparadorJogadorNome;
import pt.ips.pa.model.patterns.strategies.ComparadorJogadorPontuacao;

/**
 *
 * @author André Bastos & Luís Mestre
 */
public class TADRankingTest {
    
    public TADRankingTest() {
    }

    /**
     * Test of isEmpty method, of class TADRanking.
     */
    @Test
    public void testIsEmpty() {
        TADRanking <Jogador> ranking = new TADRanking<>(new ComparadorJogadorPontuacao(), new ComparadorJogadorNome());
        assertEquals(ranking.isEmpty(),true);
        ranking.add(new Jogador2("Luís",10));
        assertEquals(ranking.isEmpty(),false);
    }

    /**
     * Test of size method, of class TADRanking.
     */
    @Test
    public void testSize() {
        TADRanking <Jogador> ranking = new TADRanking<>(new ComparadorJogadorPontuacao(), new ComparadorJogadorNome());
        assertEquals(ranking.size(),0);
        ranking.add(new Jogador2("Luís",10));
        assertEquals(ranking.size(),1);
        ranking.add(new Jogador2("Andre",11));
        ranking.add(new Jogador2("Pedro",12));
        ranking.add(new Jogador2("Miguel",13));
        assertEquals(ranking.size(),4);
    }

    /**
     * Test of add method, of class TADRanking.
     */
    @Test
    public void testAdd() {
        TADRanking <Jogador> ranking = new TADRanking<>(new ComparadorJogadorPontuacao(), new ComparadorJogadorNome());
        ranking.add(new Jogador2("Luís",10));
        assertEquals(ranking.size(),1);
        ranking.add(new Jogador2("Luís",11));
        assertEquals(ranking.size(),1);
        ranking.add(new Jogador2("Andre",15));
        ranking.add(new Jogador2("Pedro",9));
        assertEquals(ranking.size(),3);
        assertEquals(ranking.get(0).getUsername(),"Andre");
        ranking.add(new Jogador2("Pedro",16));
        assertEquals(ranking.size(),3);
        assertEquals(ranking.get(0).getUsername(),"Pedro");
    }

    /**
     * Test of get method, of class TADRanking.
     */
    @Test
    public void testGet() {
        TADRanking <Jogador> ranking = new TADRanking<>(new ComparadorJogadorPontuacao(), new ComparadorJogadorNome());
        ranking.add(new Jogador2("Luís",10));
        assertEquals(ranking.size(),1);
        assertEquals(ranking.get(0).getUsername(),"Luís");
        ranking.add(new Jogador2("Andre",11));
        assertEquals(ranking.get(0).getUsername(),"Andre");
        assertEquals(ranking.get(1).getUsername(),"Luís");
        ranking.add(new Jogador2("Miguel",40));
        ranking.add(new Jogador2("Tiago",40));
        assertEquals(ranking.get(0).getUsername(),"Tiago");
        assertEquals(ranking.get(1).getUsername(),"Miguel");
        assertEquals(ranking.get(2).getUsername(),"Andre");
        assertEquals(ranking.get(3).getUsername(),"Luís");
    }

    /**
     * Test of reset method, of class TADRanking.
     */
    @Test
    public void testReset() {
        TADRanking <Jogador> ranking = new TADRanking<>(new ComparadorJogadorPontuacao(), new ComparadorJogadorNome());
        ranking.add(new Jogador2("Luís",10));
        assertEquals(ranking.size(),1);
        ranking.reset();
        assertEquals(ranking.size(),0);
    }
    
    private class Jogador2 extends Jogador{
        private String nome;
        private int pontuacao;

        public Jogador2(String nome, int pontuacao) {
            super(nome);
            setPontuacao(pontuacao);
        }
    }
}
