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
		String nome, endereco, telefone;
		
		int op;
		do {
			System.out.println("-------------");
			System.out.println("1 - Física    |");
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
		
		if(op == 1) {
			String cpf, data;
			LocalDate dataNascimento;
			
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			System.out.print("CPF: ");
			cpf = inputChar.nextLine();
			System.out.print("Data de nascimento:\nformato dd/MM/yyyy\n");
			data = inputChar.nextLine();
			
			dataNascimento = LocalDate.parse(data, formato);
			
			Fisica f = new Fisica(nome, endereco, telefone, cpf, dataNascimento);
			arrayclientes.add(f);
			
			System.out.println("\nPessoa física cadastrada com sucesso!");
		}
		
		if(op == 2) {
			String cnpj;
			System.out.print("CNPJ: ");
			cnpj = inputChar.nextLine();
			
			Juridica j = new Juridica(nome, endereco, telefone, cnpj);
			arrayclientes.add(j);
			
			System.out.println("\nPessoa Jurídica cadastrada com sucesso!");
		}
		
	}
	
	public void alterar() {
		
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
