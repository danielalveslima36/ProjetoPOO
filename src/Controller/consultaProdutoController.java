package Controller;

import Alerts.Alertas;
import BancoDAO.ProdutoDaoBanco;
import Model.Produto;
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
import Enum.Sessao;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class consultaProdutoController implements Initializable {

    Alertas alerta = new Alertas();
    ProdutoDaoBanco daoProduto = new ProdutoDaoBanco();
    @FXML
    private TableView<Produto> tableView;

    @FXML
    private TableColumn<Produto, String> tableProdutoNome;

    @FXML
    private TableColumn<Produto, String> tableProdutoCodigo;

    @FXML
    private Label codigo;

    @FXML
    private Label nome;

    @FXML
    private Label validade;

    @FXML
    private Label fabricante;

    @FXML
    private Label sessao;

    @FXML
    private Label descricao;

    @FXML
    private Label Preco;

    private List<Produto> produtos;

    private ObservableList<Produto> observableListProdutos;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            carregarTableViewProdutos();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewProdutos(newValue));
    }

    private void selecionarItemTableViewProdutos(Produto produto) {
        if (produto != null) {
            codigo.setText(produto.getCodigoDeBarras());
            nome.setText(produto.getNome());
            validade.setText(String.valueOf(produto.getValidade().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            fabricante.setText(produto.getFabricante());
            Preco.setText(String.valueOf(produto.getPrecoUnitario()));
            sessao.setText(String.valueOf(Sessao.valueOf(String.valueOf(produto.getSessao()))));
            descricao.setText(produto.getDecricao());
        }else{
            codigo.setText("");
            nome.setText("");
            validade.setText("");
            fabricante.setText("");
            Preco.setText("");
            sessao.setText("");
            descricao.setText("");
        }
    }

    private void carregarTableViewProdutos() throws SQLException, ClassNotFoundException {
        tableProdutoCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoDeBarras"));
        tableProdutoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        produtos = new ArrayList<>(daoProduto.getProduto());

        observableListProdutos = FXCollections.observableArrayList(produtos);
        tableView.setItems(observableListProdutos);
    }

    @FXML
    void Alterar(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        Produto produto = tableView.getSelectionModel().getSelectedItem();
        if (produto != null){
            boolean buttonConfirmarClicked = showFXMLAnchorPaneConsultaFarmaciaDialog(produto);
            if (buttonConfirmarClicked){
                daoProduto.atualizar(produto);
                carregarTableViewProdutos();
            }
        } else{
            alerta.Warning("Atenção", "Por favor, selecione uma produto da tabela");
        }
    }

    @FXML
    void Excluir(ActionEvent event) throws SQLException, ClassNotFoundException {
        Produto produto = tableView.getSelectionModel().getSelectedItem();
        if (produto != null){
            daoProduto.deletar(produto);
            carregarTableViewProdutos();
        }else{
            alerta.Warning("Atenção", "Por favor, selecione uma farmacia da tabela");
        }
    }

    private boolean showFXMLAnchorPaneConsultaFarmaciaDialog(Produto produto) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ConsultaProdutoDialog.class.getResource("../View/consultaProdutoDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Produtos");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        ConsultaProdutoDialog controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setProduto(produto);

        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
    }
}
