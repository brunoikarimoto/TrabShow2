package principal.telas.menus;

import principal.telas.Comando;
import principal.telas.TelaControleDeShow;
import principal.telas.TelaPrincipal;
import principal.util.Mensagem;

public class MenuDeGerente extends Menu {
	public MenuDeGerente(){
		adicionar(1, "Controle de funcionário", new Comando() {
			public void executar() {
			}
		});
		
		adicionar(2, "Controle de show", new Comando() {
			public void executar() {
				TelaControleDeShow.getInstance().mostrar();
			}
		});
		
		adicionar(3, Mensagem.MENU_VOLTAR, new Comando() {
			public void executar() {
				TelaPrincipal.getInstance().mostrar();
			}
		});
	}
}
