package InterfaceDao;

import Model.Produto;

import java.sql.SQLException;
import java.util.Set;

public interface ProdutoDAO {

    /**
     * <b>Função Set Produto</b>
     * A função lista todos os produtos cadastrados.
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    Set<Produto> getProduto() throws SQLException, ClassNotFoundException;

    /**
     * <b>Função Salvar</b>
     * Tem como objetivo salvar um produto desejado.
     * @param produto
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    boolean salvar(Produto produto) throws SQLException, ClassNotFoundException;

    /**
     * <b>Função Deletar</b>
     * A função recebe um produto que queira deletar
     * @param produto
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    boolean deletar(Produto produto) throws SQLException, ClassNotFoundException;

    /**
     * <b>Função Buscar por Codigo</b>
     * A função tem como objetivo buscar o produto com o seu codigo
     * @param codigoBarras
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    Produto buscarPorCodigo(String codigoBarras) throws SQLException, ClassNotFoundException;

    /**
     * <b>Função Atualizar</b>
     * Sua função sera atualizar umproduto desejado
     * @param produto
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    boolean atualizar(Produto produto) throws SQLException, ClassNotFoundException;
}
