package pt.ips.pa.model.jogo;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import pt.ips.pa.model.exceptions.TADFullException;
import pt.ips.pa.model.interfaces.EstrategiaPontuacao;
import pt.ips.pa.model.patterns.JogoMemento;
import pt.ips.pa.model.patterns.strategies.EstrategiaPontuacaoBase;
import pt.ips.pa.model.tads.TADConjuntoAleatorio;

/**
 * Classe Jogo e uma classe abstrata que ira representar o jogo com as regras
 * gerais.
 *
 * @author Luis Mestre e Andre Bastos
 */
public class Jogo extends Observable implements Serializable {

    /**
     * Atributo do tipo Jogador que guarda o Jogador que esta a jogar.
     */
    private Jogador jogador;

    /**
     * Atributo do tipo Tabuleiro que guarda o tabuleiro em jogo.
     */
    private Tabuleiro tabuleiro;

    /**
     * Atributo do tipo boolean que guarda true caso o jogador perca ou false
     * caso ainda nao tenha perdido.
     */
    private boolean perdeu;

    /**
     * Atributo do tipo long que guarda o tempo em que o jogo comecou (em
     * segundos).
     */
    private long time;

    /**
     * Atributo do tipo Random que guarda a instancia usada para obter numeros
     * aleatorios
     */
    private Random random;

    /**
     * Atributo do tipo TADConjuntoAleatorio que ira guardar os elementos que
     * irao aparecer nas linhas do jogo.
     */
    private TADConjuntoAleatorio conjunto;

    /**
     * Atributos do tipo peca que irao guardar as pecas que vao ser adicionadas
     * respetivamente a esquerda e a direita de uma linha do jogo.
     */
    private Peca<String> elemEsquerdaAJogar, elemDireitaAJogar;

    /**
     * Atributos do tipo peca que irao guardar as pecas que vao a jogo para
     * adicionar, respetivamente a esquerda e a direita, depois de terem sido
     * adicionadas as pecas que estao guardas nos atributos anteriormente
     * referidos.
     */
    private Peca<String> proxElemEsquerda, proxElemDireita;

    /**
     * Atributo do tipo int que guarda a pontuacao do jogador que esta a jogar.
     */
    private int pontuacao;

    /**
     * Atributo do tipo EstrategiaPontuacao que guarda a estrategia da pontuacao
     * do jogo. Isto e, faz o calculo do valor de cada peca destruida de acordo
     * com a estrategia enviada.
     */
    private EstrategiaPontuacao estrategia;

    /**
     * Atributo do tipo int que guarda a última jogada feita pelo jogador.
     */
    private int jogada;

    /**
     * Construtor da classe que recebe como parametros um jogador e um
     * tabuleiro. Guarda-os nos atributos jogador e tabuleiro (respetivamente) e
     * inicializa os restantes atributos.
     *
     * @param jogador
     * @param tabuleiro
     * @param conjunto
     * @param estrategia
     */
    public Jogo(Jogador jogador, Tabuleiro tabuleiro, TADConjuntoAleatorio conjunto, EstrategiaPontuacao estrategia) {
        this.random = new Random();
        this.perdeu = false;
        this.jogador = jogador;
        this.tabuleiro = tabuleiro;
        this.conjunto = conjunto;
        this.estrategia = estrategia;
        this.jogada = 0;
        this.time = System.currentTimeMillis() / 1000;
        this.pontuacao = (estrategia instanceof EstrategiaPontuacaoBase) ? 0 : 1000;
        iniciarPecas();
        iniciarLinhas();
    }

    /**
     * Metodo que adicionar os observers
     * @param observer 
     */
    public void addObservador(Observer observer){
        addObserver(observer);
    }
    
    public void notifyInicio(){
        setChanged();
        notifyObservers("Inicio");
    }
    
    /**
     * Metodo que adiciona os primeiros elementos a cada linha.
     */
    public final void iniciarLinhas() {
        for (int i = 0; i < tabuleiro.getLinhas().length; i++) {
            tabuleiro.addElements(i, new Peca(getConjunto().peek()), new Peca(getConjunto().peek()));
        }
    }

