package BancoDAO;

import Banco.ConFactory;
import InterfaceDao.FarmaciaDAO;
import Model.Farmacia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class FarmaciaDaoBanco implements FarmaciaDAO {

    private ConFactory factory;

    public FarmaciaDaoBanco(){
        factory = new ConFactory();
    }

    @Override
    public Set<Farmacia> getFarmacia() throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM farmacia"
            );
            ResultSet resultSet = statement.executeQuery();
            Set<Farmacia> farmacias = new HashSet<>();
            while (resultSet.next()){
                String cnpj = resultSet.getString("cnpj");
                String razaoSocial = resultSet.getString("razaoSocial");
                String endereco = resultSet.getString("endereco");
                String telefone = resultSet.getString("telefone");

                farmacias.add(new Farmacia(cnpj, razaoSocial, endereco, telefone));
            }
         return farmacias;
        }
    }

    @Override
    public boolean salvar(Farmacia farmacia) throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Farmacia (cnpj, razaoSocial, endereco, telefone) VALUES(?, ?, ?, ?);"
            );
            statement.setString(1, farmacia.getCnpj());
            statement.setString(2, farmacia.getRazaoSocial());
            statement.setString(3, farmacia.getEndereco());
            statement.setString(4, farmacia.getTelefone());

            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deletar(Farmacia farmacia) throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM farmacia WHERE cnpj = ?"
            );

            statement.setString(1, farmacia.getCnpj());

            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public Farmacia buscarPorCnpj(String cnpj) throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM farmacia WHERE cnpj = ? "
            );

            statement.setString(1, cnpj);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                String cnpj1 = resultSet.getString("cnpj");
                String razaoSocial = resultSet.getString("razaoSocial");
                String endereco = resultSet.getString("endereco");
                String telefone = resultSet.getString("telefone");

                return new Farmacia(cnpj1, razaoSocial, endereco, telefone);
            } else return null;
        }
    }

    @Override
    public boolean atualizar(Farmacia farmacia) throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE farmacia SET razaosocial = ?, endereco = ?, telefone = ? WHERE cnpj = ?"
            );

            statement.setString(1, farmacia.getRazaoSocial());
            statement.setString(2, farmacia.getEndereco());
            statement.setString(3, farmacia.getTelefone());
            statement.setString(4,  farmacia.getCnpj());

            return statement.executeUpdate() > 0;
        }
    }
}
