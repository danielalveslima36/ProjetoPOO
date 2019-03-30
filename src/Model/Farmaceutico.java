package Model;

import java.util.Objects;
import Enum.Sessao;

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