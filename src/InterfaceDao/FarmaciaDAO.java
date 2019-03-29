package InterfaceDao;

import Model.Farmacia;

import java.util.Set;

public interface FarmaciaDAO {

    Set<Farmacia> getFarmacia();

    boolean salvar(Farmacia farmacia);

    boolean deletar(Farmacia farmacia);

    Farmacia buscarPorCnpj(String cnpj);

    boolean atualizar(Farmacia farmacia);
}
