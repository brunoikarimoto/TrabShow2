package principal.telas;

import principal.telas.menus.MenuDeCliente;

public class TelaDeCliente extends Tela {
	private static TelaDeCliente instance;
	
	private TelaDeCliente() {
		super("Menu de cliente", new MenuDeCliente());
	}
	
	public static synchronized TelaDeCliente getInstance() {
		if(instance == null) {
			instance = new TelaDeCliente();
		}
		return instance;
	}
}
