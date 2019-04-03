package InterfaceDao;

import Model.Cliente;

import java.sql.SQLException;
import java.util.Set;

public interface ClienteDAO {

    /**
     * <b>Função set Cliente.</b>
     * onde lista todos os clientes inseridos.
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    Set<Cliente> getClientes() throws SQLException, ClassNotFoundException;

    /**
     * <b>Função salvar</b>
     * Tem como objetivo salvar todos os clientes desejados.
     * @param cliente
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    boolean salvar(Cliente cliente) throws SQLException, ClassNotFoundException;

    /**
     * <b>Função Deletar</b>
     * A função recebe um cliente que deseja ser deletado.
     * @param cliente
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    boolean deletar(Cliente cliente) throws SQLException, ClassNotFoundException;

    /**
     * <b>Função buscar Por Cpf</b>
     * onde a função recebe o cpf do cliente e verifica se ele existe
     * @param cpf
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    Cliente buscarPorCpf(String cpf) throws SQLException, ClassNotFoundException;

    /**
     * <b>Função atualizar</b>
     * A função recebe um cliente que deseja ser atualizado.
     * @param cliente
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    boolean atualizar(Cliente cliente) throws SQLException, ClassNotFoundException;
}
