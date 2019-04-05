package Controller;

import Main.TelaPrincipal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FuncionarioController implements Initializable {

    @FXML
    private ImageView logo;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    void CadastrarCliente(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("../View/cadastroCliente.fxml"));
        anchorPane.getChildren().setAll(a);
    }


    @FXML
    void ConsultarCliente(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("../View/consultaCliente.fxml"));
        anchorPane.getChildren().setAll(a);
    }

    @FXML
    void sair(ActionEvent event) {
        TelaPrincipal.changeScreen("login");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("imagens/pill.png");
        Image image = new Image(file.toURI().toString());
        logo.setImage(image);
    }
}
