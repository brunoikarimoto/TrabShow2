package principal.controles;

import java.util.List;

import principal.daos.ArtistaDAO;
import principal.daos.DAO;
import principal.modelos.Artista;

public class ArtistaController {
private DAO<Artista> dao = new ArtistaDAO();
	
	private static ArtistaController instance;
	
	private ArtistaController() {};
	
	public static synchronized ArtistaController getInstance() {
		if(instance == null) {
			instance = new ArtistaController();
		}
		return instance;
	}
	
	public List<Artista> getArtistas(){
		return dao.listar();
	}
	public Artista buscarId(int id) {
		return dao.buscarPorId(id);
	}
	public int buscarCPF(String cpf) {
		return dao.buscarArtistaPorCpf(cpf);
	}
	public void adicionar(Artista artista) {
		dao.salvar(artista);
	}
	public void atualizar(Artista artista) {
		dao.atualizar(artista);
	}
	public void excluir(int id) {
		dao.excluir(id);
	}
}
