package principal.telas.menus;

import principal.telas.Comando;
import principal.telas.TelaBar;
import principal.telas.TelaControleEntrada;
import principal.telas.TelaPrincipal;
import principal.util.Mensagem;

public class MenuDeFuncionario extends Menu {
	public MenuDeFuncionario(){
		adicionar(1, "Bar", new Comando() {
			public void executar() {
				TelaBar.getInstance().mostrar();
			}
		});
		
		adicionar(2, "Controle entrada/saída", new Comando() {
			public void executar() {
				TelaControleEntrada.getInstance().mostrar();
			}
		});
		
		adicionar(3, Mensagem.MENU_VOLTAR, new Comando() {
			public void executar() {
				TelaPrincipal.getInstance().mostrar();
			}
		});
	}
}
