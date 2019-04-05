package Excecoes;

/**
 * A classe <b>DataNacimentoInvalidaEsception</b>
 * tem como objetivo tratar a exceçao de data invalida.
 * @autora Maria kelcilene
 * @author Daniel Alves
 * @version 1.0
 * @since 04-04-19
 */

public class DataNascimentoInvalidaException extends Exception {

    public DataNascimentoInvalidaException(String mensagem){
        super(mensagem);
    }
}
