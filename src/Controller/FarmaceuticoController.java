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

public class FarmaceuticoController implements Initializable{

    @FXML
    private ImageView logo;

    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("imagens/pill.png");
        Image image = new Image(file.toURI().toString());
        logo.setImage(image);
    }

    @FXML
    void cadastrarFarmacia(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("../View/cadastroFarmacia.fxml"));
        anchorPane.getChildren().setAll(a);

    }

    @FXML
    void cadastrarFuncionario(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("../View/cadastroFuncionario.fxml"));
        anchorPane.getChildren().setAll(a);
    }

    @FXML
    void cadastrarProduto(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("../View/cadastroProduto.fxml"));
        anchorPane.getChildren().setAll(a);
    }

    @FXML
    void consultarProduto(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("../View/consultaProduto.fxml"));
        anchorPane.getChildren().setAll(a);
    }


    @FXML
    void consultarFarmacia(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("../View/consultaFarmacia.fxml"));
        anchorPane.getChildren().setAll(a);
    }

    @FXML
    void consultarFuncionarios(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("../View/consultaFuncionario.fxml"));
        anchorPane.getChildren().setAll(a);
    }

    @FXML
    void sair(ActionEvent event) {
        TelaPrincipal.changeScreen("login");
    }


}
