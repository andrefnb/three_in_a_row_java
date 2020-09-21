package pt.ips.pa.model.jogo;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import pt.ips.pa.model.interfaces.EstrategiaPontuacao;
import pt.ips.pa.model.patterns.JogoCareTaker;
import pt.ips.pa.model.patterns.strategies.*;
import pt.ips.pa.model.tads.TADConjuntoAleatorio;

/**
 * Classe GestorJogo e a classe que ira gerir e guardar tudo o que acontece no
 * jogo desde o inicio ao fim do mesmo.
 *
 * @author Luis Mestre e Andre Bastos
 */
public final class GestorJogo extends Observable implements Observer, Serializable {

    /**
     *
     */
    private static GestorJogo instance = new GestorJogo();

    /**
     *
     */
    private ListaJogadores lista;

    /**
     * Atributo do tipo ComparadorJogadorNome que ira fazer as comparacoes dos
     * jogadores nos rankings
     */
    private ComparadorJogadorNome comparadorNome;

    /**
     * Atributo do tipo ComparadorJogadorPontuacao que ira fazer as comparacoes
     * dos jogadores nos rankings
     */
    private ComparadorJogadorPontuacao comparadorPontuacao;

    /**
     * Atributo do tipo TADRanking que ira guardar as pontuacoes dos jogadores
     * que jogaram um jogo do tipo Base Normal
     */
    private RankingJogador rankingBaseNormal;

    /**
     * Atributo do tipo TADRanking que ira guardar as pontuacoes dos jogadores
     * que jogaram um jogo do tipo Base Rapido
     */
    private RankingJogador rankingBaseRapido;

    /**
     * Atributo do tipo TADRanking que ira guardar as pontuacoes dos jogadores
     * que jogaram um jogo do tipo Corrida Normal
     */
    private RankingJogador rankingCorridaNormal;

    /**
     * Atributo do tipo TADRanking que ira guardar as pontuacoes dos jogadores
     * que jogaram um jogo do tipo Corrida Rapido
     */
    private RankingJogador rankingCorridaRapido;

    /**
     * Atributo do tipo Jogo. Ira ser o jogo com o qual o utilizador ira
     * interagir
     */
    private Jogo jogo;

    /**
     * Atributo do tipo JogoCareTaker que ira ser o
     */
    private JogoCareTaker careTaker;

    /**
     * O jogador que vai jogar.
     */
    private Jogador jogador;

    /**
     * Construtor da classe que inicia todos os atributos da mesma, e ainda
     * adiciona a propria classe como classe Observer do jogo que esta na
     * consola.
     */
    public GestorJogo() {
        lista = new ListaJogadores();
        careTaker = new JogoCareTaker();
        comparadorNome = new ComparadorJogadorNome();
        comparadorPontuacao = new ComparadorJogadorPontuacao();
        rankingBaseNormal = new RankingJogador(comparadorPontuacao, comparadorNome);
        rankingBaseRapido = new RankingJogador(comparadorPontuacao, comparadorNome);
        rankingCorridaNormal = new RankingJogador(comparadorPontuacao, comparadorNome);
        rankingCorridaRapido = new RankingJogador(comparadorPontuacao, comparadorNome);
        addObserver(rankingBaseNormal);
        addObserver(rankingBaseRapido);
        addObserver(rankingCorridaNormal);
        addObserver(rankingCorridaRapido);
    }

    /**
     * Metodo que adiciona um jogador ao ranking de acordo com o tipo de jogo.
     * Recebe como parametro o jogo.
     *
     * @param jogo
     */
    public void add(Jogo jogo) {
        if (jogo instanceof JogoRapido) {
            if (jogo.getEstrategia() instanceof EstrategiaPontuacaoBase) {
                rankingBaseRapido.add(jogo.getJogador());
            } else {
                rankingCorridaRapido.add(jogo.getJogador());
            }
        } else if (jogo.getEstrategia() instanceof EstrategiaPontuacaoBase) {
            rankingBaseNormal.add(jogo.getJogador());
        } else {
            rankingCorridaNormal.add(jogo.getJogador());
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Metodo que devolve o ranking de acordo com o tipo de jogo jogado.
     *
     * @param jogo
     * @return ranking do jogo
     */
    public RankingJogador getRanking(Jogo jogo) {
        if (jogo instanceof JogoRapido) {
            if (jogo.getEstrategia() instanceof EstrategiaPontuacaoBase) {
                return rankingBaseRapido;
            } else {
                return rankingCorridaRapido;
            }
        } else if (jogo.getEstrategia() instanceof EstrategiaPontuacaoBase) {
            return rankingBaseNormal;
        } else {
            return rankingCorridaNormal;
        }
    }

    /**
     * Metodo que atualiza o Logger com as informacoes do jogador, em termos de
     * posicao no ranking e pontuacao.
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("Fim")) {
            Jogo jogo = (Jogo) o;
            add(jogo);
            RankingJogador rank = getRanking(jogo);
            int posicao = getRanking(jogo).size();
            for (int i = 0; i < getRanking(jogo).size(); i++) {
                if (comparadorNome.compare((rank.get(i)), jogo.getJogador()) == 0) {
                    posicao = i + 1;
                    break;
                }
            }
            String informacao = String.format("O jogador %s ficou na posição %d com %d", jogo.getJogador().getUsername(), posicao, jogo.getPontuacao());
            Logger.getInstance().addLog(informacao);
        }
    }

    /**
     * Metodo que faz restaurar um momento anterior do jogo.
     */
    public void undo() {
        careTaker.restoreState(jogo);
    }

    /**
     * Metodo que inicia o jogo
     *
     * @param tabuleiro
     * @param estrategia
     * @param jogoNormal
     */
    public void iniciarJogo(Tabuleiro tabuleiro, EstrategiaPontuacao estrategia, boolean jogoNormal) {
        Integer[] numero = {1, 2, 3};
        TADConjuntoAleatorio<Integer> conjunto = new TADConjuntoAleatorio(numero);
        jogo = (jogoNormal == true) ? new Jogo(jogador, tabuleiro, conjunto, estrategia) : new JogoRapido(jogador, tabuleiro, conjunto, estrategia);
        jogo.addObserver(this);
    }

    public RankingJogador getRankingBaseNormal() {
        return rankingBaseNormal;
    }

    public RankingJogador getRankingBaseRapido() {
        return rankingBaseRapido;
    }

    public RankingJogador getRankingCorridaNormal() {
        return rankingCorridaNormal;
    }

    public RankingJogador getRankingCorridaRapido() {
        return rankingCorridaRapido;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public JogoCareTaker getCareTaker() {
        return careTaker;
    }

    public ListaJogadores getLista() {
        return lista;
    }

    public void setLista(ListaJogadores lista) {
        this.lista = lista;
    }

    public void reiniciarCareTaker() {
        this.careTaker = new JogoCareTaker();
        addObserver(rankingBaseNormal);
        addObserver(rankingBaseRapido);
        addObserver(rankingCorridaNormal);
        addObserver(rankingCorridaRapido);
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public static GestorJogo getInstance() {
        return instance;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public static void setInstance(GestorJogo gestor) {
        instance = gestor;
    }
}
