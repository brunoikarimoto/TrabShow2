package principal.telas.menus;

import java.util.List;

import principal.controles.ClienteController;
import principal.controles.IngressoController;
import principal.controles.ShowController;
import principal.modelos.Cliente;
import principal.modelos.Comanda;
import principal.modelos.Pessoa.Sexo;
import principal.telas.Comando;
import principal.telas.TelaDeCliente;
import principal.telas.TelaPrincipal;
import principal.util.Mensagem;
import principal.util.Prompt;
import principal.modelos.Show;
import principal.modelos.Ingresso;

public class MenuDeCliente extends Menu {
	private ClienteController controle = ClienteController.getInstance();
	private ShowController showControle = ShowController.getInstance();
	private IngressoController ingressoControle = IngressoController.getInstance();
	
	public MenuDeCliente() {
		adicionar(1, Mensagem.MENU_CADASTRO_DE_CLIENTES, new Comando() {
			public void executar() {
				Prompt.linhaEmBranco();
				/*NOME, CPF, IDADE, SEXO*/
				String nome = Prompt.lerLinha(Mensagem.MSG_INFORME_NOME);
				String cpf = Prompt.lerLinha(Mensagem.MSG_INFORME_CPF);
				
				int id_cliente = controle.buscarCPF(cpf);
				if(id_cliente != -1) {
					Prompt.linhaEmBranco();
					Prompt.imprimir("Cliente já existente");
					Prompt.pressionarEnter();
					TelaDeCliente.getInstance().mostrar();;
				}
				int idade = Prompt.lerInteiro("Idade? ");
				int opcao = Prompt.lerInteiro("Sexo: [1]Homem, [2]Mulher");
				
				Sexo sexo;
				
				if(opcao == 1) {
					sexo = Sexo.MASCULINO;
				}
				else {
					sexo = Sexo.FEMININO;
				}
				
				/*fazer validacao se n é vazio*/
				if(!nome.isEmpty() && !cpf.isEmpty() && idade >= 18) {					
					controle.adicionar(new Cliente(nome, cpf, idade, sexo));
				}
				else {
					Prompt.linhaEmBranco();
					Prompt.imprimir("Campos vazios ou menor de idade!");
				}
				Prompt.linhaEmBranco();
				Prompt.pressionarEnter();
				TelaDeCliente.getInstance().mostrar();;
			}
		});
		
		adicionar(2, Mensagem.MENU_ALTERAR_CLIENTE, new Comando() {
			public void executar() {
				Prompt.linhaEmBranco();
				Prompt.imprimir(Mensagem.MSG_ALTERACAO_CLIENTE);
				
				String cpf = Prompt.lerLinha(Mensagem.MSG_INFORME_CPF);
				
				if(!cpf.isEmpty()) {
					int id_cliente = controle.buscarCPF(cpf);
					
					if(id_cliente != -1) {
						Cliente cliente = controle.buscarId(id_cliente);
						String nome = Prompt.lerLinha(Mensagem.MSG_INFORME_NOME);
						String cpfNovo = Prompt.lerLinha(Mensagem.MSG_INFORME_CPF);
						int id_cliente_novo = controle.buscarCPF(cpfNovo);
						if(!cpf.equals(cpfNovo) && id_cliente_novo != -1) {
							Prompt.linhaEmBranco();
							Prompt.imprimir("Cliente já existente");
							Prompt.pressionarEnter();
							TelaDeCliente.getInstance().mostrar();;
						}
						int idade = Prompt.lerInteiro("Idade?");
						int opcao = Prompt.lerInteiro("Sexo: [1]Homem, [2]Mulher");
						
						Sexo sexo;
						
						if(opcao == 1) {
							sexo = Sexo.MASCULINO;
						}
						else {
							sexo = Sexo.FEMININO;
						}
						
						/*fazer validacao se n é vazio*/
						if(!nome.isEmpty() && !cpf.isEmpty() && idade >= 18) {							
							cliente.setNome(nome);
							cliente.setCpf(cpfNovo);
							cliente.setIdade(idade);
							cliente.setSexo(sexo);
							
							controle.atualizar(cliente);
						}
						else {
							Prompt.linhaEmBranco();
							Prompt.imprimir("Campos vazios ou menor de idade!");
						}
						
						Prompt.linhaEmBranco();
						Prompt.imprimir(Mensagem.MSG_CLIENTE_ALTERADO);
					}
					else {						
						Prompt.linhaEmBranco();
						Prompt.imprimir(Mensagem.MSG_CLIENTE_NAO_ENCONTRADO);
					}
					
					Prompt.linhaEmBranco();
					Prompt.pressionarEnter();
				}
				
				TelaDeCliente.getInstance().mostrar();;
			}
		});
		
		adicionar(3, Mensagem.MENU_EXCLUIR_CLIENTE, new Comando() {
			public void executar() {
				Prompt.linhaEmBranco();
				Prompt.imprimir(Mensagem.MSG_EXCLUSAO_CLIENTE);
				String cpf = Prompt.lerLinha(Mensagem.MSG_INFORME_CPF);
				
				if(!cpf.isEmpty()) {
					int id_cliente = controle.buscarCPF(cpf);
					
					if(id_cliente != -1) {
						Cliente cliente = controle.buscarId(id_cliente);
						
						Comanda comandaCli = cliente.getComanda();
						
						if(comandaCli.getPago() == false) {
							Prompt.linhaEmBranco();
							Prompt.imprimir("Pague tudo antes");
							Prompt.linhaEmBranco();
							Prompt.pressionarEnter();
							TelaDeCliente.getInstance().mostrar();
						}
						
						controle.excluir(cliente.getId());
						
						Prompt.linhaEmBranco();
						Prompt.imprimir(Mensagem.MSG_CLIENTE_EXCLUIDO);
					}
					else {
						Prompt.linhaEmBranco();
						Prompt.imprimir(Mensagem.MSG_CLIENTE_NAO_ENCONTRADO);
					}
					Prompt.linhaEmBranco();
					Prompt.pressionarEnter();
				}
				TelaDeCliente.getInstance().mostrar();;
			}
		});
		
		/*TODO INGRESSO*/
		adicionar(4, "Comprar ingresso", new Comando() {
			public void executar() {
				Prompt.linhaEmBranco();
				Prompt.imprimir("COMPRA DE INGRESSO");
				String cpf = Prompt.lerLinha(Mensagem.MSG_INFORME_CPF);
				
				if(!cpf.isEmpty()) {
					int id_cliente = controle.buscarCPF(cpf);
					
					if(id_cliente != -1) {
						Cliente cliente = controle.buscarId(id_cliente);
						List<Show> shows = showControle.getShow();
						
						if(shows.isEmpty()) {
							Prompt.linhaEmBranco();
							Prompt.imprimir("Nenhuma show para comprar");
						}
						else {
							for(int i = 0; i < shows.size(); i++) {
								Prompt.imprimir("[" + shows.get(i).getId() + "] " + shows.get(i).getNomeShow());
							}
							
							int escolha = Prompt.lerInteiro("Qual show deseja");
							Show showEscolhido = shows.get(escolha);
							
							if(showEscolhido != null) {
								Prompt.imprimir("Quantidade de ingressos: " + showEscolhido.getQuantIngressos());
								
								if(showEscolhido.getQuantIngressos() <= 0) {
									Prompt.linhaEmBranco();
									Prompt.imprimir("Ingressos esgotados!");
									Prompt.linhaEmBranco();
									Prompt.pressionarEnter();
									TelaDeCliente.getInstance().mostrar();
								}
								
								if(cliente.getSexo() == Sexo.MASCULINO) {
									Prompt.imprimir("Valor do ingresso: R$" + showEscolhido.getValorMasc());
								}
								else {
									Prompt.imprimir("Valor do ingresso: R$" + showEscolhido.getValorFem());
								}
								
								escolha = Prompt.lerInteiro("Deseja comprar? [1] Sim, [2] Não");
								switch(escolha) {
									case 1:
										showEscolhido.setQuantIngressos(showEscolhido.getQuantIngressos() - 1);
										showControle.atualizar(showEscolhido);
										ingressoControle.adicionar(new Ingresso(showEscolhido.getNomeShow(), cliente.getCpf()));
										Prompt.linhaEmBranco();
										Prompt.imprimir("Comprado com sucesso");
										break;
									case 2:
										Prompt.linhaEmBranco();
										Prompt.imprimir("Volte sempre!");
										break;
									default:
										Prompt.linhaEmBranco();
										Prompt.imprimir("Opcao invalida");
								}
							}
							else {
								Prompt.linhaEmBranco();
								Prompt.imprimir("Opcao invalida");
							}
						}
					}
					else {
						Prompt.linhaEmBranco();
						Prompt.imprimir("Crie uma conta antes!");
					}
					Prompt.linhaEmBranco();
					Prompt.pressionarEnter();
				}
				TelaDeCliente.getInstance().mostrar();
			}
		});
		
		adicionar(5, "Buscar Infos Cliente", new Comando() {
			public void executar() {
				Prompt.linhaEmBranco();
				Prompt.imprimir("INFOS DO CLIENTE");
				String cpf = Prompt.lerLinha(Mensagem.MSG_INFORME_CPF);
				
				if(!cpf.isEmpty()) {
					int id_cliente = controle.buscarCPF(cpf);
					
					if(id_cliente != -1) {
						Cliente cliente = controle.buscarId(id_cliente);
						List<Ingresso> listaIngresso = ingressoControle.buscarPorCPF(cpf);
						Prompt.imprimir("Nome: " + cliente.getNome());
						Prompt.imprimir("CPF: " + cliente.getCpf());
						Prompt.imprimir("Idade: " + cliente.getIdade());
						Prompt.imprimir("Sexo: " + cliente.getSexo());
						Prompt.imprimir("Ingressos: ");
						if(listaIngresso != null) {
							for(Ingresso ingresso: listaIngresso) {
								Prompt.imprimir("Nome show: " + ingresso.getNomeShow());
								Prompt.imprimir("Status: " + ingresso.getStatus());
								Prompt.imprimir("-----------------------------------------");
							}
						}
						else {
							Prompt.imprimir("Nenhum ingresso =(");
						}
					}
					else {
						Prompt.linhaEmBranco();
						Prompt.imprimir(Mensagem.MSG_CLIENTE_NAO_ENCONTRADO);
					}
					Prompt.linhaEmBranco();
					Prompt.pressionarEnter();
				}
				TelaDeCliente.getInstance().mostrar();
			}
		});
		
		adicionar(6, Mensagem.MENU_VOLTAR, new Comando() {
			public void executar() {
				TelaPrincipal.getInstance().mostrar();
			}
		});
	}
}
