package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Agenda;
import Model.Caminhao;
import Model.Carro;
import Model.Cliente;
import Model.Locacao;
import Model.Veiculo;

public class GerenciaLocacao {
	ArrayList<Locacao> locacoes;
	ArrayList<Veiculo> arrayVeiculos;
	ArrayList<Cliente> arrayclientes;
	
	Scanner inputNum;
	Scanner inputChar;

	public GerenciaLocacao(ArrayList<Locacao> locacoes) {
		inputNum = new Scanner(System.in);
		inputChar = new Scanner(System.in);
	}
	
	public GerenciaLocacao(ArrayList<Veiculo> arrayVeiculos,
			ArrayList<Cliente> arrayclientes, ArrayList<Locacao> locacoes, ArrayList<Agenda> arrayAgenda) {
		super();
		this.locacoes = locacoes;
		this.arrayVeiculos = arrayVeiculos;
		this.arrayclientes = arrayclientes;
		inputNum = new Scanner(System.in);
		inputChar = new Scanner(System.in);
	}
	
	
	public void imprimir(Veiculo v) {
		
	}

}
