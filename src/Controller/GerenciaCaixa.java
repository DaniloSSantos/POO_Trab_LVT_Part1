package Controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Cliente;
import Model.Locacao;
import Model.Veiculo;

public class GerenciaCaixa {
	ArrayList<Veiculo> arrayVeiculos;
	ArrayList<Cliente> arrayclientes;
	ArrayList<Locacao> locacoes;

	Scanner inputNum;
	Scanner inputChar;

	public GerenciaCaixa(ArrayList<Veiculo> arrayVeiculos, ArrayList<Cliente> arrayclientes,
			ArrayList<Locacao> locacoes) {
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
				e.printStackTrace();
			}
		} while (op != 5);
	}

	public void pagamentoLocacao() {
		int pos, resp;
		double multa = 0;

		GerenciaAgenda gerAg = new GerenciaAgenda(arrayVeiculos, arrayclientes, locacoes);

		if (!locacoes.isEmpty()) {

			gerAg.relatorioVeiculosLocadosEmDia();
			System.out.println("Qual posição deseja pagar?");
			pos = inputNum.nextInt();

			if (pos >= 0 && pos < locacoes.size()) {

				LocalDate dataPrevistaDevolucao = locacoes.get(pos).getDataPrevistaDevolucao();
				LocalDate dataDevolucao = LocalDate.now();
				double preco = locacoes.get(pos).getPreco();
				double total = 0;

				if (dataDevolucao.isAfter(dataPrevistaDevolucao)) {
					Period periodo = Period.between(dataPrevistaDevolucao, dataDevolucao);
					System.out.println("ATRASADO");
					System.out.println("Dias de atraso: " + periodo.getDays());

					multa = 0.3;
					total = calcularJuros(periodo.getDays(), preco, multa);

					System.out.println("Total a pagar: R$" + total);
					System.out.println("\nConfirmar pagamento?\n1.Sim | 2.Não");
					resp = inputNum.nextInt();
					if (resp == 1) {
						locacoes.get(pos).setStatus(3);
						locacoes.get(pos).setMulta(total);
						locacoes.get(pos).setDataDevolucao(dataDevolucao);
						System.out.println("\nPagamento realizado com sucesso!");
					} else {
						System.out.println("\nPagamento não realizado!");
					}

				} else {
					System.out.println("EM DIAS");
					total = preco;

					System.out.println("Total a pagar: R$" + total);
					System.out.println("\nConfirmar pagamento?\n1.Sim | 2.Não");
					resp = inputNum.nextInt();

					if (resp == 1) {
						locacoes.get(pos).setStatus(3);
						locacoes.get(pos).setMulta(0);
						locacoes.get(pos).setDataDevolucao(dataDevolucao);
						System.out.println("\nPagamento realizado com sucesso!");
					} else {
						System.out.println("\nPagamento não realizado!");
					}
				}

			} else {
				System.out.println("Posição inexistente!");
			}

		} else {
			System.out.println("Não existe veículo locado!");
		}

	}

	public void totalArrecadado() {
		if (locacoes.size() != 0) {
			double total = 0;

			for (Locacao l : locacoes) {
				if (l.getStatus() == 3) {
					imprimir(l);
				}

				System.out.println("------------------------------");
			}
			for (Locacao l : locacoes) {
				total += calcularTotalArrecadado(l);
			}
			System.out.println("\nTotal arrecadado = " + total);
		}
	}

	public void totalArrecadadoPorPeriodo() {
		LocalDate dataInicial, dataFinal, dataDevolucao;

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("Período a ser calculado");
		System.out.print("\nformato dd/MM/yyyy\n");
		System.out.println("Digite a data inicial:");
		String dInicial = inputChar.nextLine();
		dataInicial = LocalDate.parse(dInicial, formato);
		System.out.println("Digite a data final:");
		String dFinal = inputChar.nextLine();
		dataFinal = LocalDate.parse(dFinal, formato);

		dataInicial = dataInicial.minusDays(1);
		dataFinal = dataFinal.plusDays(1);

		double total = 0;

		for (Locacao l : locacoes) {
			dataDevolucao = l.getDataDevolucao();
			if (l.getStatus() == 3) {
				imprimir(l);
				if (dataDevolucao.isAfter(dataInicial) && dataDevolucao.isBefore(dataFinal))
					;
			}
			System.out.println("------------------------------");
		}
		for (Locacao l : locacoes) {
			dataDevolucao = l.getDataDevolucao();
			if (l.getStatus() == 3) {

				if (dataDevolucao.isAfter(dataInicial) && dataDevolucao.isBefore(dataFinal))
					;
				total += calcularTotalArrecadado(l);
			}

		}
		System.out.println("\nTotal arrecadado = " + total);

	}

	public void totalAReceber() {

		if (locacoes.size() != 0) {
			double total = 0, juros = 0;
			LocalDate hoje = LocalDate.now();

			for (Locacao l : locacoes) {
				LocalDate dataPrevistaDevolucao = l.getDataPrevistaDevolucao();

				Period periodo = Period.between(dataPrevistaDevolucao, hoje);

				if (atrasado(l) && periodo.getDays() > 0) {

					juros = 0.3;
					l.setMulta(juros);

					System.out.println("Dias de atraso: " + periodo.getDays());

					imprimirAreceber(l);

				} else if (l.getStatus() == 2) {

					imprimirAreceber(l);
				}

				System.out.println("------------------------------");
			}

			total = calcularTotalAreceber();
			System.out.println("\nTotal a receber = " + total);

		}
	}

	public static double calcularJuros(int dias, double preco, double juros) {
		double total = 0;
		total = preco * Math.pow(1 + juros / 100, dias);
		return total;
	}

	public static void calcularJurosEmostrar(int dias, double preco, double juros) {
		double total = 0;
		double parc;
		int aux;

		for (aux = 1; aux <= dias; aux++) {
			total = preco * Math.pow(1 + juros / 100, dias);
			parc = total;
			System.out.println("dia " + aux + ": R$" + parc);
		}

		System.out.println("\n Total a pagar= " + total);
		System.out.println("\n\n");
	}

	public void imprimir(Locacao l) {

		System.out.println("POSIÇÃO-" + "[" + locacoes.indexOf(l) + "]");
		System.out.println("Cliente:--------" + l.getCliente().getNome());

		if (l.getMulta() == 0) {
			System.out.println("Valor pago:-----" + l.getPreco());

		} else {
			System.out.println("Valor + Multa:--" + l.getMulta());
		}

	}

	public void imprimirAreceber(Locacao l) {

		if (l.getStatus() == 2) {
			System.out.println("POSIÇÃO-->" + "[" + locacoes.indexOf(l) + "]");
			System.out.println("Cliente:--------" + l.getCliente().getNome());

			if (l.getMulta() == 0) {
				System.out.println("Valor:----------" + l.getPreco());

			} else {
				System.out.println("Multa:--" + l.getMulta());
			}

		}

	}

	public double calcularTotalArrecadado(Locacao l) {
		double total = 0;
		if (l.getStatus() == 3) {
			if (l.getMulta() == 0) {
				total = l.getPreco();
			} else {
				total = l.getMulta();
			}

		}

		return total;
	}

	public double calcularTotalAreceber() {
		double total = 0, calc = 0, preco = 0, juros = 0;
		LocalDate hoje = LocalDate.now();

		for (Locacao l : locacoes) {
			LocalDate dataPrevistaDevolucao = l.getDataPrevistaDevolucao();
			juros = hoje.isAfter(dataPrevistaDevolucao) ? 0.3 : 0;
			l.setMulta(juros);

			if (l.getStatus() == 2) {
				if (l.getMulta() == 0) {
					total = l.getPreco();
				} else {
					preco = l.getPreco();
					Period periodo = Period.between(dataPrevistaDevolucao, hoje);
					int dias = periodo.getDays();
					total = calcularJuros(dias, preco, juros);
				}

			}
			calc += total;
		}

		return calc;
	}

	public boolean atrasado(Locacao l) {
		boolean atrasou;
		LocalDate hoje = LocalDate.now();
		LocalDate dataPrevistaDevolucao = l.getDataPrevistaDevolucao();

		if (hoje.isAfter(dataPrevistaDevolucao) && l.getStatus() == 2) {
			atrasou = true;
			return atrasou;
		} else {
			atrasou = true;
			return atrasou;
		}

	}

	public int verificaSeExiteLocacaoApagar(ArrayList<Locacao> loc) {
		int compara = 0;

		for (Locacao l : loc) {
			compara = l.getStatus();

			if (l.getStatus() == 2) {

				break;
			}

		}

		if (compara == 2) {
			return 2;
		} else {
			return -1;
		}

	}

}
