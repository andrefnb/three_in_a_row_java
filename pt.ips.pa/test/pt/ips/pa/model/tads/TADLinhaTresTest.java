/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.model.tads;

import pt.ips.pa.model.exceptions.*;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author André Bastos & Luís Mestre
 */
public class TADLinhaTresTest {
    
    public TADLinhaTresTest() {
    }

    /**
     * Test of addFirst method, of class TADLinhaTres.
     */
    @Test (expected=TADFullException.class)
    public void testAddFirst() {
        TADLinhaTres<String> linha = new TADLinhaTres<>(5);
        linha.addFirst("teste1");
        linha.addFirst("teste2");
        linha.addFirst("teste3");
        String[] array = {"teste3", "teste2", "teste1"};
        Assert.assertArrayEquals(array,linha.getFirst());
        linha.addFirst("teste4");
        linha.addFirst("teste5");
        linha.addFirst("teste6erro");
    }

    /**
     * Test of addLast method, of class TADLinhaTres.
     */
    @Test (expected=TADFullException.class)
    public void testAddLast() {
        TADLinhaTres<String> linha = new TADLinhaTres<>(5);
        linha.addLast("teste1");
        linha.addLast("teste2");
        linha.addLast("teste3");
        String[] array = {"teste1", "teste2", "teste3"};
        Assert.assertArrayEquals(array,linha.getFirst());
        linha.addLast("teste4");
        linha.addLast("teste5");
        linha.addLast("teste6erro");
    }

    /**
     * Test of removeFirst method, of class TADLinhaTres.
     */
    @Test (expected=TADEmptyException.class)
    public void testRemoveFirst() {
       TADLinhaTres<String> linha = new TADLinhaTres<>(5);
        linha.addFirst("teste1");
        linha.addFirst("teste2");
        linha.addFirst("teste3");
        String[] array = {"teste3", "teste2", "teste1"};
        Assert.assertArrayEquals(array,linha.removeFirst());
        linha.removeFirst();
    }

    /**
     * Test of removeLast method, of class TADLinhaTres.
     */
    @Test (expected=TADEmptyException.class)
    public void testRemoveLast() {
        TADLinhaTres<String> linha = new TADLinhaTres<>(5);
        linha.addLast("teste1");
        linha.addLast("teste2");
        linha.addLast("teste3");
        String[] array = {"teste3", "teste2", "teste1"};
        Assert.assertArrayEquals(array,linha.removeLast());
        linha.removeLast();
    }

    /**
     * Test of getFirst method, of class TADLinhaTres.
     */
    @Test
    public void testGetFirst() {
        TADLinhaTres<String> linha = new TADLinhaTres<>(5);
        linha.addFirst("teste1");
        linha.addLast("teste2");
        linha.addLast("teste3");
        String[] array = {"teste1", "teste2", "teste3"};
        Assert.assertArrayEquals(array,linha.getFirst());
    }

    /**
     * Test of getLast method, of class TADLinhaTres.
     */
    @Test
    public void testGetLast() {
        TADLinhaTres<String> linha = new TADLinhaTres<>(5);
        linha.addLast("teste1");
        linha.addLast("teste2");
        linha.addLast("teste3");
        String[] array = {"teste1", "teste2", "teste3"};
        Assert.assertArrayEquals(array,linha.getFirst());
    }

    /**
     * Test of isEmpty method, of class TADLinhaTres.
     */
    @Test
    public void testIsEmpty() {
        TADLinhaTres<String> linha = new TADLinhaTres<>(5);
        linha.addLast("teste1");
        linha.addLast("teste1");
        linha.addLast("teste1");
        assertEquals(false,linha.isEmpty());
        linha.removeFirst();
        assertEquals(true,linha.isEmpty());
    }

    /**
     * Test of size method, of class TADLinhaTres.
     */
    @Test
    public void testSize() {
        TADLinhaTres<String> linha = new TADLinhaTres<>(5);
        linha.addLast("teste1");
        linha.addLast("teste2");
        linha.addLast("teste3");
        assertEquals(3,linha.size());
    }
}
