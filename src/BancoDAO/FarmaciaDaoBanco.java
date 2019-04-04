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

/**
 * A classe <b>FarmaciaDaoBanco</b> representa o CRUD do objeto farmacia.
 * @autora Maria Kelcilene
 * @author Daniel Alves
 * @version 1.0
 * @since 04-04-19
 */

public class FarmaciaDaoBanco implements FarmaciaDAO {

    private ConFactory factory;

    public FarmaciaDaoBanco(){
        factory = new ConFactory();
    }

    /**
     * Metodo para listar farmacia.
     * ou seja, expor todos as farmacias cadastrados.
     * @return Array de farmacias cadastradas.
     * @throws SQLException
     * @throws ClassNotFoundException
     */

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

    /**
     * Recebe um farmacia que queira se cadastrar.
     * Os campos <i>cnpj,razaoSocail,endereco,telefone</i> devem ser informados pelo usuario.
     * @param farmacia
     * @return uma nova farmacia
     * @throws SQLException
     * @throws ClassNotFoundException
     */

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

    /**
     * O metodo deletar recebe uma farmacia que deseja ser deletado.
     * Para isso, é necessario que o farmacia infome seu cnpj.
     * @param farmacia
     * @return true se a operaçao for concluida com sucesso.
     * @return false caso haja algum erro.
     * @throws SQLException
     * @throws ClassNotFoundException
     */

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

    /**
     * O metodo Buscar
     * Tem como objetivo localizar uma farmacia através do seu cnpj.
     * caso ele encontre, recebe a ficha completa da farmacia
     * <i>nome,endereço,cnpj,razaoSocail</i>, entre outros.
     * @param cnpj
     * @return true caso a busca seja concluida com sucesso
     * @return false caso a busca tenha algum erro
     * @throws SQLException
     * @throws ClassNotFoundException
     */

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

    /**
     * Metodo atualizar.
     * Essa função recebe uma farmacia que deseja ser atualizado do sistema.
     * onde, ele pode alterar suas informações pessoais.
     * @param farmacia
     * @return true caso a informação seja concluida com sucesso.
     * @return false caso não consiga localizar a farmacia.
     * @throws SQLException
     * @throws ClassNotFoundException
     */

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
