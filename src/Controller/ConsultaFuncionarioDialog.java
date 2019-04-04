package Controller;

import Alerts.Alertas;
import Model.Funcionario;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import Enum.Sessao;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.TextFieldFormatter;

import java.net.URL;
import java.util.ResourceBundle;

public class ConsultaFuncionarioDialog implements Initializable {

    Alertas alerta = new Alertas();
    @FXML
    private TextField TextCpf;

    @FXML
    private TextField TextMatricula;

    @FXML
    private TextField TextNome;

    @FXML
    private TextField TextSenha;

    @FXML
    private TextField TextSalario;

    @FXML
    private TextField TextEndereco;

    @FXML
    private TextField TextTelefone;

    @FXML
    private ComboBox<Sessao> TextSessao;

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Funcionario funcionario ;

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
        this.buttonConfirmarClicked = buttonConfirmarClicked;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
        this.TextCpf.setText(funcionario.getCpf());
        this.TextMatricula.setText(funcionario.getMatricula());
        this.TextNome.setText(funcionario.getNome());
        this.TextSenha.setText(funcionario.getSenha());
        this.TextEndereco.setText(funcionario.getEndereco());
        this.TextSalario.setText(String.valueOf(funcionario.getSalario()));
        this.TextSessao.setValue(funcionario.getSessao());
    }

    @FXML
    void cancelar(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    void ok(ActionEvent event) {
        if (TextCpf.getText().isEmpty() || TextSessao.getItems().isEmpty() || TextSalario.getText().isEmpty() || TextMatricula.getText().isEmpty() || TextNome.getText().isEmpty() || TextEndereco.getText().isEmpty() || TextSenha.getText().isEmpty()){
            alerta.Warning("Atenção", "Preencha todos os campos");
        }else{
            funcionario.setCpf(TextCpf.getText());
            funcionario.setEndereco(TextEndereco.getText());
            funcionario.setMatricula(TextMatricula.getText());
            funcionario.setSessao(TextSessao.getValue());
            funcionario.setSenha(TextSenha.getText());
            funcionario.setTelefone(TextTelefone.getText());
            funcionario.setNome(TextNome.getText());

            buttonConfirmarClicked = true;

            dialogStage.close();
        }
    }

    @FXML
    void tfCpf(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("###.###.###-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(TextCpf);
        tff.formatter();
    }

    @FXML
    void tfMatricula(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("#######-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(TextMatricula);
        tff.formatter();
    }

    @FXML
    void tfSalario(KeyEvent event) {
        TextSalario.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")){
                    TextSalario.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @FXML
    void tfTelefone(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("(##)#####-####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(TextTelefone);
        tff.formatter();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextSessao.getItems().setAll(Sessao.values());
    }
}
