package principal.telas;

import principal.telas.menus.MenuDeCliente;

public class TelaDeGerente extends Tela {
	private static TelaDeGerente instance;
	
	private TelaDeGerente() {
		super("", new MenuDeCliente());
	}
	
	public static synchronized TelaDeGerente getInstance() {
		if(instance == null) {
			instance = new TelaDeGerente();
		}
		return instance;
	}
}
