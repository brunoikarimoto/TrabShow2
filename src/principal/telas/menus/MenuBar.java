package principal.telas.menus;

import java.util.List;

import principal.controles.ClienteController;
import principal.controles.ComandaController;
import principal.controles.ProdutoController;
import principal.modelos.Cliente;
import principal.modelos.Comanda;
import principal.modelos.Produto;
import principal.modelos.Produto.Tipo;
import principal.telas.Comando;
import principal.telas.TelaBar;
import principal.telas.TelaDeFuncionario;
import principal.util.Mensagem;
import principal.util.Prompt;

public class MenuBar extends Menu {
	private ProdutoController produto = ProdutoController.getInstance();
	private ClienteController cliente = ClienteController.getInstance();
	private ComandaController comanda = ComandaController.getInstance();
	
	public MenuBar(){
		Comando listar = new Comando() {
			public void executar() {
				Prompt.linhaEmBranco();
				Prompt.imprimir(Mensagem.MSG_LISTA_DE_PRODUTOS);
				
				List<Produto> lista = produto.listar();
				
				if(lista.isEmpty()) {
					Prompt.linhaEmBranco();
					Prompt.imprimir("Nenhum produto");
					Prompt.linhaEmBranco();
					Prompt.pressionarEnter();
					TelaBar.getInstance().mostrar();
				}
				else {
					for(Produto produto : lista) {
						Prompt.imprimir("[" + produto.getId() + "] " + produto.getNome() + ", Valor: R$" + produto.getPreco());
					}
				}
			}
		};
		
		adicionar(1, "Listar produtos", new Comando() {
			public void executar() {
				Prompt.linhaEmBranco();
				Prompt.imprimir(Mensagem.MSG_LISTA_DE_PRODUTOS);
				
				List<Produto> lista = produto.listar();
				
				if(lista.isEmpty()) {
					Prompt.imprimir("Nenhum produto");
				}
				else {
					for(Produto produto : lista) {
						Prompt.imprimir("[" + produto.getId() + "] " + produto.getNome() + ", Valor: R$" + produto.getPreco());
					}
				}
				
				Prompt.linhaEmBranco();
				Prompt.pressionarEnter();
				TelaBar.getInstance().mostrar();
			}
		});
		
		adicionar(2, Mensagem.MENU_INCLUIR_PRODUTO, new Comando() {
			public void executar() {
				Prompt.linhaEmBranco();
				Prompt.imprimir(Mensagem.MSG_INCLUSAO_PRODUTO);
				
				String nome = Prompt.lerLinha(Mensagem.MSG_INFORME_NOME);
				float preco = (float)Prompt.lerDecimal(Mensagem.MSG_INFORME_PRECO);
				
				if(!nome.isEmpty() && preco >= 0) {
					int tipo = Prompt.lerInteiro("Informe o tipo: [1] Comida, [2] Bebida");
					
					Tipo tipoProd = null;
					
					switch(tipo) {
					case 1:
						tipoProd = Tipo.COMIDA;
						break;
					case 2:
						tipoProd = Tipo.BEBIDA;
						break;
					default:
						Prompt.imprimir("Invalido");
						Prompt.linhaEmBranco();
						Prompt.pressionarEnter();
						TelaBar.getInstance().mostrar();
					}
					
					produto.adicionar(new Produto(nome, preco, tipoProd));
				}
				else {
					Prompt.imprimir("Inválido");
				}
				
				Prompt.linhaEmBranco();
				Prompt.pressionarEnter();
				TelaBar.getInstance().mostrar();
			}
		});
		
		adicionar(3, Mensagem.MENU_ALTERAR_PRODUTO, new Comando() {
			public void executar() {
				Prompt.linhaEmBranco();
				Prompt.imprimir(Mensagem.MSG_ALTERACAO_PRODUTO);
				
				int idProd = Prompt.lerInteiro(Mensagem.MSG_INFORME_ID);
				
				if(idProd > 0) {
					Produto prod = produto.buscarPorId(idProd);
					
					if(prod != null) {
						String nome = Prompt.lerLinha(Mensagem.MSG_INFORME_NOME);
						float preco = (float)Prompt.lerDecimal(Mensagem.MSG_INFORME_PRECO);
						
						if(!nome.isEmpty() && preco >= 0) {
							int tipo = Prompt.lerInteiro("Informe o tipo: [1] Comida, [2] Bebida");
							Tipo tipoProd = null;
							
							switch(tipo) {
							case 1:
								tipoProd = Tipo.COMIDA;
								break;
							case 2:
								tipoProd = Tipo.BEBIDA;
								break;
							default:
								Prompt.imprimir("Invalido");
								Prompt.linhaEmBranco();
								Prompt.pressionarEnter();
								TelaBar.getInstance().mostrar();
							}
							
							prod.setNome(nome);
							prod.setPreco(preco);
							prod.setTipo(tipoProd);
							
							produto.atualizar(prod);
							
							Prompt.linhaEmBranco();
							Prompt.imprimir(Mensagem.MSG_PRODUTO_ALTERADO);
						}
					}
					else {
						Prompt.linhaEmBranco();
						Prompt.imprimir(Mensagem.MSG_PRODUTO_NAO_ENCONTRADO);
					}
				}
				
				Prompt.linhaEmBranco();
				Prompt.pressionarEnter();
				TelaBar.getInstance().mostrar();
			}
		});
		
		adicionar(4, Mensagem.MENU_EXCLUIR_PRODUTO, new Comando() {
			public void executar() {
				Prompt.linhaEmBranco();
				Prompt.imprimir(Mensagem.MSG_EXCLUSAO_PRODUTO);
				
				int idProd = Prompt.lerInteiro(Mensagem.MSG_INFORME_ID);
				
				if(idProd > 0) {
					Produto prod = produto.buscarPorId(idProd);
					
					if(prod != null) {
						produto.excluir(idProd);
						
						Prompt.linhaEmBranco();
						Prompt.imprimir(Mensagem.MSG_PRODUTO_EXCLUIDO);
					}
					else {
						Prompt.linhaEmBranco();
						Prompt.imprimir(Mensagem.MSG_PRODUTO_NAO_ENCONTRADO);
					}
					
					Prompt.linhaEmBranco();
					Prompt.pressionarEnter();
					TelaBar.getInstance().mostrar();
				}
			}
		});
		
		adicionar(5, "Comprar produtos", new Comando() {
			public void executar() {
				Prompt.linhaEmBranco();
				Prompt.imprimir("Compra de Produtos");
				
				String cpf = Prompt.lerLinha(Mensagem.MSG_INFORME_CPF);
				
				if(!cpf.isEmpty()) {
					int idCli = cliente.buscarCPF(cpf);
					
					if(idCli != -1) {
						Cliente cli = cliente.buscarId(idCli);
						
						listar.executar();
						int escolha = Prompt.lerInteiro("O que deseja?");
						
						List<Produto> lista = produto.listar();
						
						boolean existe = false;
						
						for(Produto produto : lista) {
							if(produto.getId() == escolha) {
								existe = true;
							}
						}
						
						if(existe) {
							int quantidade = Prompt.lerInteiro("Quantos?");
							
							if(quantidade <= 0) {
								Prompt.linhaEmBranco();
								Prompt.imprimir("Invalido");
								Prompt.linhaEmBranco();
								Prompt.pressionarEnter();
								TelaBar.getInstance().mostrar();
							}
							
							Comanda comandaCli = cli.getComanda();
							Produto prod = produto.buscarPorId(escolha);
							
							if(prod.getTipo() == Tipo.BEBIDA) {
								comandaCli.setBebida(comandaCli.getBebida() + (prod.getPreco() * quantidade));
							}
							else {
								comandaCli.setComida(comandaCli.getComida() + (prod.getPreco() * quantidade));
							}
							
							comandaCli.setPago(false);
							
							comanda.atualizar(comandaCli);
							
							Prompt.linhaEmBranco();
							Prompt.imprimir("Adicionado a comanda");
						}
						else {
							Prompt.linhaEmBranco();
							Prompt.imprimir(Mensagem.MSG_PRODUTO_NAO_ENCONTRADO);
						}
					}
				}
				else {
					Prompt.linhaEmBranco();
					Prompt.imprimir(Mensagem.MSG_CLIENTE_NAO_ENCONTRADO);
				}
				
				Prompt.linhaEmBranco();
				Prompt.pressionarEnter();
				TelaBar.getInstance().mostrar();
			}
		});
		
		adicionar(6, Mensagem.MENU_VOLTAR, new Comando() {
			public void executar() {
				TelaDeFuncionario.getInstance().mostrar();
			}
		});
	}
}
