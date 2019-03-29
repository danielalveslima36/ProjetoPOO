package Main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaPrincipal extends Application {

    public static void main(String[] args) {

        launch(args);
    }

        @Override
        public void start(Stage primaryStage) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("../View/telaPrincipal.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Sistema de Controle Farmaceutico");
            primaryStage.setResizable(false);
            Image image = new Image("File:imagens/pill.png");
            primaryStage.setOnCloseRequest(e -> Platform.exit());
            primaryStage.getIcons().add(image);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
}
