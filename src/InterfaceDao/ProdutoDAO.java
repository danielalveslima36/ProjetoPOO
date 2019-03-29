package InterfaceDao;

import Model.Produto;

import java.util.Set;

public interface ProdutoDAO {

    Set<Produto> getProduto();

    boolean salvar(Produto produto);

    boolean deletar(Produto produto);

    Produto buscarPorCodigo(String codigoBarras);

    boolean atualizar(Produto produto);
}
