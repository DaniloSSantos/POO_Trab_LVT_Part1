package Controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Cliente;
import Model.Locacao;
import Model.Veiculo;

public class GerenciaCaixa {
	ArrayList<Locacao> locacoes;
	ArrayList<Veiculo> arrayVeiculos;
	ArrayList<Cliente> arrayclientes;
	
	Scanner inputNum;
	Scanner inputChar;
	
	
	public GerenciaCaixa(ArrayList<Locacao> locacoes, ArrayList<Veiculo> arrayVeiculos,
			ArrayList<Cliente> arrayclientes) {
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
			System.out.println("\n   | SUBMENU Caixa |");
			System.out.println("==================================");
			System.out.println("1 - Pagar locação");
			System.out.println("2 - Total arrecadado");
			System.out.println("3 - Total arrecadado por período");
			System.out.println("4 - Total a receber");
			System.out.println("5 - Voltar ao menu principal");
			System.out.println("============== --0-- =============");
			System.out.println("\n");
			System.out.println("Opção: ");
			String opc = inputChar.nextLine();

			try {
				op = Integer.valueOf(opc).intValue();

				switch (op) {
				case 1:
					System.out.println("*** PAGAR LOCAÇÃO ***");
					pagamentoLocacao();
					break;
				case 2:
					System.out.println("*** TOTAL ARRECADADO ***");
					totalArrecadado();
					break;
				case 3:
					System.out.println("*** TOTAL ARRECADADO POR PERÍODO  ***");
					totalArrecadadoPorPeriodo();
					break;
				case 4:
					System.out.println("*** TOTAL A RECEBER ***");
					totalAReceber();
					break;
				case 5:
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
		} while (op != 5);
	}
	
	public void pagamentoLocacao() {
		int pos, resp;

		GerenciaAgenda gerAg = new GerenciaAgenda();
		
		if (!locacoes.isEmpty()) {

			System.out.println("Veículo(s) locado(s):\n");
			gerAg.relatorioVeiculosLocadosEmDia();
			System.out.println("Qual posição deseja pagar?");
			pos = inputNum.nextInt();

			if (pos >= 0 && pos < locacoes.size()) {
				LocalDate dataInicio = locacoes.get(pos).getDataInicio();
				LocalDate dataPrevistaDevolucao = locacoes.get(pos).getDataPrevistaDevolucao();
				LocalDate dataDevolucao = LocalDate.now();
				double preco = locacoes.get(pos).getPreco();
				double multa = 0;
				double total = 0;
				
				if(dataDevolucao.isAfter(dataPrevistaDevolucao)) {
					Period periodo = Period.between(dataPrevistaDevolucao, dataDevolucao);
					double diasDeAtraso = periodo.getDays();
					System.out.println("ATRASADO");
					System.out.println("Dias de atraso: "+ periodo.getDays());
					
					multa = 0.3;
					total = preco *(1+multa);
					total += Math.pow(total, diasDeAtraso);
					
					System.out.println("Total a pagar: R$"+ total);
					
					
				}else {
					
				}
				
				System.out.println("Total a pagar: R$"+ total);
				
				locacoes.get(pos).setPago(true);

				System.out.println("\nPagamento realizado com sucesso!");

			} else {
				System.out.println("Posição inexistente!");
			}

		} else {
			System.out.println("Não existe veículo locado!");
		}

	}
	
	public void totalArrecadado() {
		
	}
	
	public void totalArrecadadoPorPeriodo() {
		
	}
	
	public void totalAReceber() {
		
	}
	
}