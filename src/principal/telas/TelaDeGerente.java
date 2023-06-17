package principal.telas;

import principal.telas.menus.MenuDeGerente;

public class TelaDeGerente extends Tela {
	private static TelaDeGerente instance;
	
	private TelaDeGerente() {
		super("MENU DE GERENTE", new MenuDeGerente());
	}
	
	public static synchronized TelaDeGerente getInstance() {
		if(instance == null) {
			instance = new TelaDeGerente();
		}
		return instance;
	}
}
