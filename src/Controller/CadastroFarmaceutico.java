package Controller;

import Alerts.Alertas;
import BancoDAO.FarmaceuticoDaoBanco;
import Main.TelaPrincipal;
import Model.Farmaceutico;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import util.TextFieldFormatter;
import Enum.Sessao;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CadastroFarmaceutico implements Initializable {

    FarmaceuticoDaoBanco dao  = new FarmaceuticoDaoBanco();
    Alertas alerta = new Alertas();
    @FXML
    private ImageView logo;

    @FXML
    private TextField cpf;

    @FXML
    private TextField matricula;

    @FXML
    private TextField endereco;

    @FXML
    private TextField telefone;

    @FXML
    private TextField salario;

    @FXML
    private TextField nome;

    @FXML
    private ComboBox<Sessao> sessao;

    @FXML
    private PasswordField senha;

    @FXML
    private TextField crf;

    @FXML
    void cadastrar(ActionEvent event) {
        if (cpf.getText().isEmpty() || matricula.getText().isEmpty() || nome.getText().isEmpty() || endereco.getText().isEmpty() || telefone.getText().isEmpty() || senha.getText().isEmpty() || salario.getText().isEmpty() || sessao.getItems().isEmpty()){
            alerta.Warning("Atenção", "Preencha todos os campos");
        }
        else{
            try {
                if (dao.salvar(new Farmaceutico(cpf.getText(), matricula.getText(), nome.getText(), senha.getText(), Float.valueOf(salario.getText()), sessao.getValue(), telefone.getText(), endereco.getText(), crf.getText()))){
                    alerta.Information("Parabéns", "Cadastro realizado com sucesso");
                    TelaPrincipal.changeScreen("login");
                    limpar();
                }else alerta.Error("Erro", "Falha ao cadastrar");
            } catch (SQLException e) {
                alerta.Error("Erro",  "Funcionario ja cadastrado");
            } catch (ClassNotFoundException e) {
                alerta.Error("Atenção",  "Classe não encontrada");
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
    void tfCrf(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("#######-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(crf);
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
    void tfMatricula(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("#######-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(matricula);
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


    @FXML
    void voltar(ActionEvent event) {
        limpar();
        TelaPrincipal.changeScreen("login");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("imagens/pill.png");
        Image image = new Image(file.toURI().toString());
        logo.setImage(image);

        sessao.getItems().addAll(Sessao.values());

    }



    public void limpar(){
        cpf.setText("");
        matricula.setText("");
        telefone.setText("");
        senha.setText("");
        salario.setText("");
        sessao.setValue(null);
        endereco.setText("");
        crf.setText("");
        nome.setText("");
    }

}
