package Alerts;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ResourceBundle;

public class Alertas implements Initializable {

    public void Warning(String titulo, String cabecalho){
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecalho);
        alerta.show();
    }

    public void Error(String titulo, String cabecalho){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecalho);
        alerta.show();
    }

    public void Information(String titulo, String cabecalho){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecalho);
        alerta.show();
    }

    public void Confirmation(String titulo, String cabecalho){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecalho);
        alerta.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("File:imagens/pill.png");
    }
}
