package Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import Enum.TipoVenda;

/**
 * A classe <b>Venda</b> modela a entidade venda no domínio da aplicação.
 *
 * @autora Maria Kelcilne
 * @author Dsaniel Alves
 * @version 1.0
 * @since 01-04-19
 *
 */

public class Venda {
    private int codVenda;
    private LocalDate data;
    private LocalTime hora;
    private float total;
    private TipoVenda tipo;
    private String funcionario;
    private String cliente;

    public Venda(int codVenda, LocalDate data, LocalTime hora, float total, TipoVenda tipo, String funcionario, String cliente) {
        this.codVenda = codVenda;
        this.data = data;
        this.hora = hora;
        this.total = total;
        this.tipo = tipo;
        this.funcionario = funcionario;
        this.cliente = cliente;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
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

    public LocalTime getHora() {
        return hora;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public TipoVenda getTipo() {
        return tipo;
    }

    public void setTipo(TipoVenda tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "codVenda=" + codVenda +
                ", data=" + data +
                ", hora=" + hora +
                ", total=" + total +
                ", tipo='" + tipo + '\'' +
                ", funcionario='" + funcionario + '\'' +
                ", cliente='" + cliente + '\'' +
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
