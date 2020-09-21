/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.model.jogo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Luís Mestre
 */
public class ListaJogadores extends Observable implements Serializable {

    /**
     * Atributo do tipo ArrayList que ira guardar as contas dos jogadores
     * (apagar antes da entrega)
     */
    private final ArrayList<Jogador> listaJogadores;

    /**
     *
     */
    public ListaJogadores() {
        listaJogadores = new ArrayList<>();
    }

    /**
     * Metodo que nao ira ser usado neste milestone
     *
     * @param nome
     * @return
     */
    public void criarJogador(String nome) {
        if (!hasJogador(nome)) {
            listaJogadores.add(new Jogador(nome));
            setChanged();
            notifyObservers();
        }
    }

    /**
     * metodo que nao ira ser usado neste milestone
     *
     * @param nome
     * @return
     */
    public boolean hasJogador(String nome) {
        if (!listaJogadores.isEmpty()) {
            for (Jogador jog : listaJogadores) {
                if (jog.getUsername().equals(nome)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @return
     */
    public ArrayList<Jogador> getListaJogadores() {
        return listaJogadores;
    }

    public boolean isEmpty() {
        return listaJogadores.isEmpty();
    }
}
