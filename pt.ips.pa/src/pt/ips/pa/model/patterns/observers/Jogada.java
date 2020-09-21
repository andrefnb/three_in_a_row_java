/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.model.patterns.observers;

import java.util.Observable;
import java.util.Observer;
import pt.ips.pa.model.jogo.Jogo;
import pt.ips.pa.model.jogo.Logger;

/**
 * Classe Jogada que implementa da interface Observer (que ja provem do
 * Java) que ira observar o jogo, a decorrer e quando for notificado de que foi
 * feito uma jogada ele ira adicionar ao Logger as novas informacoes.
 * 
 * @author Luis Mestre e Andre Bastos
 */
public class Jogada implements Observer{

    /**
     * Metodo update que provem da interface Observer. Este metodo ira ser
     * despoletado quando a classe for notificada de alguma alteracao no Jogo
     * que esta a observar. Atualiza o Logger com as novas informacoes,
     * nomeadamente que jogada o jogador fez.
     * @param o
     * @param arg 
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("Jogada") || arg.equals("Undo")) {
            Jogo jogo = (Jogo) o;
            String nomeJogador = jogo.getJogador().getUsername();
            String informacao = (arg.equals("Undo"))?"O jogador "+nomeJogador+" fez undo da última jogada":
                    "O jogador "+nomeJogador+" jogou para a linha "+(jogo.getJogada()+1);
            Logger.getInstance().addLog(informacao);
        }
    }
    
}
