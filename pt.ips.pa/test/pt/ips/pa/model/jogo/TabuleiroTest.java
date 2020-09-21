/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.model.jogo;

import org.junit.Test;
import static org.junit.Assert.*;
import pt.ips.pa.model.tads.TADLinhaTres;

/**
 *
 * @author Asuspc
 */
public class TabuleiroTest {
    
    public TabuleiroTest() {
    }

    /**
     * Test of addElements method, of class Tabuleiro.
     */
    @Test
    public void testAddElements() {
        
        Tabuleiro tabuleiro = new Tabuleiro(6,8);
        tabuleiro.addElements(1, new Peca("A"), new Peca("A"));
        tabuleiro.addElements(1, new Peca("B"), new Peca("B"));
        tabuleiro.addElements(1, new Peca("A"), new Peca("A"));
        TADLinhaTres linha = new TADLinhaTres(8);
        linha.addFirst("A"); linha.addLast("A");
        linha.addFirst("B"); linha.addLast("B");
        linha.addFirst("A"); linha.addLast("A");
        assertEquals(6, tabuleiro.getLinhas()[1].size());
    }

    /**
     * Test of getLinhas method, of class Tabuleiro.
     */
    @Test
    public void testGetLinhas() {
        Tabuleiro tabuleiro = new Tabuleiro(6,8);
        tabuleiro.addElements(1, new Peca("A"), new Peca("A"));
        tabuleiro.addElements(1, new Peca("B"), new Peca("B"));
        tabuleiro.addElements(1, new Peca("A"), new Peca("A"));
        TADLinhaTres linha = new TADLinhaTres(8);
        linha.addFirst("A"); linha.addLast("A");
        linha.addFirst("B"); linha.addLast("B");
        linha.addFirst("A"); linha.addLast("A");
        assertEquals(6, tabuleiro.getLinhas()[1].size());
    }

    /**
     * Test of setLinhas method, of class Tabuleiro.
     */
    @Test
    public void testSetLinhas() {
        Tabuleiro tabuleiro = new Tabuleiro(6,8);
        tabuleiro.addElements(1, new Peca("A"), new Peca("A"));
        tabuleiro.addElements(1, new Peca("B"), new Peca("B"));
        tabuleiro.addElements(1, new Peca("A"), new Peca("A"));
        TADLinhaTres linha = new TADLinhaTres(8);
        linha.addFirst("A"); linha.addLast("A");
        linha.addFirst("B"); linha.addLast("B");
        linha.addFirst("A"); linha.addLast("A");
        assertEquals(6, tabuleiro.getLinhas()[1].size());
        LinhaJogo[] linhas = new LinhaJogo[6];
        linhas[0] = new LinhaJogo(8);
        linhas[1] = new LinhaJogo(8);
        tabuleiro.setLinhas(linhas);
        assertEquals(0, tabuleiro.getLinhas()[1].size());
    }
    
}
