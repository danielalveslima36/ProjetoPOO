package Controller;

import Alerts.Alertas;
import BancoDAO.FarmaciaDaoBanco;
import Model.Farmacia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ConsultaFarmaciaController implements Initializable {

    FarmaciaDaoBanco daoFarmacia = new FarmaciaDaoBanco();
    Alertas alerta = new Alertas();
    @FXML
    private TableView<Farmacia> tableView;

    @FXML
    private TableColumn<Farmacia, String> tableFarmaciaNome;

    @FXML
    private TableColumn<Farmacia, String> tableFarmaciaCnpj;

    @FXML
    private Label cnpj;

    @FXML
    private Label nome;

    @FXML
    private Label endereco;

    @FXML
    private Label telefone;

    private List<Farmacia> farmacias;

    private ObservableList<Farmacia> observableListFarmacias;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            carregarTableViewFarmacias();
        } catch (SQLException e) {


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewFarmacia(newValue));
    }

    public void carregarTableViewFarmacias() throws SQLException, ClassNotFoundException {
        tableFarmaciaCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        tableFarmaciaNome.setCellValueFactory(new PropertyValueFactory<>("razaoSocial"));

        farmacias = new ArrayList<>(daoFarmacia.getFarmacia());

        observableListFarmacias = FXCollections.observableArrayList(farmacias);
        tableView.setItems(observableListFarmacias);
    }

    public void selecionarItemTableViewFarmacia(Farmacia farmacia) {
        if (farmacia != null) {
            cnpj.setText(farmacia.getCnpj());
            nome.setText(farmacia.getRazaoSocial());
            endereco.setText(farmacia.getEndereco());
            telefone.setText(farmacia.getTelefone());
        }else{
            cnpj.setText("");
            nome.setText("");
            endereco.setText("");
            telefone.setText("");
        }
    }

    @FXML
    public void Alterar() throws SQLException, ClassNotFoundException, IOException {
        Farmacia farmacia = tableView.getSelectionModel().getSelectedItem();
        if (farmacia != null){
            boolean buttonConfirmarClicked = showFXMLAnchorPaneConsultaFarmaciaDialog(farmacia);
            if (buttonConfirmarClicked){
                daoFarmacia.atualizar(farmacia);
                carregarTableViewFarmacias();
            }
        } else{
            alerta.Warning("Atenção", "Por favor, selecione uma farmacia da tabela");
        }
    }


    @FXML
    void Excluir(ActionEvent event) throws SQLException, ClassNotFoundException {
        Farmacia farmacia = tableView.getSelectionModel().getSelectedItem();
        if (farmacia != null){
            daoFarmacia.deletar(farmacia);
            carregarTableViewFarmacias();
        }else{
            alerta.Warning("Atenção", "Por favor, selecione uma farmacia da tabela");
        }
    }

    public boolean showFXMLAnchorPaneConsultaFarmaciaDialog(Farmacia farmacia) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ConsultaFarmaciaDialog.class.getResource("../View/consultaFarmaciaDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Farmácias");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        ConsultaFarmaciaDialog controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setFarmacia(farmacia);

        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
    }

}
