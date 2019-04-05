package Model;

import java.util.Objects;

/**
 * A classe <b>Farmácia</b> é composta por : <i>cnpj,razão social,endereço,telefone</i>.
 • Ela controla atributos como :<i>Excluir farmácia, listar funcionários</i>, entre outros.
 * @author Maria Kelcilene
 * @author Daniel Alves
 * @version 1.0
 * @since 01-04-19
 */


public class Farmacia {
    private String cnpj;
    private String razaoSocial;
    private String endereco;
    private String telefone;

    /**
     * Construtor da classe Farmácia com dados iniciais
     * @param cnpj,raaoSocail,endereço,telefone
     */

    public Farmacia(String cnpj, String razaoSocial, String endereco, String telefone) {
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    /**
     * Gets e sets com retorno do objeto Farmácia
     * @return cnj,raaoSaocial,endereço,telefone
     */

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getEndereco() {
        return endereco;
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

    @Override
    public String toString() {
        return "Farmacia{" +
                "cnpj='" + cnpj + '\'' +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Farmacia farmacia = (Farmacia) o;
        return cnpj.equals(farmacia.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj);
    }
}
