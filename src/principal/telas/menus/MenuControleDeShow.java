package principal.telas.menus;

import java.util.List;

import principal.controles.ArtistaController;
import principal.controles.ShowController;
import principal.modelos.Artista;
import principal.modelos.Show;
import principal.modelos.Pessoa.Sexo;
import principal.telas.Comando;
import principal.telas.TelaControleDeShow;
import principal.telas.TelaDeGerente;
import principal.util.Mensagem;
import principal.util.Prompt;

public class MenuControleDeShow extends Menu {
	private ShowController showControle = ShowController.getInstance();
	private ArtistaController artistaControle = ArtistaController.getInstance();

	public MenuControleDeShow(){
		adicionar(1, "Adicionar show", new Comando() {
			public void executar() {
				Prompt.linhaEmBranco();
				Prompt.imprimir("ADICIONAR SHOW");
				
				String nome = Prompt.lerLinha("Informe nome:");
				int quantidadeIngresso = Prompt.lerInteiro("Informe quantidade de ingressos:");
				float valorMasc = (float)Prompt.lerDecimal("Valor ingresso masculino:");
				float valorFemi = (float)Prompt.lerDecimal("Valor ingresso feminino:");
				int ano = Prompt.lerInteiro("Ano:");
				int mes = Prompt.lerInteiro("Mes:");
				int dia = Prompt.lerInteiro("Dia:");
				
				if(!nome.isEmpty() && quantidadeIngresso > 0 && valorMasc >= 0 && valorFemi >= 0 && (dia < 32 || dia > 0) && (mes <= 12 || mes > 0) && ano >= 2023) {
					Show show = new Show(nome, quantidadeIngresso, valorMasc, valorFemi, ano, mes, dia);
					
					showControle.adicionar(show);
					
					Prompt.linhaEmBranco();
					Prompt.imprimir("Adicionado com sucesso");
				}
				else {
					Prompt.linhaEmBranco();
					Prompt.imprimir("Inválido");
				}
				
				Prompt.linhaEmBranco();
				Prompt.pressionarEnter();
				TelaControleDeShow.getInstance().mostrar();;
			}
		});
		
		adicionar(2, "Alterar show", new Comando() {
			public void executar() {
				Prompt.linhaEmBranco();
				Prompt.imprimir("ALTERAR SHOW");
				
				int idShow = Prompt.lerInteiro("Digite o ID do show:");
				
				if(idShow > 0) {
					Show show = showControle.buscarId(idShow);
					
					if(show != null) {
						String nome = Prompt.lerLinha("Informe nome:");
						int quantidadeIngresso = Prompt.lerInteiro("Informe quantidade de ingressos:");
						float valorMasc = (float)Prompt.lerDecimal("Valor ingresso masculino:");
						float valorFemi = (float)Prompt.lerDecimal("Valor ingresso feminino:");
						int ano = Prompt.lerInteiro("Ano:");
						int mes = Prompt.lerInteiro("Mes:");
						int dia = Prompt.lerInteiro("Dia:");
						
						if(!nome.isEmpty() && quantidadeIngresso > 0 && valorMasc >= 0 && valorFemi >= 0 && (dia < 32 || dia > 0) && (mes <= 12 || mes > 0) && ano >= 2023) {
							show.setNomeShow(nome);
							show.setQuantIngressos(quantidadeIngresso);
							show.setValorMasc(valorMasc);
							show.setValorFem(valorFemi);
							String diaShow = dia + "/" + mes + "/" + ano;
							show.setDiaEvento(diaShow);
							
							showControle.atualizar(show);
							
							Prompt.linhaEmBranco();
							Prompt.imprimir("Alterado com sucesso");
						}
						else {
							Prompt.linhaEmBranco();
							Prompt.imprimir("Inválido");
						}
					}
					else {
						Prompt.linhaEmBranco();
						Prompt.imprimir("Não encontrado");
					}
				}
				else {
					Prompt.linhaEmBranco();
					Prompt.imprimir("Id inválido");
				}
				
				Prompt.linhaEmBranco();
				Prompt.pressionarEnter();
				TelaControleDeShow.getInstance().mostrar();
			}
		});
		
		adicionar(3, "Excluir show", new Comando() {
			public void executar() {
				Prompt.linhaEmBranco();
				Prompt.imprimir("EXCLUIR SHOW");
				
				int idShow = Prompt.lerInteiro("Digite o ID do show:");
				
				if(idShow > 0) {
					Show show = showControle.buscarId(idShow);
					
					if(show != null) {
						showControle.excluir(idShow);
						
						Prompt.linhaEmBranco();
						Prompt.imprimir("Excluido com sucesso");
					}
					else {
						Prompt.linhaEmBranco();
						Prompt.imprimir("Não encontrado");
					}
				}
				else {
					Prompt.linhaEmBranco();
					Prompt.imprimir("Id inválido");
				}
				
				Prompt.linhaEmBranco();
				Prompt.pressionarEnter();
				TelaControleDeShow.getInstance().mostrar();
			}
		});
		
		adicionar(4, "Adicionar artista", new Comando() {
			public void executar() {
				Prompt.linhaEmBranco();
				Prompt.imprimir("ADICIONAR ARTISTA");
				
				int idShow = Prompt.lerInteiro("Digite o ID do show:");
				
				if(idShow > 0) {
					Show show = showControle.buscarId(idShow);
					
					if(show != null) {
						List<Artista> listaArtista = show.getArtistas();
						
						String nome = Prompt.lerLinha(Mensagem.MSG_INFORME_NOME);
						String cpf = Prompt.lerLinha(Mensagem.MSG_INFORME_CPF);
						int idade = Prompt.lerInteiro("Idade? ");
						int opcao = Prompt.lerInteiro("Sexo: [1]Homem, [2]Mulher");
						
						Sexo sexo;
						
						if(opcao == 1) {
							sexo = Sexo.MASCULINO;
						}
						else {
							sexo = Sexo.FEMININO;
						}
						
						float cache = (float)Prompt.lerDecimal("Cache:");
						
						if(!nome.isEmpty() && !cpf.isEmpty() && cache >= 0) {					
							Artista artista = new Artista(nome, cpf, idade, sexo, cache);
							
							listaArtista.add(artista);
							
							show.setArtistas(listaArtista);
							
							showControle.atualizar(show);
							
							Prompt.linhaEmBranco();
							Prompt.imprimir("Artista adicionado com sucesso!");
						}
						else {
							Prompt.linhaEmBranco();
							Prompt.imprimir("Campo vazio ou cache negativo");
						}
					}
					else {
						Prompt.linhaEmBranco();
						Prompt.imprimir("Não encontrado");
					}
				}
				else {
					Prompt.linhaEmBranco();
					Prompt.imprimir("Id inválido");
				}
				
				Prompt.linhaEmBranco();
				Prompt.pressionarEnter();
				TelaControleDeShow.getInstance().mostrar();
			}
		});
		
		adicionar(5, "Editar artista", new Comando() {
			public void executar() {
				Prompt.linhaEmBranco();
				Prompt.imprimir("EDITAR ARTISTA");
				
				int idShow = Prompt.lerInteiro("Digite o ID do show:");
				
				if(idShow > 0) {
					Show show = showControle.buscarId(idShow);
					
					if(show != null) {
						List<Artista> listaArtista = show.getArtistas();
						
						if(!listaArtista.isEmpty()) {
							for(Artista artista : listaArtista) {
								Prompt.imprimir("[" + artista.getId() + "] " + artista.getNome());
							}
							
							int idArtista = Prompt.lerInteiro("Digite o ID do artista que deseja alterar:");
							
							if(idArtista > 0) {
								Artista artista = artistaControle.buscarId(idArtista);
								
								if(artista != null) {
									String nome = Prompt.lerLinha(Mensagem.MSG_INFORME_NOME);
									String cpf = Prompt.lerLinha(Mensagem.MSG_INFORME_CPF);
									int idade = Prompt.lerInteiro("Idade? ");
									int opcao = Prompt.lerInteiro("Sexo: [1]Homem, [2]Mulher");
									
									Sexo sexo;
									
									if(opcao == 1) {
										sexo = Sexo.MASCULINO;
									}
									else {
										sexo = Sexo.FEMININO;
									}
									
									float cache = (float)Prompt.lerDecimal("Cache:");
									
									if(!nome.isEmpty() && !cpf.isEmpty() && cache >= 0) {					
//										listaArtista.remove(artista);
										
										artista.setNome(nome);
										artista.setCache(cache);
										artista.setCpf(cpf);
										artista.setIdade(idade);
										artista.setSexo(sexo);
										
										artistaControle.atualizar(artista);
										
										showControle.atualizar(show);
										
										Prompt.linhaEmBranco();
										Prompt.imprimir("Artista atualizado com sucesso!");
									}
									else {
										Prompt.linhaEmBranco();
										Prompt.imprimir("Campo vazio ou cache negativo");
									}
								}
								else {
									Prompt.linhaEmBranco();
									Prompt.imprimir("Não encontrado");
								}
							}
							else {
								Prompt.linhaEmBranco();
								Prompt.imprimir("ID inválido");
							}
						}
						else {
							Prompt.linhaEmBranco();
							Prompt.imprimir("Nenhun artista neste show ainda!");
						}
					}
					else {
						Prompt.linhaEmBranco();
						Prompt.imprimir("Não encontrado");
					}
				}
				else {
					Prompt.linhaEmBranco();
					Prompt.imprimir("Id inválido");
				}
				
				Prompt.linhaEmBranco();
				Prompt.pressionarEnter();
				TelaControleDeShow.getInstance().mostrar();
			}
		});
		
		adicionar(6, "Excluir artista", new Comando() {
			public void executar() {
				Prompt.linhaEmBranco();
				Prompt.imprimir("EXCLUIR ARTISTA");
				
				int idShow = Prompt.lerInteiro("Digite o ID do show:");
				
				if(idShow > 0) {
					Show show = showControle.buscarId(idShow);
					
					if(show != null) {
						List<Artista> listaArtista = show.getArtistas();
						
						if(!listaArtista.isEmpty()) {
							for(Artista artista : listaArtista) {
								Prompt.imprimir("[" + artista.getId() + "] " + artista.getNome());
							}
							
							int idArtista = Prompt.lerInteiro("Digite o ID do artista que deseja alterar:");
							
							if(idArtista > 0) {
								Artista artista = artistaControle.buscarId(idArtista);
								
								if(artista != null) {
									listaArtista.remove(artista);
									
									show.setArtistas(listaArtista);
									
									showControle.atualizar(show);
									
									Prompt.linhaEmBranco();
									Prompt.imprimir("Excluido com sucesso");
								}
								else {
									Prompt.linhaEmBranco();
									Prompt.imprimir("Não encontrado");
								}
							}
							else {
								Prompt.linhaEmBranco();
								Prompt.imprimir("ID inválido");
							}
						}
					}
					else {
						Prompt.linhaEmBranco();
						Prompt.imprimir("Não encontrado");
					}
				}
				else {
					Prompt.linhaEmBranco();
					Prompt.imprimir("Id inválido");
				}
				
				Prompt.linhaEmBranco();
				Prompt.pressionarEnter();
				TelaControleDeShow.getInstance().mostrar();
			}
		});
		
		adicionar(7, Mensagem.MENU_VOLTAR, new Comando() {
			public void executar() {
				TelaDeGerente.getInstance().mostrar();
			}
		});
	}
}
