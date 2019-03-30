package InterfaceDao;

import Model.Produto;

import java.sql.SQLException;
import java.util.Set;

public interface ProdutoDAO {

    Set<Produto> getProduto() throws SQLException, ClassNotFoundException;

    boolean salvar(Produto produto) throws SQLException, ClassNotFoundException;

    boolean deletar(Produto produto) throws SQLException, ClassNotFoundException;

    Produto buscarPorCodigo(String codigoBarras) throws SQLException, ClassNotFoundException;

    boolean atualizar(Produto produto) throws SQLException, ClassNotFoundException;
}
