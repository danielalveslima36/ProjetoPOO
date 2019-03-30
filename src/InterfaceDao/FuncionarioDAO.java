package InterfaceDao;

import Model.Funcionario;

import java.sql.SQLException;
import java.util.Set;

public interface FuncionarioDAO {

    Set<Funcionario> getFuncionarios() throws SQLException, ClassNotFoundException;

    boolean salvar(Funcionario funcionario) throws SQLException, ClassNotFoundException;

    boolean deletar(Funcionario funcionario) throws SQLException, ClassNotFoundException;

    Funcionario buscarPorMatricula(String matricula) throws SQLException, ClassNotFoundException;

    boolean atualizar(Funcionario funcionario) throws SQLException, ClassNotFoundException;
}
