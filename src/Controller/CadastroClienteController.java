package Controller;

import Alerts.Alertas;
import BancoDAO.ClienteDaoBanco;
import Model.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import Enum.*;
import javafx.scene.input.KeyEvent;
import util.TextFieldFormatter;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CadastroClienteController implements Initializable {

    Alertas alerta = new Alertas();
    ClienteDaoBanco daoCliente = new ClienteDaoBanco();
    @FXML
    private TextField cpf;

    @FXML
    private TextField nome;

    @FXML
    private TextField endereco;

    @FXML
    private ComboBox<Sexo> sexo;

    @FXML
    private DatePicker nascimento;

    @FXML
    private TextField telefone;

    @FXML
    void cadastrar(ActionEvent event) {
        if (cpf.getText().isEmpty() || nome.getText().isEmpty() || endereco.getText().isEmpty() || nascimento.getValue() == null || telefone.getText().isEmpty() || sexo.getItems().isEmpty()){
            alerta.Warning("Atenção", "Preecha todos os campos");
        }else{
            try {
                if (daoCliente.salvar(new Cliente(nome.getText(), cpf.getText(), telefone.getText(), endereco.getText(), sexo.getValue(), nascimento.getValue()))){
                    alerta.Information("Parabéns", "Cliente cadastrado com sucesso");
                    limpar();
                }else alerta.Error("Erro", "Falha ao cadastrar");
            } catch (SQLException e) {
                alerta.Error("Erro",  "Produto ja cadastrado");
            } catch (ClassNotFoundException e) {
                alerta.Error("Atenção",  "Classe não encontrada");
            }
        }
    }

    private void limpar() {
        cpf.setText("");
        endereco.setText("");
        nascimento.setValue(null);
        sexo.setValue(null);
        telefone.setText("");
        nome.setText("");
    }

    @FXML
    void tfCpf(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("###.###.###-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(cpf);
        tff.formatter();
    }

    @FXML
    void tfTelefone(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("(##)#####-####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(telefone);
        tff.formatter();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sexo.getItems().setAll(Sexo.values());
    }
}
