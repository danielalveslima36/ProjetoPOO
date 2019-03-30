package BancoDAO;

import Banco.ConFactory;
import InterfaceDao.FarmaceuticoDAO;
import Model.Farmaceutico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import Enum.Sessao;

public class FarmaceuticoDaoBanco implements FarmaceuticoDAO {
    private ConFactory factory;

    public FarmaceuticoDaoBanco(){
        factory = new ConFactory();
    }

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
                String senha = resultSet.getString("senha");
                Float salario = resultSet.getFloat("salario");
                Sessao sessao = Sessao.valueOf(resultSet.getString("sessao"));
                String telefone = resultSet.getString("telefone");
                String endereco = resultSet.getString("endereco");
                String numeroCRF = resultSet.getString("numeroCRF");

                farmaceuticos.add(new Farmaceutico(cpf, matricula, senha, salario, sessao, telefone, endereco, numeroCRF));
            }
            return farmaceuticos;

        }
    }

    @Override
    public boolean salvar(Farmaceutico farmaceutico) throws SQLException, ClassNotFoundException {

        try(Connection connection = factory.getConnection()){
            PreparedStatement statement1 = connection.prepareStatement(
                    "INSERT INTO funcionario(matricula, cpf, senha, salario, endereco, sessao, telefone) VALUES(?, ?, ?, ?, ?, ?, ?);"
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

            statement2.setString(1, farmaceutico.getMatricula());
            statement2.setString(2, farmaceutico.getNumeroCRF());

            return statement1.executeUpdate() > 0 && statement2.executeUpdate() > 0;
        }
    }

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

    @Override
    public Farmaceutico buscarPorCRF(String CRF) throws SQLException, ClassNotFoundException {
        try(Connection connection = factory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                        "SELECT * " +
                            "FROM funcionario fu, farmaceutico fa " +
                            " WHERE fa.matFuncionario = fu.matricula AND fa.numeroCRF = ?"
            );

            statement.setString(1, CRF);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                String cpf = resultSet.getString("cpf");
                String matricula = resultSet.getString("matFuncionario");
                String senha = resultSet.getString("senha");
                Float salario = resultSet.getFloat("salario");
                Sessao sessao = Sessao.valueOf(resultSet.getString("sessao"));
                String telefone = resultSet.getString("telefone");
                String endereco = resultSet.getString("endereco");
                String numeroCRF = resultSet.getString("numeroCRF");

                return new Farmaceutico(cpf, matricula, senha, salario, sessao, telefone, endereco, numeroCRF);
            } else return null;
        }
    }

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
                            "SET cpf = ?, senha = ?, salario = ?, sessao = ?, telefone = ?, endereco = ?" +
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
            statement2.setString(7, farmaceutico.getMatricula());

            return statement1.executeUpdate() > 0 && statement2.executeUpdate() > 0;

        }
    }
}
