package principal.telas;

import principal.telas.menus.MenuInicial;
import principal.util.Mensagem;

public class TelaPrincipal extends Tela {
	
	private static Tela instance;
	
	private TelaPrincipal() {
		super(Mensagem.TELA_PRINCIPAL, new MenuInicial());
	}
	
	public static synchronized Tela getInstance() {
		if(instance == null) {
			instance = new TelaPrincipal();
		}
		return instance;
	}
	
}
