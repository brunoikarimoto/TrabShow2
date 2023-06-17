package principal.telas;

import principal.telas.menus.MenuControleDeShow;

public class TelaControleDeShow extends Tela {
	private static TelaControleDeShow instance;
	
	private TelaControleDeShow() {
		super("MENU CONTROLE DE SHOW", new MenuControleDeShow());
	}
	
	public static synchronized TelaControleDeShow getInstance() {
		if(instance == null) {
			instance = new TelaControleDeShow();
		}
		return instance;
	}
}
