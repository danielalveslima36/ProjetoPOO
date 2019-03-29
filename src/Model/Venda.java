package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Venda {
    private int codVenda;
    private LocalDate data;
    private LocalDateTime hora;
    private float total;
    private String tipo;

    public Venda(int codVenda, LocalDate data, LocalDateTime hora, float total, String tipo) {
        this.codVenda = codVenda;
        this.data = data;
        this.hora = hora;
        this.total = total;
        this.tipo = tipo;
    }

    public int getCodVenda() {
        return codVenda;
    }

    public void setCodVenda(int codVenda) {
        this.codVenda = codVenda;
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
        return "VendaDAO{" +
                "codVenda=" + codVenda +
                ", data=" + data +
                ", hora=" + hora +
                ", total=" + total +
                ", tipo='" + tipo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venda venda = (Venda) o;
        return codVenda == venda.codVenda;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codVenda);
    }
}
