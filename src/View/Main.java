package View;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Agenda;
import Model.Carro;
import Model.Cliente;
import Model.Juridica;
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
		
		//---------------------------------------------------------//
		for(int i=0; i <=4; i++){//carro
			String dado= Integer.toString(i);
            Carro carro = new Carro(dado,dado,i,i,dado,i,i);
            
            arrayVeiculos.add(carro);
        }
		for(int i=0; i <=4; i++){//cliente
			String dado= Integer.toString(i);
            Juridica juri = new Juridica(dado,dado,dado,i,dado);
            
            arrayclientes.add(juri);
        }
		
		//---------------------------------------------------------//

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
				//caixa
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
