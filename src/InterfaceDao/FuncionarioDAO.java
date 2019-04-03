package InterfaceDao;

import Model.Funcionario;

import java.sql.SQLException;
import java.util.Set;

public interface FuncionarioDAO {

    /**
     * b>Função set Funcionario</b>
     * A função foi criada para listar todos os funcionarios cadastradas
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    Set<Funcionario> getFuncionarios() throws SQLException, ClassNotFoundException;

    /**
     * <b> Função Salvar</b>
     * Tem como objetivo salvar um funcionario
     * @param funcionario
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    boolean salvar(Funcionario funcionario) throws SQLException, ClassNotFoundException;

    /**
     * <b>Função Deletar</b>
     * A função recebe um funcionario que deseja deletar
     * @param funcionario
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    boolean deletar(Funcionario funcionario) throws SQLException, ClassNotFoundException;

    /**
     * <b>Função Buscar por Matricula</b>
     * Recebe um funcionario verifica se ele existe
     * @param matricula
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    Funcionario buscarPorMatricula(String matricula) throws SQLException, ClassNotFoundException;

    /**
     * <b>Função deletar</b>
     * Onde a função tem como objetivo atualizar um funcionario
     * @param funcionario
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    boolean atualizar(Funcionario funcionario) throws SQLException, ClassNotFoundException;
}
