package Model;

import java.util.Objects;
import Enum.Sessao;

/**
 * A classe <b> Funcionário </b> tem a capacidade de armazenar informações sobre os seus atributos.
 * @autora Maria Kelcilene
 * @author Daniel Alves
 * @version 1.0
 * @since -01-04-19
 */


public class Funcionario {
    private String cpf;
    private String matricula;
    private String nome;
    private String senha;
    private float salario;
    private Sessao sessao;
    private String telefone;
    private String endereco;

    /**
     * Construtor com os dados inciais da classe Funcionario
     *
     * @param cpf,matricula,nome,senha
     * @param salario,sessao,telefone,endereco
     */

    public Funcionario(String cpf, String matricula, String nome, String senha, float salario, Sessao sessao, String telefone, String endereco) {
        this.cpf = cpf;
        this.matricula = matricula;
        this.nome = nome;
        this.senha = senha;
        this.salario = salario;
        this.sessao = sessao;
        this.telefone = telefone;
        this.endereco = endereco;
    }




    /**
     * Gets e sets do objeto funcionaio
     * @return cpf,matricula,nome,senha,salario,telefone,endereço
     */

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
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
                ", nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                ", salario=" + salario +
                ", sessao=" + sessao +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(matricula, that.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }
}
