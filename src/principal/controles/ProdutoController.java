package principal.controles;

import java.util.List;

import principal.daos.DAO;
import principal.daos.ProdutoDAO;
import principal.modelos.Produto;

public class ProdutoController {
	private DAO<Produto> dao = new ProdutoDAO();
	private static ProdutoController instance;
	
	public static synchronized ProdutoController getInstance() {
		if(instance == null) {
			instance = new ProdutoController();
		}
		return instance;
	}
	
	public List<Produto> listar(){
		return dao.listar();
	}
	public void adicionar(Produto produto) {
		dao.salvar(produto);
	}
	public Produto buscarPorId(int id) {
		return dao.buscarPorId(id);
	}
	public void atualizar(Produto produto) {
		dao.atualizar(produto);
	}
	public void excluir(int id) {
		dao.excluir(id);
	}
}
