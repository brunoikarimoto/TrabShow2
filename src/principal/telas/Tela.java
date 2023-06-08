package principal.telas;

import java.util.List;

import principal.telas.menus.ItemMenu;
import principal.telas.menus.Menu;
import principal.util.Mensagem;
import principal.util.Prompt;

public abstract class Tela {
	protected String titulo;
	protected Menu menu;
	
	public Tela(String titulo) {
		this.titulo = titulo;
	}
	
	public Tela(String titulo, Menu menu) {
		this.titulo = titulo;
		this.menu = menu;
	}
	
	public void mostrar() {
		if(menu != null) {
			Prompt.linhaEmBranco();
			Prompt.imprimir(titulo);
			Prompt.imprimir(Mensagem.MSG_ESCOLHA);
			List<ItemMenu> itensMenu = menu.getItens();
			for(ItemMenu item: itensMenu) {
				item.mostrar();
			}
			Integer opcao = Prompt.lerInteiro() -1; //pq o vetor começa em 0
			ItemMenu itemSelecionado = itensMenu.get(opcao);
			itemSelecionado.executar();
		}
	}
}
