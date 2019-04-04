package Model;

import java.util.Objects;
import Enum.Sessao;

/**
 * A classe <b>Farmacêutico</b> herda de funcionário todos os seus atributo.
 * <i>cpf,matricula,senha,salario...</i>.
 * tem como função desenvolver, guardar e gerenciar  métodos como <i>...</i>
 * @autora Maria Kelcilene
 * @author Daneil Alves
 * @version 1.0
 * @since 01-04-19
 *
 */

public class Farmaceutico extends Funcionario {
    private String numeroCRF;

    /**
     * Construtor com alguns dados iniciais.
     * @param cpf,matricula,senha,salario
     * @param sessao,telefone,endereco,numeroCRF
     */

    public Farmaceutico(String cpf, String matricula, String nome, String senha, float salario, Sessao sessao, String telefone, String endereco, String numeroCRF) {
        super(cpf, matricula, nome, senha, salario, sessao, telefone, endereco);
        this.numeroCRF = numeroCRF;
    }

    /**
     * Retora o numeroCRF do objeto Farmacêutico
     * @return o nuemroCRF
     */

    public String getNumeroCRF() {
        return numeroCRF;
    }


    public void setNumeroCRF(String numeroCRF) {
        this.numeroCRF = numeroCRF;
    }

    @Override
    public String toString() {
        return  "Farmaceutico{" +
                "numeroCRF='" + numeroCRF + '\'' +
                '}' + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Farmaceutico that = (Farmaceutico) o;
        return Objects.equals(numeroCRF, that.numeroCRF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numeroCRF);
    }
}