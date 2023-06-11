package principal.telas;

import principal.telas.menus.MenuControleEntrada;

public class TelaControleEntrada extends Tela {
	private static TelaControleEntrada instance;
	
	private TelaControleEntrada() {
		super("ENTRADA, SAÍDA E PAGAMENTO", new MenuControleEntrada());
	}
	
	public static synchronized TelaControleEntrada getInstance() {
		if(instance == null) {
			instance = new TelaControleEntrada();
		}
		return instance;
	}
}
