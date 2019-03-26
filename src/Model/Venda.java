package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Venda {
    private LocalDate data;
    private LocalDateTime hora;
    private float total;
    private String tipo;

    public Venda(LocalDate data, LocalDateTime hora, float total, String tipo) {
        this.data = data;
        this.hora = hora;
        this.total = total;
        this.tipo = tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalDateTime getHora() {
        return hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "data=" + data +
                ", hora=" + hora +
                ", total=" + total +
                ", tipo='" + tipo + '\'' +
                '}';
    }


}
