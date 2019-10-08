package Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Cliente;
import Model.Fisica;
import Model.Juridica;

public class GerenciaClientes {
	ArrayList<Cliente> arrayclientes;

	Scanner inputNum;
	Scanner inputChar;

	public GerenciaClientes(ArrayList<Cliente> arrayclientes) {
		this.arrayclientes = arrayclientes;
		inputNum = new Scanner(System.in);
		inputChar = new Scanner(System.in);
	}

	int op = 0;

	public void subMenu() {
		do {
			System.out.println("\n   | SUBMENU Clientes |");
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
				System.out.println("*** CADASTRAR CLIENTE ***\n");
				incluir();
				break;
			case 2:
				System.out.println("*** ALTERAR CLIENTE ***\n");
				alterar();
				break;
			case 3:
				System.out.println("*** EXCLUIR CLIENTE ***\n");
				excluir();
				break;
			case 4:
				System.out.println("*** CONSULTAR CLIENTE ***\n");
				consultar();
				break;
			case 5:
				System.out.println("*** RELATÓRIO CLIENTE ***\n");
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
		String nome, endereco, telefone;

		int op;
		do {
			System.out.println("-------------");
			System.out.println("1 - Física   |");
			System.out.println("2 - Jurídica |");
			System.out.println("-------------\n");
			System.out.println("Opção: ");
			op = inputNum.nextInt();

			if ((op < 1) || (op > 2)) {
				System.out.println("Opção inexistente!");
			}

		} while ((op < 1) || (op > 2));

		System.out.println("Digite os dados a seguir");

		System.out.print("Nome: ");
		nome = inputChar.nextLine();
		System.out.print("Endereço: ");
		endereco = inputChar.nextLine();
		System.out.print("Telefone: ");
		telefone = inputChar.nextLine();

		if (op == 1) {
			String cpf, data;
			LocalDate dataNascimento;
			int codigo;

			codigo = GerarCodigo.getProximoCodigo();

			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			System.out.print("CPF: ");
			cpf = inputChar.nextLine();
			System.out.print("Data de nascimento:\nformato dd/MM/yyyy\n");
			data = inputChar.nextLine();

			dataNascimento = LocalDate.parse(data, formato);

			Fisica f = new Fisica(nome, endereco, telefone, codigo, cpf, dataNascimento);
			arrayclientes.add(f);

			System.out.println("\nPessoa física cadastrada com sucesso!");
		}

		if (op == 2) {
			String cnpj;
			System.out.print("CNPJ: ");
			cnpj = inputChar.nextLine();
			int codigo;

			codigo = GerarCodigo.getProximoCodigo();

			Juridica j = new Juridica(nome, endereco, telefone, codigo, cnpj);
			arrayclientes.add(j);

			System.out.println("\nPessoa Jurídica cadastrada com sucesso!");
		}

	}

	public void alterar() {
		String nome, endereco, telefone;
		int pos, resp, resp2;

		if (!arrayclientes.isEmpty()) {
			System.out.println("Qual posição deseja alterar?");
			pos = inputNum.nextInt();

			if (pos >= 0 && pos <= arrayclientes.size()) {
				System.out.println("Informações atuais:\n");
				imprimirNumerado(arrayclientes.get(pos));
				System.out.println("\nDigite o número correspondente ao campo que deseja alterar:");
				resp = inputNum.nextInt();

				switch (resp) {
				case 1:
					System.out.print("Nome: ");
					nome = inputChar.nextLine();
					System.out.println("Alterar? 1.sim | 2.não");
					resp2 = inputNum.nextInt();

					if (resp2 == 1) {
						arrayclientes.get(pos).setNome(nome);
						System.out.println("OK, alterado com sucesso!");
					} else {
						System.out.println("Certo, não foi alterado");
					}

					break;
				case 2:
					System.out.print("\nEndereço: ");
					endereco = inputChar.nextLine();
					System.out.println("Alterar? 1.sim | 2.não");
					resp2 = inputNum.nextInt();

					if (resp2 == 1) {
						arrayclientes.get(pos).setEndereco(endereco);
						System.out.println("OK, alterado com sucesso!");
					} else {
						System.out.println("Certo, não foi alterado");
					}
					break;
				case 3:
					System.out.print("\nTelefone: ");
					telefone = inputChar.nextLine();

					System.out.println("Alterar? 1.sim | 2.não");
					resp2 = inputNum.nextInt();

					if (resp2 == 1) {
						arrayclientes.get(pos).setTelefone(telefone);
						System.out.println("OK, alterado com sucesso!");
					} else {
						System.out.println("Certo, não foi alterado");
					}

					break;
				case 4:
					Cliente tipoCliente = arrayclientes.get(pos);

					if (tipoCliente instanceof Fisica) {
						Fisica fisica = (Fisica) tipoCliente;

						String cpf;
						System.out.print("\nCPF: ");
						cpf = inputChar.nextLine();

						System.out.println("Alterar? 1.sim | 2.não");
						resp2 = inputNum.nextInt();

						if (resp2 == 1) {
							fisica.setCpf(cpf);
							System.out.println("OK, alterado com sucesso!");

						} else {
							System.out.println("Certo, não foi alterado");
						}
					}

					if (tipoCliente instanceof Juridica) {
						Juridica juri = (Juridica) tipoCliente;

						String cnpj;
						System.out.print("\nCNPJ: ");
						cnpj = inputChar.nextLine();

						System.out.println("Alterar? 1.sim | 2.não");
						resp2 = inputNum.nextInt();

						if (resp2 == 1) {
							juri.setCnpj(cnpj);
							System.out.println("OK, alterado com sucesso!");

						} else {
							System.out.println("Certo, não foi alterado");
						}
					}

					break;

				case 5:
					tipoCliente = arrayclientes.get(pos);

					if (tipoCliente instanceof Fisica) {
						LocalDate dataNascimento;
						String data;

						Fisica fisica = (Fisica) tipoCliente;

						DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

						System.out.print("Data de nascimento:\nformato dd/MM/yyyy\n");
						data = inputChar.nextLine();

						dataNascimento = LocalDate.parse(data, formato);

						System.out.println("Alterar? 1.sim | 2.não");
						resp2 = inputNum.nextInt();

						if (resp2 == 1) {
							fisica.setDataNascimento(dataNascimento);
							System.out.println("OK, alterado com sucesso!");

						} else {
							System.out.println("Certo, não foi alterado");
						}
					}

					break;

				}

			} else {
				System.out.println("Posição inexistente!");
			}

		} else {
			System.out.println("Não existe Cliente cadastrado!");
		}
	}

	public void excluir() {
		int pos, resp;

		System.out.println("Qual posição deseja excluir?");
		pos = inputNum.nextInt();

		if (pos >= 0 && pos <= arrayclientes.size()) {
			System.out.println("Informações atuais:\n");
			imprimir(arrayclientes.get(pos));
			System.out.println("\nTem certeza que deseja excluir?\n1.Sim | 2.Não\n");
			resp = inputNum.nextInt();
			if (resp == 1) {
				arrayclientes.remove(pos);
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

		if (pos >= 0 && pos <= arrayclientes.size()) {
			System.out.println("Informações atuais:\n");
			imprimir(arrayclientes.get(pos));
		} else {
			System.out.println("Posição inexistente!");
		}
	}

	public void relatorio() {
		if (arrayclientes.size() != 0) {
			for (Cliente c : arrayclientes) {
				imprimir(c);
				System.out.println("\n----x----x----x----x----x-----\n");
			}
		} else {
			System.out.println("Não existe veículo cadastrado!");
		}
	}

	public void imprimir(Cliente c) {
		System.out.println("CÓDIGO [" + c.getCodigo() + "]");
		System.out.println("Nome:------------------" + c.getNome());
		System.out.println("Endereço:--------------" + c.getEndereco());
		System.out.println("Telefone:--------------" + c.getTelefone());

		if (c instanceof Fisica) {
			Fisica fisica = (Fisica) c;
			System.out.println("CPF:-------------------" + fisica.getCpf());
			System.out.println("Data de nascimento:----" + fisica.getDataNascimento());
		}

		if (c instanceof Juridica) {
			Juridica juri = (Juridica) c;
			System.out.println("CNPJ:------------------" + juri.getCnpj());
		}
	}

	public void imprimirNumerado(Cliente c) {
		System.out.println("1.CÓDIGO [" + c.getCodigo() + "]");
		System.out.println("1.Nome:------------------" + c.getNome());
		System.out.println("2.Endereço:--------------" + c.getEndereco());
		System.out.println("3.Telefone:--------------" + c.getTelefone());

		if (c instanceof Fisica) {
			Fisica fisica = (Fisica) c;
			System.out.println("4.CPF:-------------------" + fisica.getCpf());
			System.out.println("5.Data de nascimento:----" + fisica.getDataNascimento());
		}

		if (c instanceof Juridica) {
			Juridica juri = (Juridica) c;
			System.out.println("4.CNPJ:------------------" + juri.getCnpj());
		}
	}

	public int buscarClientePorCodigo(int codCliente) {
		int posicao = 0;
		int compara = 0;

		if (arrayclientes.size() != 0) {
			for (int i = 0; i < arrayclientes.size(); i++) {
				if (arrayclientes.get(i).getCodigo() == codCliente) {
					compara = arrayclientes.get(i).getCodigo();
					posicao = i;
					break;
				}
			}

			if (compara == codCliente) {
				return posicao;

			} else {
				System.out.println("Cliente não encontrado!");
				return -1;
			}
		} else {
			System.out.println("Não existe cliente cadastrado!");
			return -2;

		}
	}

}
