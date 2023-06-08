package principal.telas;

import principal.util.Prompt;

public class TelaBoasVindas extends Tela {
	public TelaBoasVindas() {
		super("BEM VINDO");
	}
	
	@Override
	public void mostrar() {
		Prompt.linhaEmBranco();
		Prompt.imprimir("APLICATIVO CASA DE SHOW");
		Prompt.linhaEmBranco();
		Prompt.pressionarEnter();
		TelaPrincipal.getInstance().mostrar();
	}
}
