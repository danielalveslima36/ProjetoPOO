package BancoDAO;

import Banco.ConFactory;
import InterfaceDao.ClienteDAO;
import Model.Cliente;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ClienteDaoBanco implements ClienteDAO {

    private ConFactory factory;

    public ClienteDaoBanco(){
        factory = new ConFactory();
    }

    @Override
    public Set<Cliente> getClientes() throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM cliente"
            );

            ResultSet resultSet = statement.executeQuery();
            Set<Cliente> clientes = new HashSet<>();

            while (resultSet.next()){
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String sexo = resultSet.getString("sexo");
                LocalDate nascimento = resultSet.getDate("nascimento").toLocalDate();
                String endereco = resultSet.getString("endereco");

                clientes.add(new Cliente(nome, cpf, endereco, sexo, nascimento));
            }
            return clientes;
        }
    }

    @Override
    public boolean salvar(Cliente cliente) throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO cliente(nome, cpf, sexo, nascimento, endereco) VALUES(?, ?, ?, ?, ?)"
            );

            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getCpf());
            statement.setString(3, cliente.getSexo());
            statement.setDate(4, Date.valueOf(cliente.getNacimento()));
            statement.setString(5, cliente.getEndereco());

            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deletar(Cliente cliente) throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM Cliente WHERE cpf = ?"
            );
            statement.setString(1, cliente.getCpf());

            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public Cliente buscarPorCpf(String cpf) throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Cliente WHERE cpf = ?"
            );

            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                String cpf1 = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String sexo = resultSet.getString("sexo");
                LocalDate nascimento = resultSet.getDate("nascimento").toLocalDate();
                String endereco = resultSet.getString("endereco");

                return new Cliente(nome, cpf1, endereco, sexo, nascimento);
            }else return null;
        }
    }

    @Override
    public boolean atualizar(Cliente cliente) throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Cliente SET  nome = ?, sexo = ?, nascimento = ?, endereco = ? WHERE cpf = ?"
            );
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getSexo());
            statement.setDate(3, Date.valueOf(cliente.getNacimento()));
            statement.setString(4, cliente.getEndereco());
            statement.setString(5, cliente.getCpf());

            return statement.executeUpdate() > 0;
        }
    }
}
