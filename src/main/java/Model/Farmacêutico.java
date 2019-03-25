package Model;

import java.util.Objects;

public class Farmacêutico extends Funcionario {
    private String numeroCRF;

    public Farmacêutico(String cpf, String matricula, float salario, String telefone, String endereco, String numeroCRF) {
        super(cpf, matricula, salario, telefone, endereco);
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
        return "Farmacêutico{" +
                "numeroCRF='" + numeroCRF + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Farmacêutico that = (Farmacêutico) o;
        return numeroCRF.equals(that.numeroCRF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numeroCRF);
    }
}
