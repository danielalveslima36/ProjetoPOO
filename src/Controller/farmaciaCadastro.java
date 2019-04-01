package Controller;

import Alerts.Alertas;
import BancoDAO.FarmaciaDaoBanco;
import Model.Farmacia;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import util.TextFieldFormatter;

import java.sql.SQLException;

public class farmaciaCadastro {

    Alertas alerta = new Alertas();
    FarmaciaDaoBanco daoFarmacia = new FarmaciaDaoBanco();

    @FXML
    private TextField cnpj;

    @FXML
    private TextField nome;

    @FXML
    private TextField endereco;

    @FXML
    private TextField telefone;

    @FXML
    void cadastrar(ActionEvent event) {
        if (cnpj.getText().isEmpty() || nome.getText().isEmpty() || endereco.getText().isEmpty() || telefone.getText().isEmpty()){
            alerta.Warning("Atenção", "Preencha todos os campos");
        }else{
            try {
                if (daoFarmacia.salvar(new Farmacia(cnpj.getText(), nome.getText(), endereco.getText(), telefone.getText()))){
                    alerta.Confirmation("Parabéns", "Cadastro realizado com sucesso");
                }else alerta.Error("Erro", "Cadastro não relizado");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void tfCnpj(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();

        tff.setMask("##.###.###/####-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(cnpj);
        tff.formatter();
    }

    @FXML
    void tfNome(KeyEvent event) {
    }

    @FXML
    void tfTelefone(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("(##)#####-####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(telefone);
        tff.formatter();
    }
}
