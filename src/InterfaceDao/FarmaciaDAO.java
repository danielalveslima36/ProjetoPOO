package InterfaceDao;

import Model.Farmacia;

import java.sql.SQLException;
import java.util.Set;

public interface FarmaciaDAO {

    Set<Farmacia> getFarmacia() throws SQLException, ClassNotFoundException;

    boolean salvar(Farmacia farmacia) throws SQLException, ClassNotFoundException;

    boolean deletar(Farmacia farmacia) throws SQLException, ClassNotFoundException;

    Farmacia buscarPorCnpj(String cnpj) throws SQLException, ClassNotFoundException;

    boolean atualizar(Farmacia farmacia) throws SQLException, ClassNotFoundException;
}
