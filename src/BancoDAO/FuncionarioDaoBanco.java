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

public class FuncionarioDaoBanco implements FuncionarioDAO {
    private ConFactory factory;

    public FuncionarioDaoBanco(){
        factory = new ConFactory();
    }

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
