package Model;

import java.time.LocalDate;

public class Venda {
    private LocalDate data;
    private float total;
    private float hora;
    private String tipo;

    public Venda(LocalDate data, float total, float hora) {
        this.data = data;
        this.total = total;
        this.hora = hora;
        this.tipo = tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getHora() {
        return hora;
    }

    public void setHora(float hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
