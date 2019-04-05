package BancoDAO;

import Banco.ConFactory;
import InterfaceDao.ProdutoDAO;
import Model.Produto;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import Enum.Sessao;

/**
 * A classe <b>ProdutoDaoBanco</b> tem como objetivo representar o CRUD do produto.
 * @autora Maria Kelcilene
 * @author Daniel Alves
 * @version 1.0
 * @since 04-04-19
 */

public class ProdutoDaoBanco implements ProdutoDAO {

    private ConFactory factory;

    public ProdutoDaoBanco(){
        factory = new ConFactory();
    }

    /**
     * Metodo para listar os produtos.
     * ou seja, expor todos os prdutos cadastrados.
     * @return Array com todos os produtos
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @Override
    public Set<Produto> getProduto() throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM produto"
            );


            ResultSet resultSet = statement.executeQuery();
            Set<Produto> produtos = new HashSet<>();

            while (resultSet.next()){
                String codProduto = resultSet.getString("codProduto");
                String descricao = resultSet.getString("descricao");
                String nome = resultSet.getString("nome");
                String fabricante = resultSet.getString("fabricante");
                Sessao sessao = Sessao.valueOf(resultSet.getString("sessao"));
                Float precoUnitario = resultSet.getFloat("precoUnitario");
                LocalDate validade = resultSet.getDate("validade").toLocalDate();
                produtos.add(new Produto(descricao, nome, validade, codProduto, sessao, precoUnitario, fabricante));
            }
            return produtos;
        }
    }

    /**
     *  Recebe um produto que queira se cadastrar.
     * Os campos <i>codigoDeBarras,fabricante,nome,descrição</i> devem ser informados pelo usuario.
     * @param produto
     * @return um novo produto
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @Override
    public boolean salvar(Produto produto) throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Produto(codProduto, descricao, fabricante, precoUnitario, validade, sessao, nome) " +
                            "VALUES(?, ?, ?, ?, ?, ?, ?)"
            );

            statement.setString(1, produto.getCodigoDeBarras());
            statement.setString(2, produto.getDecricao());
            statement.setString(3, produto.getFabricante());
            statement.setFloat(4, produto.getPrecoUnitario());
            statement.setDate(5, Date.valueOf(produto.getValidade()));
            statement.setString(6, String.valueOf(produto.getSessao()));
            statement.setString(7, String.valueOf(produto.getNome()));

            return statement.executeUpdate() > 0;
        }
    }

    /**
     * O metodo deletar recebe um produto que deseja ser deletado.
     * Para isso, é necessario que o funcionario infome o codigo de barras do produto.
     * @param produto
     * @return true caso a operação tenha dado certo
     * @return false caso não seja possivel deletar o produto.
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @Override
    public boolean deletar(Produto produto) throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM produto WHERE codProduto = ?"
            );

            statement.setString(1, produto.getCodigoDeBarras());
            return statement.executeUpdate() > 0;
        }
    }


    /**
     * O metodo Buscar
     * Tem como objetivo localizar um produto através do codigo de barras.
     * caso ele encontre, recebe a ficha completa do produto.
     * <i>nome,descrição,fabricante</i>, entre outros.
     * @param codigoBarras
     * @return informações do produto.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public Produto buscarPorCodigo(String codigoBarras) throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM produto WHERE codProduto = ?"
            );

            statement.setString(1, codigoBarras);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                String codProduto = resultSet.getString("codProduto");
                String descricao = resultSet.getString("descricao");
                String nome = resultSet.getString("nome");
                String fabricante = resultSet.getString("fabricante");
                Sessao sessao = Sessao.valueOf(resultSet.getString("sessao"));
                Float precoUnitario = resultSet.getFloat("precoUnitario");
                LocalDate validade = resultSet.getDate("validade").toLocalDate();
                return new Produto(descricao, nome, validade, codProduto, sessao, precoUnitario, fabricante);
            } else return null;
        }
    }

    /**
     * Metodo atualizar.
     * Essa função recebe um produto que deseja ser atualizado do sistema.
     * onde, ele pode alterar suas informações pessoais.
     * @param produto
     * @return true se o objeto foi modificado
     * @return false se for impossivel atualizar o produto
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @Override
    public boolean atualizar(Produto produto) throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE produto " +
                            "SET descricao = ?, fabricante = ?, sessao = ?, precoUnitario = ?, validade = ?, nome = ?" +
                            "WHERE codproduto = ?"
            );

            statement.setString(1, produto.getDecricao());
            statement.setString(2, produto.getFabricante());
            statement.setString(3, String.valueOf(produto.getSessao()));
            statement.setFloat(4, produto.getPrecoUnitario());
            statement.setDate(5, Date.valueOf(produto.getValidade()));
            statement.setString(6, produto.getNome());
            statement.setString(7, produto.getCodigoDeBarras());

            return statement.executeUpdate() > 0;
        }
    }
}
