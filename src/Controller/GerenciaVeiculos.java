package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Caminhao;
import Model.Carro;
import Model.Veiculo;

public class GerenciaVeiculos {
	ArrayList<Veiculo> arrayVeiculos;

	Scanner inputNum;
	Scanner inputChar;

	public GerenciaVeiculos(ArrayList<Veiculo> arrayVeiculos) {
		super();
		this.arrayVeiculos = arrayVeiculos;
		inputNum = new Scanner(System.in);
		inputChar = new Scanner(System.in);
	}

	int op = 0;
	public void subMenu() {
		do {
			System.out.println("\n   | SUBMENU Veículos |");
			System.out.println("==================================");
			System.out.println("1 - Cadastro");
			System.out.println("2 - Alteração");
			System.out.println("3 - Exclusão");
			System.out.println("4 - Consulta");
			System.out.println("5 - Relatório");
			System.out.println("6 - Gerar veículos");
			System.out.println("7 - Voltar ao menu principal");
			System.out.println("============== --0-- =============");
			System.out.println("\n");
			System.out.println("Opção: ");
			op = inputNum.nextInt();

			switch (op) {
			case 1:
				System.out.println("*** CADASTRAR VEÍCULO ***");
				incluir();
				break;
			case 2:
				System.out.println("*** ALTERAR VEÍCULO ***");
				alterar();
				break;
			case 3:
				System.out.println("*** EXCLUIR VEÍCULO ***");
				excluir();
				break;
			case 4:
				System.out.println("*** CONSULTAR VEÍCULO ***");
				//consultar();
				break;
			case 5:
				System.out.println("*** RELATÓRIO VEÍCULO ***");
				//relatorio();
				break;
			case 6:
				System.out.println("Voltar ao menu principal");
				break;

			default:
				System.out.println("Opção incorreta!");
			}
		} while (op != 6);
	}
	
	public void incluir() {
		String marca, modelo, placa;
		int anoFabricacao, anoModelo;
		
		int op;
		do {
			System.out.println("-------------");
			System.out.println("1 - Carro    |");
			System.out.println("2 - Caminhão |");
			System.out.println("-------------\n");
			System.out.println("Opção: ");
			op = inputNum.nextInt();
			
			if((op < 1) || (op >= 3)) {
				System.out.println("Opção inexistente!");
			}

		} while ((op < 1) || (op > 3));
		
		System.out.println("Digite os dados a seguir");
		
		System.out.print("Marca: ");
		marca = inputChar.nextLine();
		System.out.print("\nModelo: ");
		modelo = inputChar.nextLine();
		System.out.print("\nAno de Fabricação: ");
		anoFabricacao = inputNum.nextInt();
		System.out.print("\nAno Modelo: ");
		anoModelo = inputNum.nextInt();
		System.out.print("\nPlaca: ");
		placa = inputChar.nextLine();
		
		
		//tipoVeiculo = op;
		
		if (op == 1) {
			int capacidadePassageiros;
			int quantidadePortas = 0;
			System.out.print("\nCapacidade de passageiros: ");
			capacidadePassageiros = inputNum.nextInt();
			System.out.print("\nQuantidade de portas: ");
			capacidadePassageiros = inputNum.nextInt();
			
			Carro c = new Carro(marca, modelo, anoFabricacao, anoModelo, placa, capacidadePassageiros, quantidadePortas);
			arrayVeiculos.add(c);
			System.out.println("\nCarro cadastrado com sucesso!");
		}
		if (op == 2) {
			Double capacidadeCarga;
			int numeroDeEixos;
			System.out.print("\nCapacidade de Carga: ");
			capacidadeCarga = inputNum.nextDouble();
			System.out.print("\nNúmero de eixos: ");
			numeroDeEixos = inputNum.nextInt();
			
			Caminhao cam = new Caminhao(marca, modelo, anoFabricacao, anoModelo, placa, capacidadeCarga, numeroDeEixos);
			arrayVeiculos.add(cam);
			System.out.println("\nCarro cadastrado com sucesso!");
		}


	}
	
