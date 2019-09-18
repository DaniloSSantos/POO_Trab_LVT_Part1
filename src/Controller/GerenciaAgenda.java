package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Agenda;

public class GerenciaAgenda {
	
	Scanner inputNum;
	Scanner inputString;
	
	public GerenciaAgenda(ArrayList<Agenda> arrayAgenda) {
		inputNum = new Scanner(System.in);
		inputString = new Scanner(System.in);
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
			String opc = inputString.nextLine();

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
