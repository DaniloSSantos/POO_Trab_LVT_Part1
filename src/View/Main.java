package View;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Cliente;
import Model.Locacao;
import Model.Veiculo;
import Controller.GerenciaAgenda;
import Controller.GerenciaCaixa;
import Controller.GerenciaClientes;
import Controller.GerenciaVeiculos;

public class Main {

	public static void main(String[] args) {

		ArrayList<Veiculo> arrayVeiculos = new ArrayList<>();
		GerenciaVeiculos gerVeic = new GerenciaVeiculos(arrayVeiculos);

		ArrayList<Cliente> arrayclientes = new ArrayList<>();
		GerenciaClientes gerCli = new GerenciaClientes(arrayclientes);

		ArrayList<Locacao> locacoes = new ArrayList<>();

		GerenciaAgenda gerAg = new GerenciaAgenda(arrayVeiculos, arrayclientes, locacoes);

		GerenciaCaixa gerCa = new GerenciaCaixa(arrayVeiculos, arrayclientes, locacoes);

		Scanner input = new Scanner(System.in);

		int opc;
		do {
			System.out.println("\n");
			System.out.println("==| MENU PRINCIPAL |===============");
			System.out.println("1 - Caixa");
			System.out.println("2 - Cliente");
			System.out.println("3 - Veículo");
			System.out.println("4 - Locação");
			System.out.println("5 - Sair...");

			System.out.println("============== --0-- ==============");
			System.out.print("Opção: ");
			opc = input.nextInt();

			switch (opc) {
			case 1:
				gerCa.subMenu();
				break;
			case 2:
				gerCli.subMenu();
				break;
			case 3:
				gerVeic.subMenu();
				break;
			case 4:
				gerAg.subMenu();
				break;
			case 5:
				System.out.println("Saindo do sistema...");
				break;
			default:
				System.out.println("Opção incorreta!");
			}
		} while (opc != 5);
		input.close();

	}

}
