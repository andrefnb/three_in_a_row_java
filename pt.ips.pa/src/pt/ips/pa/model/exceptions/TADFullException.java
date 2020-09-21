package pt.ips.pa.model.exceptions;

/**
 * Classe que lanca uma excecão quando o TAD esta cheio. Isto e, atingiu a
 * capacidade maxima de numero de elementos.
 * @author Luis Mestre e Andre Bastos
 */
public class TADFullException extends RuntimeException {

    /**
     * Construtor da classe TADFullException que recebe como parametro uma
     * String que representa a mensagem do erro ocorrido na TAD em especifico.
     * Mais especificamente, e lancado o erro quando a TAD encontra-se com o
     * numero de elementos igual a capacidade maxima.
     * @param mensagem 
     */
    public TADFullException(String mensagem) {
        super(mensagem);
    }
    
}
