package Controller;

import Alerts.Alertas;
import Excecoes.DataNascimentoInvalidaException;
import Model.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.TextFieldFormatter;
import Enum.Sexo;

import java.net.URL;
import java.util.ResourceBundle;

public class ConsultaClienteDialog implements Initializable {

    Alertas alerta = new Alertas();

    @FXML
    private TextField Textcpf;

    @FXML
    private TextField Textnome;

    @FXML
    private TextField Textendereco;

    @FXML
    private TextField Texttelefone;

    @FXML
    private ComboBox<Sexo> Textsexo;

    @FXML
    private DatePicker TextNascimento;

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Cliente cliente ;

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) throws DataNascimentoInvalidaException {
        this.cliente = cliente;
        this.Textcpf.setText(cliente.getCpf());
        this.Textendereco.setText(cliente.getEndereco());
        this.Textsexo.setValue(cliente.getSexo());
        this.Texttelefone.setText(cliente.getTelefone());
        this.Textnome.setText(cliente.getNome());
        this.TextNascimento.setValue(cliente.getNacimento());
    }

    @FXML
    void cancelar(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    void ok(ActionEvent event) {
        if (Textcpf.getText().isEmpty() || Textsexo.getItems().isEmpty() || Textnome.getText().isEmpty() || Textendereco.getText().isEmpty() || Texttelefone.getText().isEmpty() || TextNascimento.getValue() == null){
            alerta.Warning("Atenção", "Preencha todos os campos");
        }else{
            cliente.setCpf(Textcpf.getText());

            cliente.setSexo(Sexo.valueOf(String.valueOf(Textsexo.getValue())));
            cliente.setNome(Textnome.getText());
            cliente.setEndereco(Textendereco.getText());
            cliente.setTelefone(Texttelefone.getText());
            cliente.setNacimento(TextNascimento.getValue());

            buttonConfirmarClicked = true;

            dialogStage.close();
        }
    }

    @FXML
    void tfCpf(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("###.###.###-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(Textcpf);
        tff.formatter();
    }

    @FXML
    void tfTelefone(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("(##)#####-####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(Texttelefone);
        tff.formatter();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Textsexo.getItems().setAll(Sexo.values());
    }
}
