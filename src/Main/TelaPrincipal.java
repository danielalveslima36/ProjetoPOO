package Main;

import BancoDAO.FarmaceuticoDaoBanco;
import Model.Farmaceutico;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import Enum.Sessao;

/**
 * A classe <b>TelaPrincipal</b>
 * tem como principal caracteristica o gerenciamento de toda a aplicação desenvolvida
 * Nela podemos gerenciar todas as telas da aplicação e modificarmos,caso necessario
 * @autora Maria Kelcilene
 * @author Daniel Alves
 * @version 1.0
 * @since 04-04-19
 */

public class TelaPrincipal extends Application  {

    private static Stage stage;

    private static Scene loginScene;
    private static Scene cadastroScene;
    private static Scene farmaceuticoScene;

        @Override
        public void start(Stage primaryStage) throws IOException {
            stage = primaryStage;
            Parent login = FXMLLoader.load(getClass().getResource("../View/telaPrincipal.fxml"));
            loginScene = new Scene(login);

            stage = primaryStage;
            Parent farmaceutico = FXMLLoader.load(getClass().getResource("../View/Farmaceutico.fxml"));
            farmaceuticoScene = new Scene(farmaceutico);

            Parent cadastro = FXMLLoader.load(getClass().getResource("../View/cadastroFarmaceutico.fxml"));
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
                    break;
                case "farmaceutico":
                    stage.setScene(farmaceuticoScene);
            }
        }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
                launch(args);
    }
}