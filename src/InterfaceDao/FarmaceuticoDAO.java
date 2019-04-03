package InterfaceDao;

import Model.Farmaceutico;

import java.sql.SQLException;
import java.util.Set;

public interface FarmaceuticoDAO {

    /**
     * <b>Função set Farmaceutico</b>
     * Onde lista todos os clientes cadastrados
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    Set<Farmaceutico> getFarmaceutico() throws SQLException, ClassNotFoundException;

    /**
     * <b>Função Salvar</b>
     * Tem como objetivo salvar todos os Farmaceutico desejados.
     * @param farmaceutico
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    boolean salvar(Farmaceutico farmaceutico) throws SQLException, ClassNotFoundException;

    /**
     * <b>Função Deletar</b>
     * A função recebe um farmaceutico que deseja ser deletado.
     * @param farmaceutico
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    boolean deletar(Farmaceutico farmaceutico) throws SQLException, ClassNotFoundException;

    /**
     * <b>Função Buscar por Matricula</b>
     * A função recebe  numero da matricula do farmaceutico.
     * Verifica se o farmaceutico existe
     * @param matricula
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    Farmaceutico buscarPorMatricula(String matricula) throws SQLException, ClassNotFoundException;

    /**
     * <b>Função Atualizar </b>
     * A função tem como objetivo atualizar um farmaceutico
     * @param farmaceutico
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    boolean atualizar(Farmaceutico farmaceutico) throws SQLException, ClassNotFoundException;
}
