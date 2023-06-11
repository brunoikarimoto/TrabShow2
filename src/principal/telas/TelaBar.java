package principal.telas;

import principal.telas.menus.MenuBar;

public class TelaBar extends Tela {
	private static TelaBar instance;
	
	private TelaBar() {
		super("MENU BAR", new MenuBar());
	}
	
	public static synchronized TelaBar getInstance() {
		if(instance == null) {
			instance = new TelaBar();
		}
		return instance;
	}
}
