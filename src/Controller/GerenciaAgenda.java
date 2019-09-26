package Controller;

import java.time.LocalDate;
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
		
		System.out.println("Digite os dados a seguir");

		gerCli.relatorio();
		System.out.print("Código do cliente: ");
		codCliente = inputNum.nextInt();
		
		GerenciaClientes gc = new GerenciaClientes(arrayclientes);
		posCliente = gc.buscarClientePorCodigo(codCliente);
		
		GerenciaVeiculos gv = new GerenciaVeiculos(arrayVeiculos);
		gv.relatorio();
		
		System.out.print("Escolha o veículo ");
		System.out.print("Digite a placa: ");
		String placa = inputChar.nextLine();
		gv.buscarVeiculoPorPlaca(placa);
		
		System.out.print("Data de início: ");
		dataInicio = LocalDate.now();
		System.out.print("Data prevista para devolução: ");
		dataPrevistaDevolucao = LocalDate.now();
		System.out.print("Preço: ");
		int preco = 0;
		int multa = 0;
		int status = 0;
		
		Veiculo tipoVeiculo = arrayVeiculos.get(posVeiculo);
		if(tipoVeiculo instanceof Carro) {
			Carro car = (Carro) tipoVeiculo;
			ArrayList<Veiculo> veiculosAdd = new ArrayList<>();
			veiculosAdd.add(car);
			Locacao loc = new Locacao(arrayclientes.get(posCliente), veiculosAdd, dataInicio, dataPrevistaDevolucao, dataDevolucao, preco, multa, status);
			locacoes.add(loc);
		}
		
		if(tipoVeiculo instanceof Caminhao) {
			Caminhao car = (Caminhao) tipoVeiculo;
			ArrayList<Veiculo> veiculosAdd = new ArrayList<>();
			veiculosAdd.add(car);
			Locacao loc = new Locacao(arrayclientes.get(posCliente), veiculosAdd, dataInicio, dataPrevistaDevolucao, dataDevolucao, preco, multa, status);
			locacoes.add(loc);
		}
		
		
		System.out.println("Agendamento realizado com sucesso!");
	}
	public void cancelar() {
		
	}
	public void alterar() {
		
	}
	public void relatorioVeiculosAgendados() {
		
	}
	public void relatorioVeiculosLocadosEmDia() {
		
	}
	public void relatorioVeiculosLocadosEmAtraso() {
		
	}

}
