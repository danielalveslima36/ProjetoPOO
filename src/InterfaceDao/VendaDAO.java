package InterfaceDao;

import java.util.Set;

public interface VendaDAO {

    Set<VendaDAO> getVenda();

    boolean salvar(VendaDAO venda);

    boolean deletar(VendaDAO venda);

    VendaDAO buscarPorCodigo(int codVenda);

    boolean atualizar(VendaDAO venda);
}
