package Controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Cliente;
import Model.Locacao;
import Model.Veiculo;

public class GerenciaCaixa {
	ArrayList<Veiculo> arrayVeiculos;
	ArrayList<Cliente> arrayclientes;
	ArrayList<Locacao> locacoes;
	
	Scanner inputNum;
	Scanner inputChar;
	
	
	public GerenciaCaixa(ArrayList<Veiculo> arrayVeiculos,
			ArrayList<Cliente> arrayclientes, ArrayList<Locacao> locacoes) {
		super();
		this.locacoes = locacoes;
		this.arrayVeiculos = arrayVeiculos;
		this.arrayclientes = arrayclientes;
		inputNum = new Scanner(System.in);
		inputChar = new Scanner(System.in);
	}

	int op = 0;

	public void subMenu() {

		do {
			System.out.println("\n   | SUBMENU Caixa |");
			System.out.println("==================================");
			System.out.println("1 - Pagar locação");
			System.out.println("2 - Total arrecadado");
			System.out.println("3 - Total arrecadado por período");
			System.out.println("4 - Total a receber");
			System.out.println("5 - Voltar ao menu principal");
			System.out.println("============== --0-- =============");
			System.out.println("\n");
			System.out.println("Opção: ");
			String opc = inputChar.nextLine();

			try {
				op = Integer.valueOf(opc).intValue();

				switch (op) {
				case 1:
					System.out.println("*** PAGAR LOCAÇÃO ***");
					pagamentoLocacao();
					break;
				case 2:
					System.out.println("*** TOTAL ARRECADADO ***");
					totalArrecadado();
					break;
				case 3:
					System.out.println("*** TOTAL ARRECADADO POR PERÍODO  ***");
					totalArrecadadoPorPeriodo();
					break;
				case 4:
					System.out.println("*** TOTAL A RECEBER ***");
					totalAReceber();
					break;
				case 5:
					System.out.println("Voltar ao menu principal");
					break;

				default:
					System.out.println("Opção incorreta!");
				}
			} catch (NumberFormatException n) {
				System.out.println("ATENÇÃO! Digite somente números");
			} catch (Exception e) {
				System.out.println("ATENÇÃO! Tente novamente.");
				e.printStackTrace();
			}
		} while (op != 5);
	}
	
	public void pagamentoLocacao() {
		int pos, resp;
		double multa = 0;

		GerenciaAgenda gerAg = new GerenciaAgenda(arrayVeiculos, arrayclientes, locacoes);
		
		if (!locacoes.isEmpty()) {

			gerAg.relatorioVeiculosLocadosEmDia();
			System.out.println("Qual posição deseja pagar?");
			pos = inputNum.nextInt();

			if (pos >= 0 && pos < locacoes.size()) {
				LocalDate dataInicio = locacoes.get(pos).getDataInicio();
				LocalDate dataPrevistaDevolucao = locacoes.get(pos).getDataPrevistaDevolucao();
				LocalDate dataDevolucao = LocalDate.now();
				double preco = locacoes.get(pos).getPreco();
				double total = 0;
				
				if(dataDevolucao.isAfter(dataPrevistaDevolucao)) {
					Period periodo = Period.between(dataPrevistaDevolucao, dataDevolucao);
					System.out.println("ATRASADO");
					System.out.println("Dias de atraso: "+ periodo.getDays());
					
					multa = 0.3;
					total = calcularJuros(periodo.getDays(), preco, multa);
					
					System.out.println("Total a pagar: R$"+ total);
					System.out.println("\nConfirmar pagamento?\n1.Sim | 2.Não");
					resp = inputNum.nextInt();
					if(resp == 1) {
						locacoes.get(pos).setStatus(3);
						locacoes.get(pos).setMulta(total);
						System.out.println("\nPagamento realizado com sucesso!");
					}else {
						System.out.println("\nPagamento não realizado!");
					}
					
				}else {
					System.out.println("EM DIAS");
					total = preco;
					
					System.out.println("Total a pagar: R$"+ total);
					System.out.println("\nConfirmar pagamento?\n1.Sim | 2.Não");
					resp = inputNum.nextInt();
					
					if(resp == 1) {
						locacoes.get(pos).setStatus(3);
						locacoes.get(pos).setMulta(0);
						System.out.println("\nPagamento realizado com sucesso!");
					}else {
						System.out.println("\nPagamento não realizado!");
					}
				}
				

			} else {
				System.out.println("Posição inexistente!");
			}

		} else {
			System.out.println("Não existe veículo locado!");
		}

	}
	
	public void totalArrecadado() {
		if (locacoes.size() != 0) {
			double total = 0;

			for (Locacao l : locacoes) {

				imprimir(l);
				
				System.out.println("------------------------------");
			}
			for (Locacao l : locacoes) {
				total += calcularTotalArrecadado(l);
			}
			System.out.println("\nTotal arrecadado = "+ total);
		}
	}
	
	public void totalArrecadadoPorPeriodo() {
		
	}
	
	public void totalAReceber() {
		
	}
	
	public static double calcularJuros(int dias, double preco, double juros){
        double total = 0;
        total = preco * Math.pow(1 + juros/100, dias);
        return  total;
    }
    
    public static void calcularJurosEmostrar(int dias, double preco, double juros){
        double total = 0;
        double parc;
        int aux;
        
        
        for(aux=1; aux <= dias; aux++){
            total = preco * Math.pow(1 + juros/100, dias);
            parc = total;
            System.out.println("dia "+aux+ ": R$"+parc);
        }
        
        System.out.println("\n Total a pagar= "+total);
        System.out.println("\n\n");
    }
    
    public void imprimir(Locacao l) {
		//DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		if(l.getStatus() == 3) {
			System.out.println("POSIÇÃO-"+"["+locacoes.indexOf(l)+"]");
			System.out.println("Cliente:--------" + l.getCliente().getNome());
			
			if(l.getMulta() == 0) {
				System.out.println("Valor pago:-----"+ l.getPreco());
				
			}else {
				System.out.println("Valor + Multa:--"+ l.getMulta());
			}
			
		}
		
	}
    
    public double calcularTotalArrecadado(Locacao l) {
    	double total = 0;
    	if(l.getStatus() == 3) {
    		if(l.getMulta() == 0) {
    			total = l.getPreco();
    		}else {
    			total = l.getMulta();
    		}
    		
    	}
    	
    	return total;
    }
	
}
