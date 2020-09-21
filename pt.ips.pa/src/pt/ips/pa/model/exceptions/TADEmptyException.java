package pt.ips.pa.model.exceptions;

/**
 * Classe que lanca uma excecao quando o TAD nao tem elementos.
 * @author Luis Mestre e Andre Bastos
 */
public class TADEmptyException extends RuntimeException {

    /**
     * Construtor da classe TADEmptyException que recebe como parametro uma
     * String que representa a mensagem do erro ocorrido na TAD em especifico.
     * Mais especificamente, e lancado o erro quando a TAD encontra-se sem
     * elementos
     * @param mensagem 
     */
    public TADEmptyException(String mensagem) {
        super(mensagem);
    }
}
