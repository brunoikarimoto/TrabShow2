package principal.controles;

import java.util.List;

import principal.daos.DAO;
import principal.daos.ShowDAO;
import principal.modelos.Show;

public class ShowController {
	private static ShowController instance;
	private DAO<Show> dao = new ShowDAO();
	
	public static synchronized ShowController getInstance() {
		if(instance == null) {
			instance = new ShowController();
		}
		return instance;
	}
	
	public List<Show> getShow(){
		return dao.listar();
	}
	public Show buscarId(int id) {
		return dao.buscarPorId(id);
	}
	public void adicionar(Show show) {
		dao.salvar(show);
	}
	public void atualizar(Show show) {
		dao.atualizar(show);
	}
	public void excluir(int id) {
		dao.excluir(id);
	}
}
