package BancoDAO;

import Banco.ConFactory;
import InterfaceDao.FarmaceuticoDAO;
import Model.Farmaceutico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import Enum.Sessao;

/**
 * A classe <b>FarmaceuticoDaoBanco</b> representa o CRUD do objeto farmaceutico.
 * @autora Maria Kelcilene
 * @author Daneil Alves
 * @vension 1.0
 * @since 04-04-19
 */

public class FarmaceuticoDaoBanco implements FarmaceuticoDAO {
    private ConFactory factory;

    public FarmaceuticoDaoBanco(){
        factory = new ConFactory();
    }

    /**
     * Metodo para listar farmaceutico
     * ou seja, expor todos os farmaceuticos cadastrados.
     * @return Array com todos os farmaceuticos
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @Override
    public Set<Farmaceutico> getFarmaceutico() throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM farmaceutico fa, funcionario fu"
            );
            ResultSet resultSet = statement.executeQuery();
            Set<Farmaceutico> farmaceuticos = new HashSet<>();

            while(resultSet.next()){
                String cpf = resultSet.getString("cpf");
                String matricula = resultSet.getString("matFuncionario");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                Float salario = resultSet.getFloat("salario");
                Sessao sessao = Sessao.valueOf(resultSet.getString("sessao"));
                String telefone = resultSet.getString("telefone");
                String endereco = resultSet.getString("endereco");
                String numeroCRF = resultSet.getString("numeroCRF");

                farmaceuticos.add(new Farmaceutico(cpf, matricula, nome, senha, salario, sessao, telefone, endereco, numeroCRF));
            }
            return farmaceuticos;

        }
    }

    /**
     * Metodo salvar.
     * Recebe um farmaceutico que queira se cadastrar.
     * Os campos <i>Matricula,cpf,senha,salario,endereco,sessao,telefone,nome</i> devem ser informados pelo usuario.
     * @param farmaceutico
     * @return um novo farmaceutico
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @Override
    public boolean salvar(Farmaceutico farmaceutico) throws SQLException, ClassNotFoundException {

        try(Connection connection = factory.getConnection()){
            PreparedStatement statement1 = connection.prepareStatement(
                    "INSERT INTO funcionario(matricula, cpf, senha, salario, endereco, sessao, telefone, nome) VALUES(?, ?, ?, ?, ?, ?, ?, ?);"
            );

            PreparedStatement statement2 = connection.prepareStatement(
                    "INSERT INTO farmaceutico(matfuncionario, numeroCrf) VALUES(?, ?)"
            );

            statement1.setString(1, farmaceutico.getMatricula());
            statement1.setString(2, farmaceutico.getCpf());
            statement1.setString(3, farmaceutico.getSenha());
            statement1.setFloat(4, farmaceutico.getSalario());
            statement1.setString(5, farmaceutico.getEndereco());
            statement1.setString(6, String.valueOf(farmaceutico.getSessao()));
            statement1.setString(7, farmaceutico.getTelefone());
            statement1.setString(8, farmaceutico.getNome());

            statement2.setString(1, farmaceutico.getMatricula());
            statement2.setString(2, farmaceutico.getNumeroCRF());

            return statement1.executeUpdate() > 0 && statement2.executeUpdate() > 0;
        }
    }

    /**
     * O metodo deletar recebe um farmaceutico que deseja ser deletado.
     * Para isso, é necessario que o farmaceutico infome seu cpf.
     * @param farmaceutico
     * @return true caso o farmaceutico seja removido com sucesso
     * @return false caso ocorra algum erro na busca do ususario para deletação.
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @Override
    public boolean deletar(Farmaceutico farmaceutico) throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement1 = connection.prepareStatement(
                    "DELETE FROM Farmaceutico WHERE matfuncionario = ?"
            );

            PreparedStatement statement2 = connection.prepareStatement(
                    "DELETE FROM Funcionario WHERE matricula = ?"
            );

            statement1.setString(1, farmaceutico.getMatricula());
            statement2.setString(1, farmaceutico.getMatricula());

            return statement1.executeUpdate() > 0 && statement2.executeUpdate() > 0;
        }
    }

    /**
     * O metodo Buscar
     * Tem como objetivo localizar um farmaceutico através da sua matricula.
     * caso ele encontre o usuario...ele recebe a ficha completa do farmaceutico
     * <i>nome,endereço,sexo,matricula</i>, entre outros.
     * @param matricula
     * @return true caso a operação seja concluida com sucesso.
     * @return false caso algum erro na busca.
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @Override
    public Farmaceutico buscarPorMatricula(String matricula) throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * " +
                            "FROM funcionario fu, farmaceutico fa " +
                            " WHERE fa.matFuncionario = fu.matricula AND fa.matfuncionario = ?"
            );

            statement.setString(1, matricula);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                String cpf = resultSet.getString("cpf");
                String matricula1 = resultSet.getString("matFuncionario");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                Float salario = resultSet.getFloat("salario");
                Sessao sessao = Sessao.valueOf(resultSet.getString("sessao"));
                String telefone = resultSet.getString("telefone");
                String endereco = resultSet.getString("endereco");
                String numeroCRF = resultSet.getString("numeroCRF");

                return new Farmaceutico(cpf, matricula, nome, senha, salario, sessao, telefone, endereco, numeroCRF);
            } else return null;
        }
    }

    /**
     * Metodo atualizar.
     * Essa função recebe um farmaceutico que deseja ser atualizado do sistema.
     * onde, ele pode alterar suas informações pessoais.
     * @param farmaceutico
     * @return o usuario com suas informações atualizadas.
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @Override
    public boolean atualizar(Farmaceutico farmaceutico) throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){

            PreparedStatement statement1= connection.prepareStatement(
                    "UPDATE farmaceutico " +
                            "SET numeroCRF = ?" +
                            "WHERE matfuncionario = ?"
            );

            PreparedStatement statement2 = connection.prepareStatement(
                    "UPDATE funcionario " +
                            "SET cpf = ?, senha = ?, salario = ?, sessao = ?, telefone = ?, endereco = ?, nome = ?" +
                            "WHERE matricula = ?"
            );

            statement1.setString(1, farmaceutico.getNumeroCRF());
            statement1.setString(2, farmaceutico.getMatricula());

            statement2.setString(1, farmaceutico.getCpf());
            statement2.setString(2, farmaceutico.getSenha());
            statement2.setFloat(3, farmaceutico.getSalario());
            statement2.setString(4, String.valueOf(farmaceutico.getSessao()));
            statement2.setString(5, farmaceutico.getTelefone());
            statement2.setString(6, farmaceutico.getEndereco());
            statement2.setString(7, farmaceutico.getNome());
            statement2.setString(8, farmaceutico.getMatricula());

            return statement1.executeUpdate() > 0 && statement2.executeUpdate() > 0;

        }
    }
}
