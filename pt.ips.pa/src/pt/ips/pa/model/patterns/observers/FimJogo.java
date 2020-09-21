package pt.ips.pa.model.patterns.observers;

import java.util.Observable;
import java.util.Observer;
import pt.ips.pa.model.jogo.Jogo;
import pt.ips.pa.model.jogo.Logger;

/**
 * Classe FimJogo que implementa da interface Observer (que ja provem do Java)
 * que ira observar o jogo, a decorrer e quando for notificado de que o jogo
 * acabou ele ira adicionar ao Logger as novas informacoes.
 * 
 * @author Luis Mestre e Andre Bastos
 */
public class FimJogo implements Observer {

    /**
     * Metodo update que provem da interface Observer. Este metodo ira ser
     * despoletado quando a classe for notificada de alguma alteracao no Jogo
     * que esta a observar. Atualiza o Logger com as novas informacoes,
     * nomeadamente o jogador que acabou o jogo, quantos pontos fez no final e
     * quanto tempo demorou a jogar.
     * @param o
     * @param arg 
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("Fim")) {
            Jogo jogo = (Jogo) o;
            String informacao = "O jogador " + jogo.getJogador().getUsername() + " acabou o jogo com " + jogo.getPontuacao() + " e demorou " + jogo.getJogador().getHistorico().getDuracaoDoJogo();
            Logger.getInstance().addLog(informacao);
        }
    }
}