	public void alterar() {
		String marca, modelo, placa;
		int anoFabricacao, anoModelo;
		int pos, resp, resp2;
		
		if (!arrayVeiculos.isEmpty()) {
			System.out.println("Qual posição deseja alterar?");
			pos = inputNum.nextInt();

			if (pos >= 0 && pos <= arrayVeiculos.size()) {
				System.out.println("Informações atuais:\n");
				//imprimir(arrayVeiculos.get(pos));
				System.out.println("Digite o número correspondente ao campo que deseja alterar:\n");
				resp = inputNum.nextInt();

				switch (resp) {
				case 1:
					System.out.print("Marca: ");
					marca = inputChar.nextLine();
					System.out.println("Alterar? 1.sim | 2.não");
					resp2 = inputNum.nextInt();

					if (resp2 == 1) {
						arrayVeiculos.get(pos).setMarca(marca);
						System.out.println("OK, alteado com sucesso!");
					} else {
						System.out.println("Certo, não foi alteado");
					}

					break;
				case 2:
					System.out.print("\nModelo: ");
					modelo = inputChar.nextLine();
					System.out.println("Alterar? 1.sim | 2.não");
					resp2 = inputNum.nextInt();

					if (resp2 == 1) {
						arrayVeiculos.get(pos).setModelo(modelo);
						System.out.println("OK, alteado com sucesso!");
					} else {
						System.out.println("Certo, não foi alteado");
					}
					break;
				case 3:
					System.out.print("\nAno de Fabricação: ");
					anoFabricacao = inputNum.nextInt();

					System.out.println("Alterar? 1.sim | 2.não");
					resp2 = inputNum.nextInt();

					if (resp2 == 1) {
						arrayVeiculos.get(pos).setAnoFabricacao(anoFabricacao);
						System.out.println("OK, alteado com sucesso!");
					} else {
						System.out.println("Certo, não foi alteado");
					}

					break;
				case 4:
					System.out.print("\nAno Modelo: ");
					anoModelo = inputNum.nextInt();

					System.out.println("Alterar? 1.sim | 2.não");
					resp2 = inputNum.nextInt();

					if (resp2 == 1) {
						arrayVeiculos.get(pos).setAnoModelo(anoModelo);
						System.out.println("OK, alteado com sucesso!");
					} else {
						System.out.println("Certo, não foi alteado");
					}

					break;

				case 5:
					System.out.print("\nPlaca: ");
					placa = inputChar.nextLine();

					System.out.println("Alterar? 1.sim | 2.não");
					resp2 = inputNum.nextInt();

					if (resp2 == 1) {
						arrayVeiculos.get(pos).setPlaca(placa);
						System.out.println("OK, alteado com sucesso!");

					} else {
						System.out.println("Certo, não foi alteado");
					}

					break;
				case 6: // tipoVeiculo: 1. Carro, 2. Caminhão
					Veiculo tipoVeiculo = arrayVeiculos.get(pos);
					
					
					if (tipoVeiculo instanceof Carro) {
						Carro carro = (Carro)tipoVeiculo;
						
						int capacidadePassageiros;
						int quantidadePortas = 0;
						System.out.print("\nCapacidade de passageiros: ");
						capacidadePassageiros = inputNum.nextInt();
						System.out.print("\nQuantidade de portas: ");
						capacidadePassageiros = inputNum.nextInt();

						System.out.println("Alterar? 1.sim | 2.não");
						resp2 = inputNum.nextInt();

						if (resp2 == 1) {
							carro.setCapacidadePassageiros(capacidadePassageiros);
							carro.setQuantidadePortas(quantidadePortas);
							
							System.out.println("OK, alterado com sucesso!");

						} else {
							System.out.println("Certo, não foi alterado");
						}
					}

					if (tipoVeiculo instanceof Caminhao) {
						Caminhao cami = (Caminhao)tipoVeiculo;
						
						Double capacidadeCarga;
						int numeroDeEixos;
						System.out.print("\nCapacidade de Carga: ");
						capacidadeCarga = inputNum.nextDouble();
						System.out.print("\nNúmero de eixos: ");
						numeroDeEixos = inputNum.nextInt();

						System.out.println("Alterar? 1.sim | 2.não");
						resp2 = inputNum.nextInt();

						if (resp2 == 1) {
							cami.setCapacidadeCarga(capacidadeCarga);
							cami.setNumeroDeEixos(numeroDeEixos);
							System.out.println("OK, alteado com sucesso!");

						} else {
							System.out.println("Certo, não foi alteado");
						}
					}

					
				}

			} else {
				System.out.println("Posição inexistente!");
			}

		} else {
			System.out.println("Não existe veículo cadastrado!");
		}
		
		
		
	}

	public void excluir() {
		
	}
	
	public void consultar() {
		
	}
	
	public void relatorio() {
		
	}
	
	public void imprimir() {
		
	}
	
	
}
