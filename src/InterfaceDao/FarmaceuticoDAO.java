package InterfaceDao;

import Model.Farmaceutico;

import java.sql.SQLException;
import java.util.Set;

public interface FarmaceuticoDAO {

    Set<Farmaceutico> getFarmaceutico() throws SQLException, ClassNotFoundException;

    boolean salvar(Farmaceutico farmaceutico) throws SQLException, ClassNotFoundException;

    boolean deletar(Farmaceutico farmaceutico) throws SQLException, ClassNotFoundException;

    Farmaceutico buscarPorCRF(String CRF) throws SQLException, ClassNotFoundException;

    boolean atualizar(Farmaceutico farmaceutico) throws SQLException, ClassNotFoundException;
}
