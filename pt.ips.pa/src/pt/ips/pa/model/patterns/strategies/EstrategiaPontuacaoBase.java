/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.model.patterns.strategies;

import java.io.Serializable;
import pt.ips.pa.model.interfaces.EstrategiaPontuacao;

/**
 * Classe implementada da interface EstrategiaPontuacao, onde ira fazer o
 * calculo da pontuacao. Adiciona 20 pontos por cada peca destruida.
 * 
 * @author Luis Mestre e Andre Bastos
 */
public class EstrategiaPontuacaoBase implements EstrategiaPontuacao , Serializable{

    /**
     * Constante que guarda o valor de cada peca destruida
     */
    private final static int pontuacaoPorPeca = 20;

    /**
     * Metodo que calcula a pontuacao das pecas destruidas e de seguida retorna
     * essa pontuacao.
     * 
     * @param pecasDestruidas
     * @return valor das pecas destruidas
     */
    @Override
    public int calcularPontuacao(int pecasDestruidas) {
        return pecasDestruidas * pontuacaoPorPeca;
    }
}
