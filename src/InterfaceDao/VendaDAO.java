package InterfaceDao;

import Model.Venda;

import java.sql.SQLException;
import java.util.Set;

public interface VendaDAO {

    Set<Venda> getVendas() throws SQLException, ClassNotFoundException;

    boolean salvar(Venda venda) throws SQLException, ClassNotFoundException;

    boolean deletar(Venda venda) throws SQLException, ClassNotFoundException;

    Venda buscarPorCodigo(int codVenda) throws SQLException, ClassNotFoundException;

    boolean atualizar(Venda venda) throws SQLException, ClassNotFoundException;
}
