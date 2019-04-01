package BancoDAO;

import Banco.ConFactory;
import InterfaceDao.VendaDAO;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import Enum.*;
import Model.Venda;


public class VendaDaoBanco implements VendaDAO {

    private ConFactory factory;

    public VendaDaoBanco(){
        factory = new ConFactory();
    }

    @Override
    public Set<Venda> getVendas() throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM venda"
            );

            ResultSet resultSet = statement.executeQuery();
            Set<Venda> vendas = new HashSet<>();

            while (resultSet.next()){
                int codVenda = resultSet.getInt("codVenda");
                LocalDate data = resultSet.getDate("data").toLocalDate();
                LocalTime hora = resultSet.getTime("hora").toLocalTime();
                Float total = resultSet.getFloat("total");
                TipoVenda tipo = TipoVenda.valueOf(resultSet.getString("tipo"));
                String funcionario = resultSet.getString("matfuncionario");
                String cliente = resultSet.getString("cpfCliente");
                vendas.add(new Venda(codVenda, data, hora, total, tipo, funcionario, cliente));
            }
            return vendas;
        }
    }

    @Override
    public boolean salvar(Venda venda) throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO venda(codVenda, data, hora, total, tipo, matFuncionario, cpfCliente)" +
                            "VALUES(?, ?, ?, ?, ?, ?, ?)"
            );
            statement.setInt(1, venda.getCodVenda());
            statement.setDate(2, Date.valueOf(venda.getData()));
            statement.setTime(3, Time.valueOf(venda.getHora()));
            statement.setFloat(4, venda.getTotal());
            statement.setString(5, String.valueOf(venda.getTipo()));
            statement.setString(6, venda.getFuncionario());
            statement.setString(7, venda.getCliente());

           return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deletar(Venda venda) throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM venda WHERE codVenda = ?"
            );
            statement.setInt(1, venda.getCodVenda());

            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public Venda buscarPorCodigo(int codVenda) throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM venda WHERE codvenda = ?"
            );
            statement.setInt(1, codVenda);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                LocalDate data = resultSet.getDate("data").toLocalDate();
                LocalTime hora = resultSet.getTime("hora").toLocalTime();
                Float total = resultSet.getFloat("total");
                TipoVenda tipo = TipoVenda.valueOf(resultSet.getString("tipo"));
                String funcionario = resultSet.getString("matfuncionario");
                String cliente = resultSet.getString("cpfCliente");
                return new Venda(codVenda, data, hora, total, tipo, funcionario, cliente);
            } else return null;
        }

    }

    @Override
    public boolean atualizar(Venda venda) throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE venda " +
                            "SET data = ?, hora = ?, total = ?, tipo = ?, matfuncionario = ?, cpfcliente = ?" +
                            "WHERE codvenda = ?"
            );

            statement.setDate(1, Date.valueOf(venda.getData()));
            statement.setTime(2, Time.valueOf(venda.getHora()));
            statement.setFloat(3, venda.getTotal());
            statement.setString(4, String.valueOf(venda.getTipo()));
            statement.setString(5, venda.getFuncionario());
            statement.setString(6, venda.getCliente());
            statement.setInt(7, venda.getCodVenda());

            return statement.executeUpdate() > 0;
        }
    }
}
