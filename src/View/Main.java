package View;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Agenda;
import Model.Cliente;
import Model.Locacao;
import Model.Veiculo;
import Controller.GerenciaAgenda;
import Controller.GerenciaClientes;
import Controller.GerenciaLocacao;
import Controller.GerenciaVeiculos;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Veiculo> arrayVeiculos = new ArrayList<>();
		GerenciaVeiculos gerVeic = new GerenciaVeiculos(arrayVeiculos);
		
		ArrayList<Cliente> arrayclientes = new ArrayList<>();
		GerenciaClientes gerCli = new GerenciaClientes(arrayclientes);
		
		ArrayList<Locacao> locacoes = new ArrayList<>();
		GerenciaLocacao gerLoc = new GerenciaLocacao(locacoes);
		
		ArrayList<Agenda> arrayAgenda = new ArrayList<>();
		GerenciaAgenda gerAg = new GerenciaAgenda(arrayVeiculos, arrayclientes, locacoes, arrayAgenda);

		Scanner input = new Scanner(System.in);

		int opc;
		do {
			System.out.println("\n");
			System.out.println("==| MENU PRINCIPAL |===============");
			System.out.println("1 - Agenda");
			System.out.println("2 - Cliente");
			System.out.println("3 - Veículo");
			System.out.println("4 - Caixa");
			System.out.println("5 - Locação");
			System.out.println("6 - Sair...");

			System.out.println("============== --0-- ==============");
			System.out.print("Opção: ");
			opc = input.nextInt();

			switch (opc) {
			case 1:
				gerAg.subMenu();
				break;
			case 2:
				gerCli.subMenu();
				break;
			case 3:
				gerVeic.subMenu();
				break;
			case 4:
				//
				break;
			case 5:
				//gerLoc.subMenu();
				break;
			case 6:
				System.out.println("Saindo do sistema...");
				break;
			default:
				System.out.println("Opção incorreta!");
			}
		} while (opc != 6);
		input.close();

	}

}
