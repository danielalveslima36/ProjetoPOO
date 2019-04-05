package BancoDAO;

import Alerts.Alertas;
import Banco.ConFactory;
import Excecoes.DataNascimentoInvalidaException;
import InterfaceDao.ClienteDAO;
import Model.Cliente;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import Enum.Sexo;

/**
 * A classe <b>ClienteDaoanco</b> representa o CRUD do objeto.
 * @author Maaria Kelcilene
 * @author Daniel alves
 * @version 1.0
 * @since 04-04-19
 */

public class ClienteDaoBanco implements ClienteDAO {

    private ConFactory factory;
    Alertas alerta = new Alertas();

    public ClienteDaoBanco(){
        factory = new ConFactory();
    }

    /**
     * Metodo para listar clientes
     * ou seja, expor todos os clientes cadatrados
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
                String telefone = resultSet.getString("telefone");
                String nome = resultSet.getString("nome");
                Sexo sexo = Sexo.valueOf(resultSet.getString("sexo"));
                LocalDate nascimento = resultSet.getDate("nascimento").toLocalDate();
                String endereco = resultSet.getString("endereco");

                clientes.add(new Cliente(nome, cpf, telefone,endereco, sexo, nascimento));
            }
            return clientes;
        }
    }

    /**
     * Metodo salvar
     * Recebe um cliente que qeira se cadastrar.
     *os campos <i>nome,cf,nacimento</i>, entre outros devem ser informados pelo usuario.
     * @param cliente
     * @return um novo cliente
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @Override
    public boolean salvar(Cliente cliente) throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO cliente(nome, cpf, sexo, nascimento, endereco, telefone) VALUES(?, ?, ?, ?, ?, ?)"
            );

            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getCpf());
            statement.setString(3, String.valueOf(cliente.getSexo()));
            statement.setDate(4, Date.valueOf(cliente.getNacimento()));
            statement.setString(5, cliente.getEndereco());
            statement.setString(6, cliente.getTelefone());

            return statement.executeUpdate() > 0;
        } catch (DataNascimentoInvalidaException e) {
            alerta.Error("Erro", "Data Invalida");
            return false;
        }
    }

     /**
     * O metodo deletar recebe um clinte que deseja ser deletado
     * para isso, é necessario que o cliente informe seu cpf.
     * @param cliente
     * @return true caso a operação tenha sido realizada
     * @return false caso haja algum erro na busca
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
     * O metodo buscar
     * Tem como objetivo localizar um usuario atraves do seu cpf.
     * caso o cliente seja encontrado, ele recebe uma ficha cliente.
     * @param cpf
     * @return informações pessoais do cliente
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
                String telefone = resultSet.getString("telefone");
                Sexo sexo = Sexo.valueOf(resultSet.getString("sexo"));
                LocalDate nascimento = resultSet.getDate("nascimento").toLocalDate();
                String endereco = resultSet.getString("endereco");

                return new Cliente(nome, cpf1, telefone, endereco, sexo, nascimento);
            }else return null;
        }
    }

    /**
     * Metodo atualizar
     * Essa função recebe um cliente que deseja ser atualizada do sistema
     * onde ele pode altera suas funções pessoais.
     * @param cliente
     * @return true se caso consiga a atualizar
     * @return false caso haja um erro na atualização.
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
        } catch (DataNascimentoInvalidaException e) {
            alerta.Error("Erro", "Data Invalida");
            return false;
        }
    }
}