    /**
     * Metodo que inicia as Pecas do jogo, em relacao com as que estao em jogo e
     * com as que vao a jogo a seguir
     */
    private void iniciarPecas() {
        this.elemDireitaAJogar = new Peca(conjunto.peek());
        this.elemEsquerdaAJogar = new Peca(conjunto.peek());
        this.proxElemDireita = new Peca(conjunto.peek());
        this.proxElemEsquerda = new Peca(conjunto.peek());
    }

    /**
     * Metodo que recebe como parametro um int que representa a linha ou jogada
     * escolhida pelo jogador.
     *
     * @param indice
     */
    public void jogada(int indice) {
        try {
            jogada = indice;
            int pecasRemovidas = tabuleiro.addElements(indice, elemEsquerdaAJogar, elemDireitaAJogar);
            atualizarPontuacao(pecasRemovidas);
            atualizarPecas();
            setChanged();
            notifyObservers("Jogada");
        } catch (TADFullException e) {
            setPerdeu(true);
        }
    }

    /**
     * Metodo que atualiza o valor das pecas que vao a jogo e as pecas que estao
     * em jogo para adicionar as linhas.
     */
    public void atualizarPecas() {
        setElemDireitaAJogar(getProxElemDireita());
        setElemEsquerdaAJogar(getProxElemEsquerda());
        setProxElemEsquerda(createPeca());
        setProxElemDireita(createPeca());
    }

    /**
     * Metodo que devolve uma peca. A peca sera especial caso o metodo odd
     * retorna true ou sera normal caso o metodo odd retorna false.
     *
     * @return uma peca
     */
    private Peca createPeca() {
        return odd(conjunto.size()) ? new PecaEspecial(getConjunto().peek()) : new Peca(getConjunto().peek());
    }

    /**
     * Metodo que retorna a peca que esta em jogo para adicionar a esquerda de
     * uma linha do tabuleiro.
     *
     * @return peca que vai ser adicionado a esquerda de uma linha
     */
    public Peca getElemEsquerdaAJogar() {
        return elemEsquerdaAJogar;
    }

    /**
     * Metodo que altera o valor do atributo que tem a peca que se vai adicionar
     * a esquerda de uma linha do tabuleiro.
     *
     * @param elemEsquerdaAJogar
     */
    public void setElemEsquerdaAJogar(Peca elemEsquerdaAJogar) {
        this.elemEsquerdaAJogar = elemEsquerdaAJogar;
    }

    /**
     * Metodo que retorna a peca que esta em jogo para adicionar a direita de
     * uma linha do tabuleiro.
     *
     * @return peca que vai ser adicionado a direita de uma linha
     */
    public Peca getElemDireitaAJogar() {
        return elemDireitaAJogar;
    }

    /**
     * Metodo que altera o valor do atributo que tem a peca que se vai adicionar
     * a direita de uma linha do tabuleiro.
     *
     * @param elemDireitaAJogar
     */
    public void setElemDireitaAJogar(Peca elemDireitaAJogar) {
        this.elemDireitaAJogar = elemDireitaAJogar;
    }

    /**
     * Metodo que retorna a peca que vai para adicionar a esquerda de uma linha
     * do tabuleiro.
     *
     * @return peca que vai a jogo para adicionar a esquerda de uma linha
     */
    public Peca getProxElemEsquerda() {
        return proxElemEsquerda;
    }

    /**
     * Metodo que altera o valor do atributo que tem a peca que vai para
     * adicionar a esquerda de uma linha do tabuleiro.
     *
     * @param proxElemEsquerda
     */
    public void setProxElemEsquerda(Peca proxElemEsquerda) {
        this.proxElemEsquerda = proxElemEsquerda;
    }

    /**
     * Metodo que retorna a peca que vai para adicionar a direita de uma linha
     * do tabuleiro.
     *
     * @return peca que vai a jogo para adicionar a direita de uma linha
     */
    public Peca getProxElemDireita() {
        return proxElemDireita;
    }

