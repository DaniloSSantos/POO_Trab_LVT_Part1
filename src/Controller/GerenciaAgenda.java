package Controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Agenda;
import Model.Caminhao;
import Model.Carro;
import Model.Cliente;
import Model.Fisica;
import Model.Juridica;
import Model.Locacao;
import Model.Veiculo;

public class GerenciaAgenda {
	ArrayList<Locacao> locacoes;
	ArrayList<Veiculo> arrayVeiculos;
	ArrayList<Cliente> arrayclientes;
	
	Scanner inputNum;
	Scanner inputChar;
	
	public GerenciaAgenda(ArrayList<Locacao> locacoes) {
		inputNum = new Scanner(System.in);
		inputChar = new Scanner(System.in);
	}
	
	public GerenciaAgenda() {
		inputNum = new Scanner(System.in);
		inputChar = new Scanner(System.in);
	}
	
	public GerenciaAgenda(ArrayList<Veiculo> arrayVeiculos,
			ArrayList<Cliente> arrayclientes, ArrayList<Locacao> locacoes) {
		super();
		this.locacoes = locacoes;
		this.arrayVeiculos = arrayVeiculos;
		this.arrayclientes = arrayclientes;
		inputNum = new Scanner(System.in);
		inputChar = new Scanner(System.in);
	}
	
	int op = 0;

	public void subMenu() {

		do {
			System.out.println("\n   | SUBMENU Agenda |");
			System.out.println("==================================");
			System.out.println("1 - Agendar");
			System.out.println("2 - Locar");
			System.out.println("3 - Cancelar");
			System.out.println("4 - Alterar");
			System.out.println("5 - Relatório Veículos Agendados");
			System.out.println("6 - Relatório Veículos em Dia");
			System.out.println("7 - Relatório Veículos em Atraso");
			System.out.println("8 - Voltar ao menu principal");
			System.out.println("============== --0-- =============");
			System.out.println("\n");
			System.out.println("Opção: ");
			String opc = inputChar.nextLine();

			try {
				op = Integer.valueOf(opc).intValue();

				switch (op) {
				case 1:
					System.out.println("*** AGENDAR ***");
					agendar();
					break;
				case 2:
					System.out.println("*** LOCAR ***");
					//GerenciaLocacao gl = new GerenciaLocacao(locacoes);
					locar();
					break;
				case 3:
					System.out.println("*** CANCELAR ***");
					cancelar();
					break;
				case 4:
					System.out.println("*** ALTERAR ***");
					alterar();
					break;
				case 5:
					System.out.println("*** RELATÓRIO ***");
					relatorioVeiculosAgendados();
					break;
				case 6:
					System.out.println("*** RELATÓRIO ***");
					relatorioVeiculosLocadosEmDia();
					break;
				case 7:
					System.out.println("*** RELATÓRIO ***");
					relatorioVeiculosLocadosEmAtraso();
					break;
				case 8:
					System.out.println("Voltar ao menu principal");
					break;

				default:
					System.out.println("Opção incorreta!");
				}
			} catch (NumberFormatException n) {
				System.out.println("ATENÇÃO! Digite somente números");
			} catch (Exception e) {
				System.out.println("ATENÇÃO! Tente novamente.");
			}
		} while (op != 8);
	}
	
	
	public void agendar() {
		int posCliente, codCliente = 0, posVeiculo = 0, codVeiculo;
		LocalDate dataInicio, dataPrevistaDevolucao, dataDevolucao = null;
		
		
		GerenciaClientes gerCli = new GerenciaClientes(arrayclientes);
		System.out.println("Clientes cadastrados:\n");
		gerCli.relatorio();
		
		System.out.print("Digite o código do cliente escolhido: ");
		codCliente = inputNum.nextInt();
		
		posCliente = gerCli.buscarClientePorCodigo(codCliente);
		
		GerenciaVeiculos gv = new GerenciaVeiculos(arrayVeiculos);
		System.out.println("Veículos disponíveis: ");
		gv.listarVeiculo();
		
		System.out.print("\nEscolha o veículo \n");
		System.out.print("\nDigite a placa: ");
		String placa = inputChar.nextLine();
		posVeiculo = gv.buscarVeiculoPorPlaca(placa);
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.print("\nformato dd/MM/yyyy");
		System.out.print("\nData de início: ");
		String dataDeInico = inputChar.nextLine();
		dataInicio = LocalDate.parse(dataDeInico, formato);
		System.out.print("\nformato dd/MM/yyyy");
		System.out.print("\nData prevista para devolução: ");
		String dataPrevDevolucao = inputChar.nextLine();
		dataPrevistaDevolucao = LocalDate.parse(dataPrevDevolucao, formato);
		
		System.out.print("\nPreço: ");
		int preco = inputNum.nextInt();
		
		int multa = 0;
		int status = 1; // 0 cancelado | 1 agendado
		
		Veiculo tipoVeiculo = arrayVeiculos.get(posVeiculo);
		if(tipoVeiculo instanceof Carro) {
			Carro car = (Carro) tipoVeiculo;
			ArrayList<Veiculo> veiculosAdd = new ArrayList<>();
			veiculosAdd.add(car);
			Locacao loc = new Locacao(arrayclientes.get(posCliente), veiculosAdd, dataInicio, dataPrevistaDevolucao, dataDevolucao, preco, multa, status);
			locacoes.add(loc);
		}
		
		if(tipoVeiculo instanceof Caminhao) {
			Caminhao cam = (Caminhao) tipoVeiculo;
			ArrayList<Veiculo> veiculosAdd = new ArrayList<>();
			veiculosAdd.add(cam);
			Locacao loc = new Locacao(arrayclientes.get(posCliente), veiculosAdd, dataInicio, dataPrevistaDevolucao, dataDevolucao, preco, multa, status);
			locacoes.add(loc);
		}
		
		
		System.out.println("\n\nAgendamento realizado com sucesso!");
	}
	
