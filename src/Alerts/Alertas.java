package Alerts;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * A classe <b>Alertas</b> tem como função aletar .
 * caso o usuario faça algo que prejudique o funcionamento, ele recebe uma mensagem na sua tela.
 * @author Maria Kelcilene
 * @author Daniel Alves
 * @version 1.0
 * @since 04-04-19
 */

public class Alertas implements Initializable {

    /**
     * A funçao warning, mostra para o usuario uma mensagem de atenção
     * @param titulo
     * @param cabecalho
     */

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

    /**
     * O metodo information mostra uma mensagem de informação para o usuario
     * @param titulo
     * @param cabecalho
     */

    public void Information(String titulo, String cabecalho){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecalho);
        alerta.show();
    }

    /**
     * O metodo confirmation mostra para o usuario uma mensagem de confirmação
     * @param titulo
     * @param cabecalho
     */

    public void Confirmation(String titulo, String cabecalho){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecalho);
        alerta.show();
    }

    /**
     * O metodo initializa tem como ojetio carregar a logo da aplicação
     * @param location
     * @param resources
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("File:imagens/pill.png");
    }
}