    /**
     * Metodo que altera o valor do atributo que tem a peca que vai para
     * adicionar a direita de uma linha do tabuleiro.
     *
     * @param proxElemDireita
     */
    public void setProxElemDireita(Peca proxElemDireita) {
        this.proxElemDireita = proxElemDireita;
    }

    /**
     * Metodo que devolve o conjunto de pecas que estao e vao estar no tabuleiro
     *
     * @return o conjunto de pecas
     */
    public TADConjuntoAleatorio getConjunto() {
        return conjunto;
    }

    /**
     * Metodo que retorna a pontuacao do jogador.
     *
     * @return a pontuacao do jogador
     */
    public int getPontuacao() {
        return pontuacao;
    }

    /**
     * Metodo que devolve a estrategia de pontuacao usada
     *
     * @return a estrategia usada
     */
    public EstrategiaPontuacao getEstrategia() {
        return estrategia;
    }

    /**
     * Metodo que devolve o tempo de jogo
     *
     * @return tempo de jogo jogado
     */
    public long getTime() {
        return time-1;
    }

    /**
     * Metodo que atualiza o tempo de jogo jogado.
     *
     * @param time
     */
    public void setTime(long time) {
        this.time = time;
    }

    /**
     * Metodo que recebe como parametro um boolean e ira guardar esse parametro
     * no atributo perdeu.
     *
     * @param perdeu
     */
    protected void setPerdeu(boolean perdeu) {
        setTime(System.currentTimeMillis() / 1000 - getTime());
        jogador.add(this);
        this.perdeu = perdeu;
        setChanged();
        notifyObservers("Fim");
    }

    /**
     * Metodo que retorna o Jogador que esta a jogar.
     *
     * @return o Jogador que esta a jogar.
     */
    public Jogador getJogador() {
        return jogador;
    }

    /**
     * Metodo que retorna o tabuleiro do jogo.
     *
     * @return o tabuleiro do jogo.
     */
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    /**
     * Metodo que retorna o boolean perdeu.
     *
     * @return perdeu
     */
    public boolean isPerdeu() {
        return perdeu;
    }

    /**
     * Metodo que guarda o estado do jogo.
     *
     * @return o novo estado (memento) do jogo.
     */
    public JogoMemento save() {
        return new JogoMemento(tabuleiro.getLinhas(), elemEsquerdaAJogar, elemDireitaAJogar, proxElemEsquerda, proxElemDireita, pontuacao, estrategia);
    }

    /**
     * Metodo que altera o estado do jogo para um que foi gravado.
     *
     * @param memento
     */
    public void restore(JogoMemento memento) {
        this.tabuleiro.setLinhas(memento.copiaLinhas(memento.getLinhas()));
        this.elemDireitaAJogar = memento.getElemDireitaAJogar();
        this.elemEsquerdaAJogar = memento.getElemEsquerdaAJogar();
        this.proxElemDireita = memento.getProxElemDireita();
        this.proxElemEsquerda = memento.getProxElemEsquerda();
        this.pontuacao = memento.getPontuacao();
        this.estrategia = memento.getEstrategia();
        setChanged();
        notifyObservers("Undo");
    }

    /**
     * Metodo que devolve um boolean. true caso o random seja 2 ou false caso
     * nao o seja
     *
     * @param tamanho
     * @return true caso o algoritmo seja 9 ou false caso nao o seja
     */
    private boolean odd(int tamanho) {
        return (random.nextInt(tamanho) + 1) * (random.nextInt(tamanho) + 1) == 9;
    }

    /**
     * Metodo que devolve a ultima jogada feita pelo jogador.
     *
     * @return jogada feita pelo jogador
     */
    public int getJogada() {
        return jogada;
    }

    /**
     * Metodo que atualiza a pontuacao
     *
     * @param pecasRemovidas
     */
    public void atualizarPontuacao(int pecasRemovidas) {
        pontuacao += estrategia.calcularPontuacao(pecasRemovidas);
    }
}
