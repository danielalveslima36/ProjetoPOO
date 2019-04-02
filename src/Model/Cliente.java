package Model;


import java.time.LocalDate;
import java.util.Objects;
import Enum.Sexo;
import Excecoes.DataNascimentoInvalidaException;

/**
 * A Classe <b>Cliente</b> tem como função guardar métodos e valores para o mesmo.
 * @autora Maria Kelcilne
 * @author Daniel Alves
 * @version 1.0
 * @since 01-04-19
 */


public class Cliente {
    private String nome;
    private String cpf;
    private String endereco;
    private Sexo sexo;
    private LocalDate nacimento;

    public Cliente(String nome, String cpf, String endereco, Sexo sexo, LocalDate nacimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.sexo = sexo;
        this.nacimento = nacimento;
    }


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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereço(String endereço) {
        this.endereco = endereço;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public LocalDate getNacimento() {
        return nacimento;
    }

    public void setNacimento(LocalDate nacimento) throws DataNascimentoInvalidaException {
        if (nacimento.compareTo(LocalDate.now()) > 0) throw new DataNascimentoInvalidaException(
                "Data de nascimento Invalida");
        this.nacimento = nacimento;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereço='" + endereco + '\'' +
                ", sexo='" + sexo + '\'' +
                ", nacimento=" + nacimento +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return cpf.equals(cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
