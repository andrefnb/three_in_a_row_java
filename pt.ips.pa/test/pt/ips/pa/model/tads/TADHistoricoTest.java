/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.model.tads;

import org.junit.Test;
import static org.junit.Assert.*;
import pt.ips.pa.model.exceptions.TADEmptyException;

/**
 *
 * @author André Bastos & Luís Mestre
 */
public class TADHistoricoTest {
    
    public TADHistoricoTest() {
    }

    /**
     * Test of size method, of class TADHistorico.
     */
    @Test
    public void testSize() {
        TADHistorico h = new TADHistorico(5);
        h.add("sdfghj");
        h.add("sdfghj");
        h.add("sdfghj");
        h.add("sdfghj");
        assertEquals(h.size(),4);
    }

    /**
     * Test of isEmpty method, of class TADHistorico.
     */
    @Test
    public void testIsEmpty() {
        TADHistorico h = new TADHistorico(5);
        assertEquals(h.isEmpty(),true);
        h.add("sdfghj");
        h.add("sdfghj");
        h.add("sdfghj");
        h.add("sdfghj");
        assertEquals(h.isEmpty(),false);
    }

    /**
     * Test of add method, of class TADHistorico.
     */
    @Test
    public void testAdd() {
        TADHistorico h = new TADHistorico(1);
        h.add("sdfghj");
        assertEquals("sdfghj",h.peek());
        h.add("dfghj");
        assertEquals("dfghj",h.peek());
    }

    /**
     * Test of remove method, of class TADHistorico.
     */
//    @Test (expected=TADEmptyException.class)
//    public void testRemove() {
//        TADHistorico h = new TADHistorico(1);
//        h.add("sdfghj");
//        h.remove();
//        assertEquals(h.size(),0);
//        h.remove();
//    }

    /**
     * Test of peek method, of class TADHistorico.
     */
    @Test (expected=TADEmptyException.class)
    public void testPeek() {
        TADHistorico h = new TADHistorico(1);
        h.peek();
        h.add("edfghjk");
        assertEquals("edfghjk",h.peek());
    }
    
}
