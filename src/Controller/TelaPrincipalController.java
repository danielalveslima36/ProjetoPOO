package Controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import javax.xml.bind.SchemaOutputResolver;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaPrincipalController implements Initializable{

    @FXML
    private TextField usuario;

    @FXML
    private PasswordField senha;

    @FXML
    private ImageView logo;

    @FXML
    private Button btnLogar;

    @FXML
    private void logar(ActionEvent event){
        if ((usuario.getText().isEmpty() || senha.getText().isEmpty())){
            alert("ATENÇÃO", "ERRO AO LOGAR", "PREENCHA TODOS OS CAMPOS");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("imagens/pill.png");
        Image image = new Image(file.toURI().toString());
        logo.setImage(image);

        usuario.setPromptText("Matricula");
        senha.setPromptText("Senha");
    }

    public void alert(String titulo, String cabecalho, String conteudo){
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecalho);
        alerta.setContentText(conteudo);
        alerta.show();
    }
}
