package principal.telas.menus;

import principal.telas.Comando;
import principal.util.Mensagem;
import principal.util.Prompt;
import principal.telas.TelaDeCliente;
import principal.telas.TelaDeFuncionario;
import principal.telas.TelaDeGerente;

public class MenuInicial extends Menu {
	
	public MenuInicial() {
		adicionar(1, "Cliente", new Comando() {
			public void executar() {
				TelaDeCliente.getInstance().mostrar();
			}
		});
		
		adicionar(2, "Funcionario", new Comando() {
			public void executar() {
				TelaDeFuncionario.getInstance().mostrar();
			}
		});
		
		adicionar(3, "Gerente", new Comando() {
			public void executar() {
				TelaDeGerente.getInstance().mostrar();
			}
		});
		
		adicionar(4, Mensagem.MENU_SAIR, new Comando() {
			public void executar() {
				Prompt.linhaEmBranco();
				Prompt.imprimir(Mensagem.MSG_PROGRAMA_ENCERRADO);
			}
		});
	}
}