	public void locar() {
		int pos= 0;
		
		if (!locacoes.isEmpty()) {
			
			System.out.println("Agendamentos:\n");
			relatorioVeiculosAgendados();
			System.out.println("Qual posição deseja locar?");
			pos = inputNum.nextInt();

			if (pos >= 0 && pos < locacoes.size()) {
				LocalDate dataInicio = LocalDate.now();
				locacoes.get(pos).setDataInicio(dataInicio);				
				locacoes.get(pos).setStatus(2);
				
				System.out.println("\nLocação realizada com sucesso!");

			} else {
				System.out.println("Posição inexistente!");
			}

		} else {
			System.out.println("Não existe agendamento!");
		}
	}
	
	public void cancelar() {
		
		if(locacoes.size() !=0) {
			int pos;
			
			System.out.println("\nAgendamentos:\n");
			relatorioVeiculosAgendados();
			
			System.out.println("Escolha a posição que deseja cancelar:");
			pos = inputNum.nextInt();
			locacoes.get(pos).setStatus(0); // 0 cancelado | 1 agendado
			
			System.out.println("\nAgendamento cancelado com sucesso!");
			
		}
		else {
			System.out.println("\nNão existe agendamentos!");
		}
		
	}
		
	public void alterar() {
		/* “alterar” dá a possibilidade de alteração do período de agendamento; */
		LocalDate dataInicio = null, dataPrevistaDevolucao = null;
		String data;
		
		int pos= 0, resp = 0, resp2 = 0;
		DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		if (!locacoes.isEmpty()) {
			System.out.println("Informações atuais:\n");
			listarNumerado();
			System.out.println("Qual posição deseja alterar?");
			pos = inputNum.nextInt();

			if (pos >= 0 && pos < locacoes.size()) {
								
				System.out.println("\nDigite o número correspondente ao campo que deseja alterar:");
				resp = inputNum.nextInt();
				
				switch (resp) {
				case 1:
					System.out.print("Data de início: ");
					System.out.print("\nformato dd/MM/yyyy\n");
					data = null;
					data = inputChar.nextLine();
					dataInicio = LocalDate.parse(data, formatoBR);
					System.out.println("Alterar? 1.sim | 2.não");
					resp2 = inputNum.nextInt();

					if (resp2 == 1) {
						locacoes.get(pos).setDataInicio(dataInicio);
						System.out.println("OK, alterado com sucesso!");
					} else {
						System.out.println("Certo, não foi alterado");
					}

					break;
				case 2:
					System.out.print("\nData prevista para devolução: ");
					System.out.print("\nformato dd/MM/yyyy\n");
					data = inputChar.nextLine();
					dataPrevistaDevolucao = LocalDate.parse(data, formatoBR);
					System.out.println("Alterar? 1.sim | 2.não");
					resp2 = inputNum.nextInt();

					if (resp2 == 1) {
						locacoes.get(pos).setDataPrevistaDevolucao(dataPrevistaDevolucao);
						System.out.println("OK, alterado com sucesso!");
					} else {
						System.out.println("Certo, não foi alterado");
					}
					break;

				}

			} else {
				System.out.println("Posição inexistente!");
			}

		} else {
			System.out.println("Não existe agendamento!");
		}
		
	}

