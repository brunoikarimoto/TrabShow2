package principal.telas;

import principal.telas.menus.MenuDeCliente;

public class TelaDeFuncionario extends Tela {
	private static TelaDeFuncionario instance;
	
	private TelaDeFuncionario() {
		super("", new MenuDeCliente());
	}
	
	public static synchronized TelaDeFuncionario getInstance() {
		if(instance == null) {
			instance = new TelaDeFuncionario();
		}
		return instance;
	}
}
