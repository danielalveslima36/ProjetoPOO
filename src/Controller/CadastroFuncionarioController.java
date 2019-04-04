package Controller;

import Alerts.Alertas;
import BancoDAO.FuncionarioDaoBanco;
import Model.Funcionario;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import util.TextFieldFormatter;
import Enum.Sessao;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CadastroFuncionarioController implements Initializable {
    Alertas alerta = new Alertas();
    FuncionarioDaoBanco daoFuncioanario = new FuncionarioDaoBanco();

    @FXML
    private TextField cpf;

    @FXML
    private TextField matricula;

    @FXML
    private TextField nome;

    @FXML
    private TextField telefone;

    @FXML
    private TextField endereco;

    @FXML
    private PasswordField senha;

    @FXML
    private TextField salario;

    @FXML
    private ComboBox<Sessao> sessao;

    @FXML
    void cadastrar(ActionEvent event) {
        if (cpf.getText().isEmpty() || matricula.getText().isEmpty() || nome.getText().isEmpty() || endereco.getText().isEmpty() || telefone.getText().isEmpty() || senha.getText().isEmpty() || salario.getText().isEmpty() || sessao.getItems().isEmpty()){
            alerta.Warning("Atenção", "Preencha todos os campos");
        }else{
            try {
                if (daoFuncioanario.salvar(new Funcionario(cpf.getText(), matricula.getText(), nome.getText(), senha.getText(), Float.valueOf(salario.getText()), sessao.getValue(), telefone.getText(), endereco.getText()))){
                    alerta.Confirmation("Parabéns", "Cadastro realizado com sucesso");
                    limpar();
                }else alerta.Error("Erro", "Cadastro não relizado");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
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
    void tfMatricula(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("#######-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(matricula);
        tff.formatter();
    }

    @FXML
    void tfSalario(KeyEvent event) {
        salario.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")){
                    salario.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @FXML
    void tfTelefone(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("(##)#####-####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(telefone);
        tff.formatter();
    }


    public void limpar(){
        cpf.setText("");
        matricula.setText("");
        telefone.setText("");
        senha.setText("");
        salario.setText("");
        sessao.setValue(null);
        endereco.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sessao.getItems().setAll(Sessao.values());
    }

}
