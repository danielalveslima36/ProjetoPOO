package Model;

public class CartaoCredito {
    private float numero;
    private float bandeira;
    private int numeroDeParcelas;
    private String titular;

    public CartaoCredito(float numero, float bandeira, int numeroDeParcelas, String titular) {
        this.numero = numero;
        this.bandeira = bandeira;
        this.numeroDeParcelas = numeroDeParcelas;
        this.titular = titular;
    }

    public float getNumero() {
        return numero;
    }

    public void setNumero(float numero) {
        this.numero = numero;
    }

    public float getBandeira() {
        return bandeira;
    }

    public void setBandeira(float bandeira) {
        this.bandeira = bandeira;
    }

    public int getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(int numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }
}
