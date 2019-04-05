package Controller;

import Alerts.Alertas;
import BancoDAO.FarmaciaDaoBanco;
import BancoDAO.ProdutoDaoBanco;
import Model.Produto;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import Enum.Sessao;
import javafx.scene.input.KeyEvent;
import util.TextFieldFormatter;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CadastroProdutoController implements Initializable {
    Alertas alerta = new Alertas();
    ProdutoDaoBanco daoProduto = new ProdutoDaoBanco();
    FarmaciaDaoBanco daoFarmacia = new FarmaciaDaoBanco();
    @FXML
    private TextField codigo;

    @FXML
    private TextField nome;

    @FXML
    private TextField descricao;

    @FXML
    private DatePicker validade;

    @FXML
    private TextField fabricante;

    @FXML
    private ComboBox<Sessao> sessao;

    @FXML
    private TextField valor;

    @FXML
    void cadastrar(ActionEvent event) {
        if (codigo.getText().isEmpty() || nome.getText().isEmpty() || descricao.getText().isEmpty() || validade.getValue() == null || fabricante.getText().isEmpty() || sessao.getItems().isEmpty() || valor.getText().isEmpty()){
            alerta.Warning("Atenção", "Preecha todos os campos");
        }else{
            try {
                if (daoProduto.salvar(new Produto(descricao.getText(), nome.getText(), validade.getValue(), codigo.getText(), sessao.getValue(), Float.valueOf(valor.getText()), fabricante.getText()))){
                    alerta.Information("Parabéns", "Produto cadastrado com sucesso");
                    limpar();
                }else alerta.Error("Erro", "Falha ao cadastrar");
            } catch (SQLException e) {
                alerta.Error("Erro",  "Produto ja cadastrado");
            } catch (ClassNotFoundException e) {
                alerta.Error("Atenção",  "Classe não encontrada");
            }
        }
    }



    @FXML
    void tfCodigo(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("############");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(codigo);
        tff.formatter();
    }

    @FXML
    void tfValor(KeyEvent event) {
        valor.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")){
                    valor.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sessao.getItems().setAll(Sessao.values());
    }

    public void limpar(){
        codigo.setText("");
        nome.setText("");
        descricao.setText("");
        validade.setValue(null);
        fabricante.setText("");
        sessao.setValue(null);
        valor.setText("");
    }

}
