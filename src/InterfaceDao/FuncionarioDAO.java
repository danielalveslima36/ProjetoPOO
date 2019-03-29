package InterfaceDao;

import Model.Funcionario;

import java.util.Set;

public interface FuncionarioDAO {

    Set<Funcionario> getFuncionarios();

    boolean salvar(Funcionario funcionario) ;

    boolean deletar(Funcionario funcionario);

    Funcionario buscarPorMatricula(String matricula) ;

    boolean atualizar(Funcionario funcionario) ;
}
