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
			System.out.println("6 - Voltar ao menu principal");
			System.out.println("============== --0-- =============");
			System.out.println("\n");
			System.out.println("Opção: ");
			op = inputNum.nextInt();

			switch (op) {
			case 1:
				System.out.println("*** CADASTRAR VEÍCULO ***\n");
				incluir();
				break;
			case 2:
				System.out.println("*** ALTERAR VEÍCULO ***\n");
				alterar();
				break;
			case 3:
				System.out.println("*** EXCLUIR VEÍCULO ***\n");
				excluir();
				break;
			case 4:
				System.out.println("*** CONSULTAR VEÍCULO ***\n");
				consultar();
				break;
			case 5:
				System.out.println("*** RELATÓRIO VEÍCULO ***\n");
				relatorio();
				break;
			case 6:
				System.out.println("\nVoltar ao menu principal\n");
				break;

			default:
				System.out.println("\nOpção incorreta!\n");
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

			if ((op < 1) || (op >= 3)) {
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

		// tipoVeiculo = op;

		if (op == 1) {
			int capacidadePassageiros;
			int quantidadePortas = 0;
			System.out.print("\nCapacidade de passageiros: ");
			capacidadePassageiros = inputNum.nextInt();
			System.out.print("\nQuantidade de portas: ");
			quantidadePortas = inputNum.nextInt();

			Carro c = new Carro(marca, modelo, anoFabricacao, anoModelo, placa, capacidadePassageiros,
					quantidadePortas);
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
				imprimirNumerado(arrayVeiculos.get(pos));
				System.out.println("\nDigite o número correspondente ao campo que deseja alterar:");
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
				case 6:
					Veiculo tipoVeiculo = arrayVeiculos.get(pos);

					if (tipoVeiculo instanceof Carro) {
						Carro carro = (Carro) tipoVeiculo;

						int capacidadePassageiros;
						System.out.print("\nCapacidade de passageiros: ");
						capacidadePassageiros = inputNum.nextInt();

						System.out.println("Alterar? 1.sim | 2.não");
						resp2 = inputNum.nextInt();

						if (resp2 == 1) {
							carro.setCapacidadePassageiros(capacidadePassageiros);
							System.out.println("OK, alterado com sucesso!");

						} else {
							System.out.println("Certo, não foi alterado");
						}
					}
					
					if (tipoVeiculo instanceof Caminhao) {
						Caminhao cami = (Caminhao) tipoVeiculo;

						Double capacidadeCarga;
						System.out.print("\nCapacidade de carga: ");
						capacidadeCarga = inputNum.nextDouble();

						System.out.println("Alterar? 1.sim | 2.não");
						resp2 = inputNum.nextInt();

						if (resp2 == 1) {
							cami.setCapacidadeCarga(capacidadeCarga);
							System.out.println("OK, alteado com sucesso!");

						} else {
							System.out.println("Certo, não foi alteado");
						}
					}
					break;

				case 7:
					tipoVeiculo = arrayVeiculos.get(pos);

					if (tipoVeiculo instanceof Carro) {
						Carro carro = (Carro) tipoVeiculo;

						int quantidadePortas;
						System.out.print("\nQuantidade de portas: ");
						quantidadePortas = inputNum.nextInt();

						System.out.println("Alterar? 1.sim | 2.não");
						resp2 = inputNum.nextInt();

						if (resp2 == 1) {
							carro.setQuantidadePortas(quantidadePortas);
							System.out.println("OK, alterado com sucesso!");

						} else {
							System.out.println("Certo, não foi alterado");
						}
					}else {
						if (tipoVeiculo instanceof Caminhao) {
							Caminhao cami = (Caminhao) tipoVeiculo;

							int numeroDeEixos;
							System.out.print("\nNúmero de eixos: ");
							numeroDeEixos = inputNum.nextInt();

							System.out.println("Alterar? 1.sim | 2.não");
							resp2 = inputNum.nextInt();

							if (resp2 == 1) {
								cami.setNumeroDeEixos(numeroDeEixos);
								System.out.println("OK, alteado com sucesso!");

							} else {
								System.out.println("Certo, não foi alteado");
							}
						}
					}

					break;

				}

			} else {
				System.out.println("Posição inexistente!");
			}

		} else {
			System.out.println("Não existe veículo cadastrado!");
		}

	}

	public void excluir() {
		int pos, resp;

		System.out.println("Qual posição deseja excluir?");
		pos = inputNum.nextInt();

		if (pos >= 0 && pos <= arrayVeiculos.size()) {
			System.out.println("Informações atuais:\n");
			imprimir(arrayVeiculos.get(pos));
			System.out.println("\nTem certeza que deseja excluir?\n1.Sim | 2.Não\n");
			resp = inputNum.nextInt();
			if (resp == 1) {
				arrayVeiculos.remove(pos);
				System.out.printf("Posição[%d] removido com sucesso!", pos);
				System.out.println("");
			}

		} else {
			System.out.println("Posição inexistente!");
		}
	}

	public void consultar() {
		int pos;

		System.out.println("Qual posição deseja consultar?");
		pos = inputNum.nextInt();

		if (pos >= 0 && pos <= arrayVeiculos.size()) {
			System.out.println("Informações atuais:\n");
			imprimir(arrayVeiculos.get(pos));
		} else {
			System.out.println("Posição inexistente!");
		}
	}

	public void relatorio() {
		if (arrayVeiculos.size() != 0) {
			for (Veiculo v : arrayVeiculos) {
				imprimirNumerado(v);
				System.out.println("\n----x----x----x----x----x-----\n");
			}
		} else {
			System.out.println("Não existe veículo cadastrado!");
		}

	}

	public void imprimirNumerado(Veiculo v) {
		System.out.println("1.Marca:----------------------" + v.getMarca());
		System.out.println("2.Modelo:---------------------" + v.getModelo());
		System.out.println("3.Ano de Fabricação:----------" + v.getAnoFabricacao());
		System.out.println("4.Ano do Modelo:--------------" + v.getAnoModelo());
		System.out.println("5.Placa:----------------------" + v.getPlaca());

		if (v instanceof Carro) {
			Carro carro = (Carro) v;
			System.out.println("6.Capacidade de passageiros:--" + carro.getCapacidadePassageiros());
			System.out.println("7.Quantidade de portas:-------" + carro.getQuantidadePortas());
		}

		if (v instanceof Caminhao) {
			Caminhao cami = (Caminhao) v;
			System.out.println("6.Capacidade de carga:--------" + cami.getCapacidadeCarga());
			System.out.println("7.Número de eixos:------------" + cami.getNumeroDeEixos());
		}
	}

	public void imprimir(Veiculo v) {
		System.out.println("Marca:----------------------" + v.getMarca());
		System.out.println("Modelo:---------------------" + v.getModelo());
		System.out.println("Ano de Fabricação:----------" + v.getAnoFabricacao());
		System.out.println("Ano do Modelo:--------------" + v.getAnoModelo());
		System.out.println("Placa:----------------------" + v.getPlaca());

		if (v instanceof Carro) {
			Carro carro = (Carro) v;
			System.out.println("Capacidade de passageiros:--" + carro.getCapacidadePassageiros());
			System.out.println("Quantidade de portas:-------" + carro.getQuantidadePortas());
		}

		if (v instanceof Caminhao) {
			Caminhao cami = (Caminhao) v;
			System.out.println("Capacidade de carga:--------" + cami.getCapacidadeCarga());
			System.out.println("Número de eixos:------------" + cami.getNumeroDeEixos());
		}
	}
	
	public int buscarVeiculoPorPlaca(String placa) {
		int pos = 0;
		String compara = null;


		if (arrayVeiculos.size() != 0) {
			for (Veiculo v : arrayVeiculos) {
				compara = v.getPlaca();

				if (compara.equals(placa)) {

					break;
				}
				pos++;
			}

			if (compara.equals(placa)) {

				return pos;
			} else {
				System.out.println("compara= "+compara);
				System.out.println("placa= "+placa);
				System.out.println("pos= "+pos);
				System.out.println("Veículo não encontrado!\n");

				return -1;
			}

		} else {
			System.out.println("Não existe veículo cadastrado!");
			return -2;
		}

	}

}
