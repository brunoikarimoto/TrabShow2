package principal.controles;

import principal.daos.ComandaDAO;
import principal.daos.DAO;
import principal.modelos.Comanda;

public class ComandaController {
	private DAO<Comanda> dao = new ComandaDAO();
	private static ComandaController instance;
	
	public static synchronized ComandaController getInstance() {
		if(instance == null) {
			instance = new ComandaController();
		}
		return instance;
	}
	
	public Comanda buscarPorId(int id) {
		return dao.buscarPorId(id);
	}
	public void atualizar(Comanda comanda) {
		dao.atualizar(comanda);
	}
}
