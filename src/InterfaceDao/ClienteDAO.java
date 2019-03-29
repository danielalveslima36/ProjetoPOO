package InterfaceDao;

import Model.Cliente;

import java.sql.SQLException;
import java.util.Set;

public interface ClienteDAO {

    Set<Cliente> getClientes() throws SQLException, ClassNotFoundException;

    boolean salvar(Cliente cliente) throws SQLException, ClassNotFoundException;

    boolean deletar(Cliente cliente) throws SQLException, ClassNotFoundException;

    Cliente buscarPorCpf(String cpf) throws SQLException, ClassNotFoundException;

    boolean atualizar(Cliente cliente) throws SQLException, ClassNotFoundException;
}
