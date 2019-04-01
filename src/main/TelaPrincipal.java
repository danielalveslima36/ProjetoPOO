package Main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;


public class TelaPrincipal extends Application {

    private static Stage stage;

    private static Scene loginScene;
    private static Scene cadastroScene;

        @Override
        public void start(Stage primaryStage) throws IOException {
            stage = primaryStage;
            Parent login = FXMLLoader.load(getClass().getResource("../View/telaPrincipal.fxml"));
            loginScene = new Scene(login);

            Parent cadastro = FXMLLoader.load(getClass().getResource("../View/telaCadastroFarmaceutico.fxml"));
            cadastroScene = new Scene(cadastro);

            primaryStage.setTitle("Sistema de Controle Farmaceutico");
            primaryStage.setResizable(false);
            Image image = new Image("File:imagens/pill.png");
            primaryStage.setOnCloseRequest(e -> Platform.exit());
            primaryStage.getIcons().add(image);
            primaryStage.setScene(loginScene);
            primaryStage.show();
        }

        public static void changeScreen(String src){
            switch (src){
                case "login":
                    stage.setScene(loginScene);
                    break;
                case "cadastro":
                    stage.setScene(cadastroScene);
            }
        }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        launch(args);
    }
}
