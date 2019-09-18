package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Agenda;
import Model.Locacao;

public class GerenciaLocacao {
	Scanner inputNum;
	Scanner inputString;
	
	public GerenciaLocacao(ArrayList<Locacao> locacoes) {
		inputNum = new Scanner(System.in);
		inputString = new Scanner(System.in);
	}
	
	int op = 0;

	public void subMenu() {

		do {
			System.out.println("\n   | SUBMENU Locação |");
			System.out.println("==================================");
			System.out.println("1 - x");
			System.out.println("2 - x");
			System.out.println("3 - x");
			System.out.println("4 - x");
			System.out.println("5 - x");
			System.out.println("6 - x");
			System.out.println("7 - x");
			System.out.println("============== --0-- =============");
			System.out.println("\n");
			System.out.println("Opção: ");
			String opc = inputString.nextLine();

			try {
				op = Integer.valueOf(opc).intValue();

				switch (op) {
				case 1:
					System.out.println("*** x ***");
					//
					break;
				case 2:
					System.out.println("*** x ***");
					//
					break;
				case 3:
					System.out.println("*** x ***");
					//
					break;
				case 4:
					System.out.println("*** x ***");
					//
					break;
				case 5:
					System.out.println("*** x ***");
					//
					break;
				case 6:
					System.out.println("*** x ***");
					//
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

}
