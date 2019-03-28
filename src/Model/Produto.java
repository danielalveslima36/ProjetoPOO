package Model;

import java.time.LocalDate;
import java.util.Objects;

public class Produto {
    private String decricao;
    private LocalDate validade;
    private String codigoDeBarras;
    private float precoUnitario;
    private String fabricante;

    public Produto(String decricao, LocalDate validade, String codigoDeBarras, float precoUnitario, String fabricante) {
        this.decricao = decricao;
        this.validade = validade;
        this.codigoDeBarras = codigoDeBarras;
        this.precoUnitario = precoUnitario;
        this.fabricante = fabricante;
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
