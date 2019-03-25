package Model;

import java.time.LocalDate;

public class Cliente {
    private String nome;
    private String cpf;
    private String endereço;
    private String sexo;
    private LocalDate nacimento;


    public Cliente(String nome, String cpf, String endereço, String sexo, LocalDate nacimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereço = endereço;
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

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getNacimento() {
        return nacimento;
    }

    public void setNacimento(LocalDate nacimento) {
        this.nacimento = nacimento;
    }

}
