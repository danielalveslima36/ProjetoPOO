package Controller;

import Alerts.Alertas;
import BancoDAO.FarmaceuticoDaoBanco;
import BancoDAO.FuncionarioDaoBanco;
import Main.TelaPrincipal;
import Model.Farmaceutico;
import Model.Funcionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import util.TextFieldFormatter;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable{

    Alertas alerta = new Alertas();
    FarmaceuticoDaoBanco daoFarmaceutico = new FarmaceuticoDaoBanco();
    FuncionarioDaoBanco  daoFuncionario  = new FuncionarioDaoBanco();

    @FXML
    private TextField usuario;

    @FXML
    private PasswordField senha;

    @FXML
    private ImageView logo;

    @FXML
    private Button btnLogar;


    @FXML
    void tfUsuario(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("#######-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(usuario);
        tff.formatter();
    }

    @FXML
    public void logar(ActionEvent event) {
        if (usuario.getText().isEmpty() || senha.getText().isEmpty()) {
            alerta.Warning("ATENÇÃO", "PREENCHA TODOS OS CAMPOS");
        }else{
            try {
                Farmaceutico farmaceutico = daoFarmaceutico.buscarPorMatricula(usuario.getText());
                Funcionario funcionario  =  daoFuncionario.buscarPorMatricula(usuario.getText());
                if (farmaceutico != null){
                    if (farmaceutico.getSenha().compareTo(senha.getText()) == 0){
                        TelaPrincipal.changeScreen("farmaceutico");
                    }else alerta.Error("erro", "erro");
                }else if (funcionario != null){
                    if (funcionario.getSenha().compareTo(senha.getText()) == 0){
                        TelaPrincipal.changeScreen("funcionario");
                    }else alerta.Error("erro", "erro");
                }else alerta.Error("erro", "erro");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void cadastrar(ActionEvent event) {
        TelaPrincipal.changeScreen("cadastro");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("imagens/pill.png");
        Image image = new Image(file.toURI().toString());
        logo.setImage(image);
        usuario.setPromptText("Matricula");
        senha.setPromptText("Senha");
    }


}
