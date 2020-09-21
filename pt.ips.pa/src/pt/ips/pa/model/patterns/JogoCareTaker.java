package pt.ips.pa.model.patterns;

import java.io.Serializable;
import pt.ips.pa.model.exceptions.TADEmptyException;
import pt.ips.pa.model.jogo.Jogo;
import pt.ips.pa.model.jogo.JogoRapido;
import pt.ips.pa.model.tads.TADStackLinked;

/**
 * Classe JogoCareTaker que ira gerir o armazenamento dos JogoMemento que
 * são gerados pelo ConsolaJogo.
 * 
 * @author Luis Mestre e Andre Bastos
 */
public class JogoCareTaker implements Serializable{

    /**
     * Atributo do tipo StackLinked  que ira guardar os
     * momentos (JogoMemento) do Jogo.
     */
    TADStackLinked<JogoMemento> estados;

    /**
     * Construtor da classe onde ira iniciar o atributo.
     */
    public JogoCareTaker() {
        this.estados = new TADStackLinked();
    }

    /**
     * Metodo que ira guardar no atributo da classe o estado do jogo
     * naquele momento que recebe o parametro.
     * 
     * @param jogo
     */
    public void saveState(Jogo jogo) {
        estados.push(jogo.save());

    }

    /**
     * Metodo que ira restaurar o estado mais recente guardado no atributo da
     * classe.
     * 
     * @param jogo
     */
    public void restoreState(Jogo jogo) {
        try {
            jogo.restore(estados.pop());
            jogo.notifyObservers("Undo");
            if (jogo instanceof JogoRapido) {
                ((JogoRapido)jogo).undo();
            }
        } catch (TADEmptyException e) {
            System.out.println("Não da para voltar mais atras.");
        }
    }
}
