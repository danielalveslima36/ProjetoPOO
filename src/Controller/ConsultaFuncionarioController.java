package Controller;

import Alerts.Alertas;
import BancoDAO.FuncionarioDaoBanco;
import Model.Funcionario;
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

public class ConsultaFuncionarioController implements Initializable {
    FuncionarioDaoBanco daoFuncionario = new FuncionarioDaoBanco();
    Alertas alerta = new Alertas();
    @FXML
    private TableView<Funcionario> tableView;

    @FXML
    private TableColumn<Funcionario, String> tableFuncionarioNome;

    @FXML
    private TableColumn<Funcionario, String> tableFuncionarioMatricula;

    @FXML
    private Label cpf;

    @FXML
    private Label matricula;

    @FXML
    private Label nome;

    @FXML
    private Label senha;

    @FXML
    private Label salario;

    @FXML
    private Label sessao;

    @FXML
    private Label telefone;

    @FXML
    private Label endereco;

    private List<Funcionario> funcionarios;

    private ObservableList<Funcionario> observableListFarmacias;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            carregarTableViewFuncionarios();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewFuncionarios(newValue));
    }


    public void carregarTableViewFuncionarios() throws SQLException, ClassNotFoundException {
        tableFuncionarioNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableFuncionarioMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));

        funcionarios = new ArrayList<>(daoFuncionario.getFuncionarios());

        observableListFarmacias = FXCollections.observableArrayList(funcionarios);
        tableView.setItems(observableListFarmacias);
    }

    private void selecionarItemTableViewFuncionarios(Funcionario funcionario) {
        if (funcionario != null) {
            cpf.setText(funcionario.getCpf());
            matricula.setText(funcionario.getMatricula());
            nome.setText(funcionario.getNome());
            telefone.setText(funcionario.getTelefone());
            senha.setText(funcionario.getSenha());
            endereco.setText(funcionario.getEndereco());
            salario.setText(String.valueOf(funcionario.getSalario()));
            sessao.setText(String.valueOf(funcionario.getSessao()));
        }else{
            cpf.setText("");
            matricula.setText("");
            nome.setText("");
            telefone.setText("");
            senha.setText("");
            endereco.setText("");
            salario.setText("");
            sessao.setText("");
        }
    }

    @FXML
    void Alterar(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        Funcionario funcionario = tableView.getSelectionModel().getSelectedItem();
        if (funcionario != null){
            boolean buttonConfirmarClicked = showFXMLAnchorPaneConsultaFuncionarioDialog(funcionario);
            if (buttonConfirmarClicked){
                System.out.println(funcionario);
                daoFuncionario.atualizar(funcionario);
                carregarTableViewFuncionarios();
            }
        } else{
            alerta.Warning("Atenção", "Por favor, selecione um funcionario da tabela");
        }

    }

    @FXML
    void Excluir(ActionEvent event) throws SQLException, ClassNotFoundException {
        Funcionario  funcionario = tableView.getSelectionModel().getSelectedItem();
        if (funcionario != null){
            daoFuncionario.deletar(funcionario);
            carregarTableViewFuncionarios();
        }else{
            alerta.Warning("Atenção", "Por favor, selecione uma farmacia da tabela");
        }

    }

    public boolean showFXMLAnchorPaneConsultaFuncionarioDialog(Funcionario funcionario) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ConsultaFarmaciaDialog.class.getResource("../View/consultaFuncionarioDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Funcionários");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        ConsultaFuncionarioDialog controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setFuncionario(funcionario);

        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
    }
}
