package principal.telas;

import principal.telas.menus.MenuDeFuncionario;

public class TelaDeFuncionario extends Tela {
	private static TelaDeFuncionario instance;
	
	private TelaDeFuncionario() {
		super("MENU DE FUNCIONÁRIOS", new MenuDeFuncionario());
	}
	
	public static synchronized TelaDeFuncionario getInstance() {
		if(instance == null) {
			instance = new TelaDeFuncionario();
		}
		return instance;
	}
}
