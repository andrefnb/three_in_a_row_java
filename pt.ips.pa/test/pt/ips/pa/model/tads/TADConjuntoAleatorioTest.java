/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.model.tads;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author André Bastos & Luís Mestre
 */
public class TADConjuntoAleatorioTest {
    
    public TADConjuntoAleatorioTest() {
    }

    /**
     * Test of size method, of class ConjuntoAleatorio.
     */
    @Test
    public void testSize() {
        Integer [] array = {1,2};
        TADConjuntoAleatorio<Integer> conj = new TADConjuntoAleatorio<>(array);
        assertEquals(conj.size(),2);
    }

    /**
     * Test of peek method, of class ConjuntoAleatorio.
     */
    @Test
    public void testPeek() {
        Integer [] array = {1,2};
        TADConjuntoAleatorio<Integer> conj = new TADConjuntoAleatorio<>(array);
        boolean verificaçao = false;
        int valor = (int)conj.peek();
        if (valor==1||valor==2) {
            verificaçao = true;
        }
        assertEquals(verificaçao,true);
        String [] array2 = {"1","2"};
        TADConjuntoAleatorio<String> conj2 = new TADConjuntoAleatorio<>(array2);
        boolean verificaçao2 = false;
        String valor2 = (String) conj2.peek();
        if (valor2.equals("1")||valor2.equals("2")) {
            verificaçao = true;
        }
        assertEquals(verificaçao,true);
    }
}
