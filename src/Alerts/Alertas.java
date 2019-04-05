package Alerts;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * A classe <b>Alertas</b> tem como função aletar .
 * caso o usuario faça algo que prejudique o funcionamento, ele recebe uma mensagem na sua tela.
 * @autora Maria Kelcilene
 * @author Daniel Alves
 * @vension 1.0
 * @since 04-04-19
 */

public class Alertas implements Initializable {

    public void Warning(String titulo, String cabecalho){
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecalho);
        alerta.show();
    }

    /**
     * Função onde o usuario recebe uma mensagem de erro.
     * @param titulo
     * @param cabecalho
     */

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
