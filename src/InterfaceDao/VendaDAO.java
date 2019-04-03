package InterfaceDao;

import Model.Venda;

import java.sql.SQLException;
import java.util.Set;

public interface VendaDAO {

    /**
     * <b>Função set Vendas</b>
     * A função lista todas as vendas cadastradas
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    Set<Venda> getVendas() throws SQLException, ClassNotFoundException;

    /**
     * <b>Função Salvar</b>
     * Tem como objetivo salvar uma venda desejada
     * @param venda
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    boolean salvar(Venda venda) throws SQLException, ClassNotFoundException;

    /**
     * <b>Função Deletar</b>
     * Onde a função recebe um venda que deseja deletar
     * @param venda
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    boolean deletar(Venda venda) throws SQLException, ClassNotFoundException;

    /**
     * <b>Função Buscar por codigo </b>
     * A função recebe o codigo e verifica se a venda existe
     * @param codVenda
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    Venda buscarPorCodigo(int codVenda) throws SQLException, ClassNotFoundException;

    /**
     * <b>Função Atualizar</b>
     * Tem como objetivo atualizar uma venda desejada
     * @param venda
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    boolean atualizar(Venda venda) throws SQLException, ClassNotFoundException;
}
