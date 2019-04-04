package BancoDAO;

import Banco.ConFactory;
import InterfaceDao.ClienteDAO;
import Model.Cliente;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import Enum.Sexo;

/**
 * A classe <b>ClienteDaoBanco</b> representa o CRUD do objeto cliente.
 * @autora Maria Kelcilene
 * @author Daniel Alves
 * @vension 1.0
 * @since 04-04-19
 */

public class ClienteDaoBanco implements ClienteDAO {

    private ConFactory factory;

    public ClienteDaoBanco(){
        factory = new ConFactory();
    }

    /**
     * Metodo para listar clientes
     * ou seja, expor todos os clientes cadastrados.
     * @return Array com todos os clientes
     * @throws SQLException
     * @throws ClassNotFoundException
     */

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
                Sexo sexo = Sexo.valueOf(resultSet.getString("sexo"));
                LocalDate nascimento = resultSet.getDate("nascimento").toLocalDate();
                String endereco = resultSet.getString("endereco");

                clientes.add(new Cliente(nome, cpf, endereco, sexo, nascimento));
            }
            return clientes;
        }
    }

    /**
     * Metodo salvar.
     * Recebe um cliente que queira se cadastrar.
     * Os campos <i>nome,cpf,sexo,nacimento,endereco</i> devem ser informados pelo usuario.
     * @param cliente
     * @return um  novo cliente
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @Override
    public boolean salvar(Cliente cliente) throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO cliente(nome, cpf, sexo, nascimento, endereco) VALUES(?, ?, ?, ?, ?)"
            );

            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getCpf());
            statement.setString(3, String.valueOf(cliente.getSexo()));
            statement.setDate(4, Date.valueOf(cliente.getNacimento()));
            statement.setString(5, cliente.getEndereco());

            return statement.executeUpdate() > 0;
        }
    }

    /**
     * O metodo deletar recebe um cliente que deseja ser deletado.
     * Para isso, é necessario que o cliente infome seu cpf.
     * @param cliente
     * @return true coso a operação tenha sido realizada
     * @return false caso haja algum erro na busca.
     * @throws SQLException
     * @throws ClassNotFoundException
     */

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

    /**
     * O metodo Buscar
     * Tem como objetivo localizar um usuario através do seu CPF.
     * caso ele encontre o usuario...ele recebe a ficha completa do cliente
     * <i>nome,endereço,sexo</i>, entre outros.
     * @param cpf
     * @return true caso o cliente seja encontrado
     * @return false caso o cliente nao exista.
     * @throws SQLException
     * @throws ClassNotFoundException
     */

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
                Sexo sexo = Sexo.valueOf(resultSet.getString("sexo"));
                LocalDate nascimento = resultSet.getDate("nascimento").toLocalDate();
                String endereco = resultSet.getString("endereco");

                return new Cliente(nome, cpf1, endereco, sexo, nascimento);
            }else return null;
        }
    }

    /**
     * Metodo atualizar.
     * Essa função recebe um cliente que deseja ser atualizado do sistema.
     * onde ele pode altera suas informações pessoais.
     * @param cliente
     * @return o cliente com suas alterações atualizadas.
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @Override
    public boolean atualizar(Cliente cliente) throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Cliente SET  nome = ?, sexo = ?, nascimento = ?, endereco = ? WHERE cpf = ?"
            );
            statement.setString(1, cliente.getNome());
            statement.setString(2, String.valueOf(cliente.getSexo()));
            statement.setDate(3, Date.valueOf(cliente.getNacimento()));
            statement.setString(4, cliente.getEndereco());
            statement.setString(5, cliente.getCpf());

            return statement.executeUpdate() > 0;
        }
    }
}
