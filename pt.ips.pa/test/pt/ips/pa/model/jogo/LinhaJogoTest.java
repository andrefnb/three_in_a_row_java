/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.model.jogo;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luís Mestre
 */
public class LinhaJogoTest {
    
    public LinhaJogoTest() {
    }

    /**
     * Test of add method, of class LinhaJogo.
     */
    @Test
    public void testAdd() {
        LinhaJogo<String> linha = new LinhaJogo<>(10);
        linha.add("Luis", "Andre");
        assertEquals(2,linha.size());
        linha.add("Luis", "Andre");
        assertEquals(4,linha.size());
        linha.add("Pedro", "Andre");
        assertEquals(3,linha.size());
    }

    /**
     * Test of clear method, of class LinhaJogo.
     */
    @Test
    public void testClear() {
        LinhaJogo<String> linha = new LinhaJogo<>(10);
        linha.add("Luis", "Andre");
        linha.add("Luis", "Andre");
        assertEquals(4,linha.size());
        linha.clear();
        assertEquals(0,linha.size());
    }

    /**
     * Test of size method, of class LinhaJogo.
     */
    @Test
    public void testSize() {
        LinhaJogo<String> linha = new LinhaJogo<>(10);
        linha.add("Luis", "Andre");
        assertEquals(2,linha.size());
        linha.add("Luis", "Andre");
        assertEquals(4,linha.size());
        linha.add("Pedro", "Andre");
        assertEquals(3,linha.size());
    }

    /**
     * Test of mostrarLinha method, of class LinhaJogo.
     */
    @Test
    public void testMostrarLinha() {
    }

    /**
     * Test of preencherEspacos method, of class LinhaJogo.
     */
    @Test
    public void testPreencherEspacos() {
    }

    /**
     * Test of getCapacidade method, of class LinhaJogo.
     */
    @Test
    public void testGetCapacidade() {
        LinhaJogo<String> linha = new LinhaJogo<>(10);
        assertEquals(10,linha.getCapacidade());
    }

    /**
     * Test of getNrElemDireita method, of class LinhaJogo.
     */
    @Test
    public void testGetNrElemDireita() {
        LinhaJogo<String> linha = new LinhaJogo<>(10);
        linha.add("Luis", "Andre");
        assertEquals(1,linha.getNrElemDireita());
    }

    /**
     * Test of getNrElemEsquerda method, of class LinhaJogo.
     */
    @Test
    public void testGetNrElemEsquerda() {
        LinhaJogo<String> linha = new LinhaJogo<>(10);
        linha.add("Luis", "Andre");
        assertEquals(1,linha.getNrElemEsquerda());
    }

    /**
     * Test of setNrElemDireita method, of class LinhaJogo.
     */
    @Test
    public void testSetNrElemDireita() {
        LinhaJogo<String> linha = new LinhaJogo<>(10);
        assertEquals(0,linha.getNrElemDireita());
        linha.setNrElemDireita(2);
        assertEquals(2,linha.getNrElemDireita());
    }

    /**
     * Test of setNrElemEsquerda method, of class LinhaJogo.
     */
    @Test
    public void testSetNrElemEsquerda() {
        LinhaJogo<String> linha = new LinhaJogo<>(10);
        assertEquals(0,linha.getNrElemEsquerda());
        linha.setNrElemEsquerda(2);
        assertEquals(2,linha.getNrElemEsquerda());
    }

    /**
     * Test of linhaCopia method, of class LinhaJogo.
     */
    @Test
    public void testLinhaCopia() {
        LinhaJogo<Peca> linha = new LinhaJogo<>(10);
        linha.add(new Peca("a"), new Peca("b"));
        LinhaJogo<Peca> linha2 = new LinhaJogo<>(10);
        linha2 = linha.linhaCopia();
        assertEquals(linha.size(),linha2.size());
    }
}
