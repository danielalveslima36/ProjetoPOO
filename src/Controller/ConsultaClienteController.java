package Controller;

import Alerts.Alertas;
import BancoDAO.ClienteDaoBanco;
import Excecoes.DataNascimentoInvalidaException;
import Model.Cliente;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ConsultaClienteController implements Initializable {

    Alertas alerta = new Alertas();
    ClienteDaoBanco daoCliente = new ClienteDaoBanco();

    @FXML
    private TableView<Cliente> tableView;

    @FXML
    private TableColumn<Cliente, String> tableClienteNome;

    @FXML
    private TableColumn<Cliente, String> tableClienteCpf;

    @FXML
    private Label cpf;

    @FXML
    private Label nome;

    @FXML
    private Label endereco;

    @FXML
    private Label telefone;

    @FXML
    private Label sexo;

    @FXML
    private Label nascimento;

    private List<Cliente> clientes;

    private ObservableList<Cliente> observableListClientes;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            carregarTableViewClientes();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    try {
                        selecionarItemTableViewFuncionarios(newValue);
                    } catch (DataNascimentoInvalidaException e) {
                        e.printStackTrace();
                    }
                });
    }

    private void carregarTableViewClientes() throws SQLException, ClassNotFoundException {
        tableClienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableClienteCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        clientes = new ArrayList<>(daoCliente.getClientes());

        observableListClientes = FXCollections.observableArrayList(clientes);
        tableView.setItems(observableListClientes);
    }

    private void selecionarItemTableViewFuncionarios(Cliente cliente) throws DataNascimentoInvalidaException {
        if (cliente != null) {
            cpf.setText(cliente.getCpf());
            endereco.setText(cliente.getEndereco());
            nome.setText(cliente.getNome());
            telefone.setText(cliente.getTelefone());
            sexo.setText(String.valueOf(cliente.getSexo()));
            nascimento.setText(String.valueOf(cliente.getNacimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        }else{
            cpf.setText("");
            endereco.setText("");
            nome.setText("");
            telefone.setText("");
            sexo.setText("");
            nascimento.setText("");
        }
    }

    @FXML
    void Alterar(ActionEvent event) throws SQLException, ClassNotFoundException, IOException, DataNascimentoInvalidaException {
        Cliente cliente = tableView.getSelectionModel().getSelectedItem();
        if (cliente != null){
            boolean buttonConfirmarClicked = showFXMLAnchorPaneConsultaFuncionarioDialog(cliente);
            if (buttonConfirmarClicked){
                daoCliente.atualizar(cliente);
                carregarTableViewClientes();
            }
        } else{
            alerta.Warning("Atenção", "Por favor, selecione um cliente da tabela");
        }
    }

    @FXML
    void Excluir(ActionEvent event) throws SQLException, ClassNotFoundException {
        Cliente cliente = tableView.getSelectionModel().getSelectedItem();
        if (cliente != null){
            daoCliente.deletar(cliente);
            carregarTableViewClientes();
        }else{
            alerta.Warning("Atenção", "Por favor, selecione uma cliente da tabela");
        }
    }

    private boolean showFXMLAnchorPaneConsultaFuncionarioDialog(Cliente cliente) throws IOException, DataNascimentoInvalidaException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ConsultaClienteDialog.class.getResource("../View/consultaClienteDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Clientes");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        ConsultaClienteDialog controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCliente(cliente);

        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
    }

}
