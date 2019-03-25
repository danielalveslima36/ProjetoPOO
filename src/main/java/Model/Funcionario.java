package Model;

import java.util.Objects;

public class Funcionario {
    private String cpf;
    private String matricula;
    private float salario;
    private String telefone;
    private String endereco;

    public Funcionario(String cpf, String matricula, float salario, String telefone, String endereco) {
        this.cpf = cpf;
        this.matricula = matricula;
        this.salario = salario;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "cpf='" + cpf + '\'' +
                ", matricula='" + matricula + '\'' +
                ", salario=" + salario +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return matricula.equals(that.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }
}
