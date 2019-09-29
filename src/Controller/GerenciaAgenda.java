package Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Agenda;
import Model.Caminhao;
import Model.Carro;
import Model.Cliente;
import Model.Locacao;
import Model.Veiculo;

public class GerenciaAgenda {
	ArrayList<Locacao> locacoes;
	ArrayList<Veiculo> arrayVeiculos;
	ArrayList<Cliente> arrayclientes;
	
	Scanner inputNum;
	Scanner inputChar;
	
	public GerenciaAgenda(ArrayList<Agenda> arrayAgenda) {
		inputNum = new Scanner(System.in);
		inputChar = new Scanner(System.in);
	}
	
	public GerenciaAgenda(ArrayList<Veiculo> arrayVeiculos,
			ArrayList<Cliente> arrayclientes, ArrayList<Locacao> locacoes, ArrayList<Agenda> arrayAgenda) {
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
			System.out.println("2 - Cancelar");
			System.out.println("3 - Alterar");
			System.out.println("4 - Relatório Veículos Agendados");
			System.out.println("5 - Relatório Veículos em Dia");
			System.out.println("6 - Relatório Veículos em Atraso");
			System.out.println("7 - Voltar ao menu principal");
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
					System.out.println("*** CANCELAR ***");
					cancelar();
					break;
				case 3:
					System.out.println("*** ALTERAR ***");
					alterar();
					break;
				case 4:
					System.out.println("*** RELATÓRIO ***");
					relatorioVeiculosAgendados();
					break;
				case 5:
					System.out.println("*** RELATÓRIO ***");
					relatorioVeiculosLocadosEmDia();
					break;
				case 6:
					System.out.println("*** RELATÓRIO ***");
					relatorioVeiculosLocadosEmAtraso();
					break;
				case 7:
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
		} while (op != 7);
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
		
		System.out.print("\nData de início: ");
		dataInicio = LocalDate.now();
		System.out.print("\nData prevista para devolução: ");
		dataPrevistaDevolucao = LocalDate.now();
		System.out.print("\nPreço: ");
		int preco = 0;
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
		
		
		System.out.println("\nAgendamento realizado com sucesso!");
	}
	
	/*
	 * cancela um agendamento que tenha sido realizado. Porém, o agendamento não
	 * deve ser excluído da base de dados;
	 */
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

	/* “alterar” dá a possibilidade de alteração do período de agendamento; */
	public void alterar() {
		
	}

	public void relatorioVeiculosAgendados() {
		if (locacoes.size() != 0) {
			for (Locacao l : locacoes) {
				
				if(l.getStatus() == 1) {
					imprimir(l);
					System.out.println("\n----x----x----x----x----x-----\n");
				}
			}
		} else {
			System.out.println("Não existe agendamentos!");
		}
	}
	public void relatorioVeiculosLocadosEmDia() {
		
	}
	public void relatorioVeiculosLocadosEmAtraso() {
		
	}
	
	public void imprimirAgendado(Locacao loc) {
		
		
	}
	
	public void imprimir(Locacao l) {
		DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.println("POSIÇÃO-"+"["+locacoes.indexOf(l)+"]");
		System.out.println("Cliente:------------" + l.getCliente().getNome());
		System.out.println("Veículo(os):--------" + l.getVeiculos().toString());
		System.out.println("Devolução para:-----" + l.getDataPrevistaDevolucao().format(formatoBR));
		System.out.println("Valor R$:-----------" + l.getPreco());
		System.out.println("Status:-------------" + l.getStatus());

		
	}

}
