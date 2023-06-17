package principal.controles;

import java.util.List;

import principal.daos.ClienteDAO;
import principal.daos.DAO;
import principal.modelos.Cliente;

public class ClienteController {
	
	private DAO<Cliente> dao = new ClienteDAO();
	
	private static ClienteController instance;
	
	private ClienteController() {};
	
	public static synchronized ClienteController getInstance() {
		if(instance == null) {
			instance = new ClienteController();
		}
		return instance;
	}
	
	public List<Cliente> getClientes(){
		return dao.listar();
	}
	public Cliente buscarId(int id) {
		return dao.buscarPorId(id);
	}
	public int buscarCPF(String cpf) {
		return dao.buscarClientePorCpf(cpf);
	}
	public void adicionar(Cliente cliente) {
		dao.salvar(cliente);
	}
	public void atualizar(Cliente cliente) {
		dao.atualizar(cliente);
	}
	public void excluir(int id) {
		dao.excluir(id);
	}
}
