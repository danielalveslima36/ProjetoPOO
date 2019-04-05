package Controller;

import Alerts.Alertas;
import Model.Farmacia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.TextFieldFormatter;

import java.net.URL;
import java.util.ResourceBundle;

public class ConsultaFarmaciaDialog implements Initializable {
    Alertas alerta = new Alertas();
    @FXML
    private TextField Textcnpj;

    @FXML
    private TextField Textnome;

    @FXML
    private TextField Textendereco;

    @FXML
    private TextField Texttelefone;

    @FXML
    void tfCnpj(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();

        tff.setMask("##.###.###/####-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(Textcnpj);
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

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Farmacia farmacia;

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

    public Farmacia getFarmacia() {
        return farmacia;
    }

    public void setFarmacia(Farmacia farmacia) {
        this.farmacia = farmacia;
        this.Textcnpj.setText(farmacia.getCnpj());
        this.Textnome.setText(farmacia.getRazaoSocial());
        this.Textendereco.setText(farmacia.getEndereco());
        this.Texttelefone.setText(farmacia.getTelefone());
    }

    @FXML
    void ok(ActionEvent event) {
        if (Textcnpj.getText().isEmpty() || Texttelefone.getText().isEmpty() || Textnome.getText().isEmpty() || Textendereco.getText().isEmpty()){
            alerta.Warning("Atenção", "Preencha todos os campos");
        }else {
            farmacia.setCnpj(Textcnpj.getText());
            farmacia.setRazaoSocial(Textnome.getText());
            farmacia.setEndereco(Textendereco.getText());
            farmacia.setTelefone(Texttelefone.getText());

            buttonConfirmarClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    void cancelar(ActionEvent event) {
        dialogStage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
