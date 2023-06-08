package principal.controles;

import java.util.List;

import principal.daos.IngressoDAO;
import principal.daos.DAO;
import principal.modelos.Ingresso;

public class IngressoController {
	private DAO<Ingresso> dao = new IngressoDAO();
	
	private static IngressoController instance;
	
	private IngressoController() {};
	
	public static synchronized IngressoController getInstance() {
		if(instance == null) {
			instance = new IngressoController();
		}
		return instance;
	}
	
	public List<Ingresso> getIngressos(){
		return dao.listar();
	}
	public void adicionar(Ingresso ingresso) {
		dao.salvar(ingresso);
	}
	public Ingresso buscarId(int id) {
		return dao.buscarPorId(id);
	}
	public List<Ingresso> buscarPorCPF(String cpf){
		return dao.listaIngressoCPF(cpf);
	}

}
