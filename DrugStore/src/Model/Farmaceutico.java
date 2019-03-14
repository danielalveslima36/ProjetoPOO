package Model;

public class Farmaceutico extends Funcionario {
    private String numeroCRF;

    public Farmaceutico(String cpf, float salario, String telefone, String endereco, String numeroCRF) {
        super(cpf, salario, telefone, endereco);
        this.numeroCRF = numeroCRF;
    }

    public String getNumeroCRF() {
        return numeroCRF;
    }

    public void setNumeroCRF(String numeroCRF) {
        this.numeroCRF = numeroCRF;
    }
}
