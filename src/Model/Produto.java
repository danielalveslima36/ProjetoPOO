package Model;

import java.time.LocalDate;
import java.util.Objects;
import Enum.Sessao;

/**
 * A classe  <b>Produto</b> modela a propriedade produto .
 * Composto pelos seus atributos gerais.
 * @autora Maria Kelcilene
 * @author Daniel Alves
 * @version 1.0
 * @since 01-04-19
 */

public class Produto {
    private String decricao;
    private LocalDate validade;
    private String codigoDeBarras;
    private Sessao sessao;
    private float precoUnitario;
    private String fabricante;

    public Produto(String decricao, LocalDate validade, String codigoDeBarras, Sessao sessao, float precoUnitario, String fabricante) {
        this.decricao = decricao;
        this.validade = validade;
        this.codigoDeBarras = codigoDeBarras;
        this.sessao = sessao;
        this.precoUnitario = precoUnitario;
        this.fabricante = fabricante;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public String getDecricao() {
        return decricao;
    }

    public void setDecricao(String decricao) {
        this.decricao = decricao;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public float getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(float precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "decricao='" + decricao + '\'' +
                ", validade=" + validade +
                ", codigoDeBarras='" + codigoDeBarras + '\'' +
                ", precoUnitario=" + precoUnitario +
                ", fabricante='" + fabricante + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return codigoDeBarras.equals(produto.codigoDeBarras);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoDeBarras);
    }
}
