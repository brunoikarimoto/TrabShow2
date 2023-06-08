package principal.daos;

import java.util.List;

public interface DAO<T> {
	
	T salvar(T entidade);
	T atualizar(T entidade);
	T buscarPorId(int id);
	List<T> listar();
	void excluir(int id);
	int buscarPorCpf(String cpf);
	List<T>listaIngressoCPF(String cpf);
	
}
