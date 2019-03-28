package Model;

import java.util.Objects;

public class Funcionario {
    private String cpf;
    private String matricula;
    private float salario;
    private String telefone;
    private String endereco;
    private String senha;

    public Funcionario(String cpf, String matricula, float salario, String telefone, String endereco, String senha) {
        this.cpf = cpf;
        this.matricula = matricula;
        this.salario = salario;
        this.telefone = telefone;
        this.endereco = endereco;
        this.senha = senha;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return Float.compare(that.salario, salario) == 0 &&
                Objects.equals(cpf, that.cpf) &&
                Objects.equals(matricula, that.matricula) &&
                Objects.equals(telefone, that.telefone) &&
                Objects.equals(endereco, that.endereco) &&
                Objects.equals(senha, that.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf, matricula, salario, telefone, endereco, senha);
    }
}
