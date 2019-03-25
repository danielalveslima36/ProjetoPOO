package Model;

import java.time.LocalDate;

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
}
