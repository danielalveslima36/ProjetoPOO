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
    private String telefone;

    /**
     * Construtor com dados iniciais
     * @param nome,cpf,telefone
     * @param endereco,sexo,nacimento
     */

    public Cliente(String nome, String cpf, String telefone, String endereco, Sexo sexo, LocalDate nacimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.sexo = sexo;
        this.nacimento = nacimento;
    }




    /**
     * Gets e sets que tem retorno do objeto Cliente
     * @return nome,cpf,endereço,sexo,nacimento
     */

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public LocalDate getNacimento() throws DataNascimentoInvalidaException {
        if (nacimento.compareTo(LocalDate.now()) > 0) throw new DataNascimentoInvalidaException(
                "Data de nascimento Invalida");
        return nacimento;
    }

    public void setNacimento(LocalDate nacimento) {
        this.nacimento = nacimento;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco='" + endereco + '\'' +
                ", sexo=" + sexo +
                ", nacimento=" + nacimento +
                ", telefone='" + telefone + '\'' +
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
