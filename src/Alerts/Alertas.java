package Alerts;

import javafx.scene.control.Alert;

public class Alertas {

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
}