	public void relatorioVeiculosAgendados() {
		if (locacoes.size() != 0) {
			for (Locacao l : locacoes) {
				
				if(l.getStatus() == 1) { // 0 cancelado | 1 agendado | 2 locado
					imprimir(l);
					System.out.println("\n----x----x----x----x----x-----\n");
				}
			}
		} else {
			System.out.println("Não existe agendamentos!");
		}
	}
	public void relatorioVeiculosLocadosEmDia() {
		if (locacoes.size() != 0) {
			System.out.println("Veículos locados:\n");
			for (Locacao l : locacoes) {
				
				if(l.getStatus() == 2) {
					imprimir(l);
					System.out.println("\n----x----x----x----x----x-----\n");
				}
			}
		} else {
			System.out.println("Atenção! Primeiro agende uma locação.");
		}
	}
	public void relatorioVeiculosLocadosEmAtraso() {
		if (locacoes.size() != 0) {
			for (Locacao l : locacoes) {
				LocalDate dataAgora = LocalDate.now();
				LocalDate dataPrevistaDevolucao = l.getDataPrevistaDevolucao();
				if(l.getStatus() == 2 && dataAgora.isAfter(dataPrevistaDevolucao)) {
					Period diasDeAtraso = Period.between(dataPrevistaDevolucao, dataAgora);
					System.out.println("\nDias de atraso: "+ diasDeAtraso.getDays());
					imprimir(l);
					System.out.println("\n----x----x----x----x----x-----\n");
				}
			}
		} else {
			System.out.println("Atenção! Primeiro agende uma locação.");
		}
	}
	
	
	public void imprimir(Locacao l) {
		DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.println("POSIÇÃO-"+"["+locacoes.indexOf(l)+"]");
		System.out.println("Cliente:------------" + l.getCliente().getNome());
		System.out.println("Veículo(os):--------" + l.getVeiculos().toString());
		System.out.println("Data de início:-----" + l.getDataInicio().format(formatoBR));
		System.out.println("Devolução para:-----" + l.getDataPrevistaDevolucao().format(formatoBR));
		System.out.println("Valor R$:-----------" + l.getPreco());
		if(l.getStatus() == 0) {
			String status = "cancelado";
			System.out.println("Status:-------------" + status);
		}
		if(l.getStatus() == 1) {
			String status = "agendado";
			System.out.println("Status:-------------" + status);
		}
		if(l.getStatus() == 2) {
			String status = "locado";
			System.out.println("Status:-------------" + status);
		}
		
	}
	
	public void  listarNumerado() {
		if (locacoes.size() != 0) {
			for (Locacao l : locacoes) {
				
				if(l.getStatus() == 1) {
					imprimirNumerado(l);
					System.out.println("\n============================\n");
				}
			}
		} else {
			System.out.println("Não existe agendamentos!");
		}
	}
	
	public void imprimirNumerado(Locacao l) {
		DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.println("POSIÇÃO-"+"["+locacoes.indexOf(l)+"]");
		System.out.println("Cliente:------------------------" + l.getCliente().getNome());
		System.out.println("1.Data de início:---------------" + l.getDataInicio().format(formatoBR));
		System.out.println("2.Data prevista para devolução:-" + l.getDataPrevistaDevolucao().format(formatoBR));
		
	}
	
	
}
