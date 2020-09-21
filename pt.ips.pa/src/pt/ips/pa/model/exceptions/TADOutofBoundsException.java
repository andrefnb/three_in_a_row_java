package pt.ips.pa.model.exceptions;

/**
 * Classe que lança uma excecao quando o rank e invalido.
 * @author Luís Mestre e André Bastos
 */
public class TADOutofBoundsException extends RuntimeException {

    /**
     * Construtor da classe TADOutofBoundsException que recebe como parametro um
     * int que representa o rank inserido pelo utilizador que e considerado
     * invalido
     * @param rank o rank invalido inserido pelo utilizador
     */
    public TADOutofBoundsException(int rank) {
        super("O rank " + rank + " é inválido");
    }
}
