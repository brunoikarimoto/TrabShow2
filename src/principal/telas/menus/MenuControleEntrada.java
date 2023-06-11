package principal.telas.menus;

import java.util.List;

import principal.controles.ClienteController;
import principal.controles.ComandaController;
import principal.controles.IngressoController;
import principal.controles.ShowController;
import principal.modelos.Cliente;
import principal.modelos.Comanda;
import principal.modelos.Ingresso;
import principal.modelos.Ingresso.Status;
import principal.modelos.Show;
import principal.telas.Comando;
import principal.telas.TelaControleEntrada;
import principal.telas.TelaDeFuncionario;
import principal.util.Mensagem;
import principal.util.Prompt;

public class MenuControleEntrada extends Menu {
	private ShowController show = ShowController.getInstance();
	private IngressoController ingresso = IngressoController.getInstance();
	private ClienteController cliente = ClienteController.getInstance();
	private ComandaController comanda = ComandaController.getInstance();
	
	public MenuControleEntrada() {
		adicionar(1, "Entrada", new Comando() {
			public void executar() {
				List<Show> listaShow = show.getShow();
				
				if(listaShow.isEmpty()) {
					Prompt.linhaEmBranco();
					Prompt.imprimir("Nenhum show no momento!");
				}
				else {
					for(Show s : listaShow) {
						Prompt.imprimir("[" + s.getId() + "] " + s.getNomeShow());
					}
					
					int escolha = Prompt.lerInteiro("Qual show deseja?");
					
					if(escolha > 0) {
						Show showEscolhido = show.buscarId(escolha);
						
						if(showEscolhido != null) {
							String cpfCli = Prompt.lerLinha(Mensagem.MSG_INFORME_CPF);
							
							
							if(!cpfCli.isEmpty()) {
								int idCli = cliente.buscarCPF(cpfCli);
								
								if(idCli != -1) {
									List<Ingresso> ingressosCli = ingresso.buscarPorCPF(cpfCli);
									int idIngressoCli = -1;
									
									if(ingressosCli == null) {
										Prompt.linhaEmBranco();
										Prompt.imprimir("Não tem ingressos");
										Prompt.linhaEmBranco();
										Prompt.pressionarEnter();
										TelaControleEntrada.getInstance().mostrar();
									}
									else {
										for(Ingresso i : ingressosCli) {
											if(i.getNomeShow().equals(showEscolhido.getNomeShow())) {
												idIngressoCli = i.getId();
											}
											
											if(i.getStatus() == Status.ATIVO) {
												Prompt.linhaEmBranco();
												Prompt.imprimir("Já está em uma festa");
												Prompt.linhaEmBranco();
												Prompt.pressionarEnter();
												TelaControleEntrada.getInstance().mostrar();
											}
										}
									}
									
									
									if(idIngressoCli != -1) {
										Ingresso ingressoCli = ingresso.buscarId(idIngressoCli);
										int simNao = Prompt.lerInteiro("Deseja entrar? [1] Sim, [2] Não");
										
										switch(simNao) {
											case 1:
												 ingressoCli.setStatus(Status.ATIVO);
												 ingresso.atualizar(ingressoCli);
												 Prompt.linhaEmBranco();
												 Prompt.imprimir("Boa festa!");
												 break;
											case 2:
												Prompt.linhaEmBranco();
												Prompt.imprimir("Volte sempre");
												break;
											default:
												Prompt.linhaEmBranco();
												Prompt.imprimir("Inválido");
										}
									}
									else {
										Prompt.linhaEmBranco();
										Prompt.imprimir("Não tem ingresso para esse show");
									}
								}
								else {
									Prompt.linhaEmBranco();
									Prompt.imprimir(Mensagem.MSG_CLIENTE_NAO_ENCONTRADO);
								}
							}
							else {
								Prompt.linhaEmBranco();
								Prompt.imprimir("Vazio!");
							}
						}
						else {
							Prompt.linhaEmBranco();
							Prompt.imprimir("Inválido");
						}
					}
					else {
						Prompt.linhaEmBranco();
						Prompt.imprimir("Inválido");
					}
				}
				
				Prompt.linhaEmBranco();
				Prompt.pressionarEnter();
				TelaControleEntrada.getInstance().mostrar();
			}
		});
		
		adicionar(2, "Saida", new Comando() {
			public void executar() {
				String cpf = Prompt.lerLinha(Mensagem.MSG_INFORME_CPF);
				
				if(!cpf.isEmpty()) {
					int idCli = cliente.buscarCPF(cpf);
					
					if(idCli != -1) {
						Cliente cli = cliente.buscarId(idCli);
						Comanda comandaCli = cli.getComanda();
						List<Ingresso> ingressosCli = ingresso.buscarPorCPF(cpf);
						int idIngressoCli = -1;
						
						if(ingressosCli == null) {
							Prompt.linhaEmBranco();
							Prompt.imprimir("Não tem ingressos");
						}
						else {							
							for(Ingresso i : ingressosCli) {
								if(i.getStatus() == Status.ATIVO) {
									idIngressoCli = i.getId();
								}
							}
							
							if(idIngressoCli == -1) {
								Prompt.linhaEmBranco();
								Prompt.imprimir("Não está em uma festa");
							}
							else {
								if(comandaCli.getPago() == true) {
									Ingresso ingressoCli = ingresso.buscarId(idIngressoCli);
									int simNao = Prompt.lerInteiro("Deseja sair? [1] Sim, [2] Não");
									
									switch(simNao) {
										case 1:
											ingressoCli.setStatus(Status.USADO);
											ingresso.atualizar(ingressoCli);
											Prompt.linhaEmBranco();
											Prompt.imprimir("Volte sempre!");
											break;
										case 2:
											Prompt.linhaEmBranco();
											Prompt.imprimir("Boa festa");
											break;
										default:
											Prompt.linhaEmBranco();
											Prompt.imprimir("Inválido");
									}
								}
								else {
									Prompt.linhaEmBranco();
									Prompt.imprimir("Vá pagar antes de sair");
								}
							}
						}
					}
					else {
						Prompt.linhaEmBranco();
						Prompt.imprimir(Mensagem.MSG_CLIENTE_NAO_ENCONTRADO);
					}
				}
				else {
					Prompt.linhaEmBranco();
					Prompt.imprimir("Inválido");
				}
				
				Prompt.linhaEmBranco();
				Prompt.pressionarEnter();
				TelaControleEntrada.getInstance().mostrar();
			}
		});
		
		adicionar(3, "Pagamento", new Comando() {
			public void executar() {
				String cpf = Prompt.lerLinha(Mensagem.MSG_INFORME_CPF);
				
				if(!cpf.isEmpty()) {
					int idCli = cliente.buscarCPF(cpf);
					
					if(idCli != -1) {
						Cliente cli = cliente.buscarId(idCli);
						
						Comanda comandaCli = cli.getComanda();
						
						if(comandaCli.getPago() == false) {
							float total = comandaCli.getBebida() + comandaCli.getComida();
							
							Prompt.imprimir("Total a pagar: R$" + total);
							int simNao = Prompt.lerInteiro("Deseja pagar? [1] Sim, [2] Não");
							
							switch(simNao) {
							case 1:
								comandaCli.setBebida(0);
								comandaCli.setComida(0);
								comandaCli.setPago(true);
								comanda.atualizar(comandaCli);
								Prompt.linhaEmBranco();
								Prompt.imprimir("Tudo pago, volte sempre");
								break;
							case 2:
								Prompt.linhaEmBranco();
								Prompt.imprimir("Boa festa");
								break;
							default:
								Prompt.linhaEmBranco();
								Prompt.imprimir("Inválido");
						}
						}
						else {
							Prompt.linhaEmBranco();
							Prompt.imprimir("Tudo certo");
						}
					}
					else {
						Prompt.linhaEmBranco();
						Prompt.imprimir(Mensagem.MSG_CLIENTE_NAO_ENCONTRADO);
					}
				}
				else {
					Prompt.linhaEmBranco();
					Prompt.imprimir("Inválido");
				}
				
				Prompt.linhaEmBranco();
				Prompt.pressionarEnter();
				TelaControleEntrada.getInstance().mostrar();
			}
		});
		
		adicionar(4, Mensagem.MENU_VOLTAR, new Comando() {
			public void executar() {
				TelaDeFuncionario.getInstance().mostrar();
			}
		});
	}
}
