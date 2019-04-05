package InterfaceDao;

import Model.Farmacia;

import java.sql.SQLException;
import java.util.Set;

public interface FarmaciaDAO {

    /**
     * <b>Função set Farmacia</b>
     * A função foi criada para listar todas as farmacias cadastradas
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    Set<Farmacia> getFarmacia() throws SQLException, ClassNotFoundException;

    /**
     * <b>Função Salvar</b>
     * Onde seu principal objetivo é salvar uma farmacia
     * @param farmacia
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    boolean salvar(Farmacia farmacia) throws SQLException, ClassNotFoundException;

    /**
     * <b>Função deletar</b>
     * A função recebe uma farmacia que deseja ser deletada
     * @param farmacia
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    boolean deletar(Farmacia farmacia) throws SQLException, ClassNotFoundException;

    /**
     * <b>Função Buscar por Cnpj</b>
     * Busca com o cnpj a farmacia e verifica se existe
     * @param cnpj
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    Farmacia buscarPorCnpj(String cnpj) throws SQLException, ClassNotFoundException;

    /**
     * <b>Função Atualizar</b>
     * Essa função recebe uma farmacia e atualiza a mesma.
     * @param farmacia
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    boolean atualizar(Farmacia farmacia) throws SQLException, ClassNotFoundException;
}
