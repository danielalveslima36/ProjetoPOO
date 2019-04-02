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

    public Farmaceutico(String cpf, String matricula, String senha, float salario, Sessao sessao, String telefone, String endereco, String numeroCRF) {
        super(cpf, matricula, senha, salario, sessao, telefone, endereco);
        this.numeroCRF = numeroCRF;
    }

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