package Controller;

import Alerts.Alertas;
import Model.Produto;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.TextFieldFormatter;
import Enum.Sessao;

import java.net.URL;
import java.util.ResourceBundle;

public class ConsultaProdutoDialog implements Initializable {

    Alertas alerta = new Alertas();
    @FXML
    private TextField textCodigo;

    @FXML
    private TextField textNome;

    @FXML
    private TextField textValor;

    @FXML
    private TextField textFabricante;

    @FXML
    private ComboBox<Sessao> textSessao;

    @FXML
    private DatePicker textValidade;

    @FXML
    private TextField textDescricao;

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Produto produto ;

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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        textSessao.setValue(produto.getSessao());
        textValor.setText(String.valueOf(produto.getPrecoUnitario()));
        textCodigo.setText(produto.getCodigoDeBarras());
        textDescricao.setText(produto.getDecricao());
        textFabricante.setText(produto.getDecricao());
        textNome.setText(produto.getNome());
        textValidade.setValue(produto.getValidade());
    }

    @FXML
    void cancelar(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    void ok(ActionEvent event) {
        if (textValidade.getValue() == null || textNome.getText().isEmpty() || textFabricante.getText().isEmpty() || textDescricao.getText().isEmpty() || textCodigo.getText().isEmpty() || textValor.getText().isEmpty() || textSessao.getItems().isEmpty()){
            alerta.Warning("Atenção", "Preencha todos os campos");
        }else{
            produto.setCodigoDeBarras(textCodigo.getText());
            produto.setDecricao(textDescricao.getText());
            produto.setFabricante(textFabricante.getText());
            produto.setNome(textNome.getText());
            produto.setValidade(textValidade.getValue());
            produto.setPrecoUnitario(Float.valueOf(textValor.getText()));
            produto.setSessao(Sessao.valueOf(String.valueOf(textSessao.getValue())));

            buttonConfirmarClicked = true;

            dialogStage.close();
        }
    }

    @FXML
    void tfCodigo(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("############");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(textCodigo);
        tff.formatter();
    }

    @FXML
    void tfValor(KeyEvent event) {
        textValor.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")){
                    textValor.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textSessao.getItems().setAll(Sessao.values());
    }
}
