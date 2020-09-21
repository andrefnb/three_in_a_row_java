package pt.ips.pa.model.patterns.observers;

import java.util.Observable;
import java.util.Observer;
import pt.ips.pa.model.jogo.Jogo;
import pt.ips.pa.model.jogo.JogoRapido;
import pt.ips.pa.model.jogo.Logger;
import pt.ips.pa.model.patterns.strategies.EstrategiaPontuacaoBase;

/**
 * Classe IniciarJogo que implementa da interface Observer (que ja provem do
 * Java) que ira observar o jogo, a decorrer e quando for notificado de que o
 * jogo acabou ele ira adicionar ao Logger as novas informacoes.
 * 
 * @author Luis Mestre e Andre Bastos
 */
public class IniciarJogo implements Observer {

    /**
     * Metodo update que provem da interface Observer. Este metodo ira ser
     * despoletado quando a classe for notificada de alguma alteracao no Jogo
     * que esta a observar. Atualiza o Logger com as novas informacoes,
     * nomeadamente o jogador que comecou o jogo, e que tipo de jogo e que
     * variante do mesmo (a variante sendo a estrategia de pontuacao).
     * @param o
     * @param arg 
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("Inicio")) {
            Jogo jogo = (Jogo) o;
            String tipoDeJogo = (jogo instanceof JogoRapido) ? "Rapido" : "Normal";
            String tipoDePontuacao = (jogo.getEstrategia() instanceof EstrategiaPontuacaoBase) ? "Base" : "Corrida";
            String informacao = "O jogador " + jogo.getJogador().getUsername() + " iniciou um jogo do tipo " + tipoDeJogo + " na versão " + tipoDePontuacao;
            Logger.getInstance().addLog(informacao);
        }
    }
}
