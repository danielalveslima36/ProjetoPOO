package BancoDAO;

import Banco.ConFactory;
import InterfaceDao.FuncionarioDAO;
import Model.Funcionario;
import Enum.Sessao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * A classe <b>FuncionarioDaoBanco</b> representa o CRUD do objeto funcionario.
 * @author Maria Kelcilene
 * @author Daniel Alves
 * @version 1.0
 * @since 04-04-19
 */

public class FuncionarioDaoBanco implements FuncionarioDAO {
    private ConFactory factory;

    public FuncionarioDaoBanco(){
        factory = new ConFactory();
    }

    /**
     * Metodo para listar os funcionarios.
     * ou seja, expor todos os funcionarios cadastrados.
     * @return Set de funcionarios.
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @Override
    public Set<Funcionario> getFuncionarios() throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement =  connection.prepareStatement(
                    "select *\n" +
                            "from funcionario f\n" +
                            "where f.matricula not in \n" +
                            "\t\t(select matFuncionario\n" +
                            "\t\tfrom farmaceutico)"
            );
            ResultSet resultSet = statement.executeQuery();
            Set<Funcionario> funcionarios = new HashSet<>();
            while (resultSet.next()){
                String cpf = resultSet.getString("cpf");
                String matricula = resultSet.getString("matricula");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                Float salario = resultSet.getFloat("salario");
                Sessao sessao = Sessao.valueOf(resultSet.getString("sessao"));
                String telefone = resultSet.getString("telefone");
                String endereco = resultSet.getString("endereco");
                funcionarios.add(new Funcionario(cpf, matricula, nome, senha, salario, sessao, telefone, endereco));
            }
            return funcionarios;
        }
    }


    /**
     * Recebe um funcionario que queira se cadastrar.
     *Os campos <i>Matricula,cpf,senha,salario,endereco,sessao,telefone,nome</i> devem ser informados pelo usuario.
     * @param funcionario
     * @return um novo funcionario
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @Override
    public boolean salvar(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        try (Connection connection = factory.getConnection()){
            PreparedStatement statement= connection.prepareStatement(
                    "INSERT INTO funcionario(matricula, cpf, senha, salario, endereco, sessao, telefone, nome) VALUES(?, ?, ?, ?, ?, ?, ?, ?);"
            );

            statement.setString(1, funcionario.getMatricula());
            statement.setString(2, funcionario.getCpf());
            statement.setString(3, funcionario.getSenha());
            statement.setFloat(4, funcionario.getSalario());
            statement.setString(5, funcionario.getEndereco());
            statement.setString(6, String.valueOf(funcionario.getSessao()));
            statement.setString(7, funcionario.getTelefone());
            statement.setString(8, funcionario.getNome());

            return statement.executeUpdate() > 0;
        }
    }

    /**
     * O metodo deletar recebe um funcionario que deseja ser deletado.
     * Para isso, é necessario que o funcionario infome sua matricula.
     * @param funcionario
     * @return true se for possivel deletar
     * @return false caso não seja possivel deletar o funcionario
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @Override
    public boolean deletar(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM Funcionario WHERE matricula = ?"
            );

            statement.setString(1, funcionario.getMatricula());

            return statement.executeUpdate() > 0;
        }
    }

    /**
     * O metodo Buscar
     * Tem como objetivo localizar um funcionario através da sua matricula.
     * caso ele encontre, recebe a ficha completa do funcionario.
     * <i>nome,endereço,senha,telefone</i>, entre outros.
     * @param matricula
     * @return informações pessoais do funcionario.
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @Override
    public Funcionario buscarPorMatricula(String matricula) throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM funcionario WHERE matricula = ?"
            );

            statement.setString(1, matricula);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                String cpf = resultSet.getString("cpf");
                String matricula1 = resultSet.getString("matricula");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                Float salario = resultSet.getFloat("salario");
                Sessao sessao = Sessao.valueOf(resultSet.getString("sessao"));
                String telefone = resultSet.getString("telefone");
                String endereco = resultSet.getString("endereco");
                return new Funcionario(cpf, matricula, nome, senha, salario, sessao, telefone, endereco);
            }else return null;
        }
    }

    /**
     * Metodo atualizar.
     *  Essa função recebe um funcionario que deseja ser atualizado do sistema.
     * onde, ele pode alterar suas informações pessoais.
     * @param funcionario
     * @return true caso a operação seja concluida com sucesso
     * @return false caso não seja possivel atualizar o funcionario
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @Override
    public boolean atualizar(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE funcionario " +
                            "SET cpf = ?, senha = ?, salario = ?, sessao = ?, telefone = ?, endereco = ?, nome = ?" +
                            "WHERE matricula = ?"
            );

            statement.setString(1, funcionario.getCpf());
            statement.setString(2, funcionario.getSenha());
            statement.setFloat(3, funcionario.getSalario());
            statement.setString(4, String.valueOf(funcionario.getSessao()));
            statement.setString(5, funcionario.getTelefone());
            statement.setString(6, funcionario.getEndereco());
            statement.setString(7, funcionario.getNome());
            statement.setString(8, funcionario.getMatricula());

            return statement.executeUpdate() > 0;
        }
    }
}
