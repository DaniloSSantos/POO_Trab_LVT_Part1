package Model;

public class Caminhao extends Veiculo {
	private Double capacidadeCarga;
	private int numeroDeEixos;
	
	public Caminhao(String marca, String modelo, int anoFabricacao, int anoModelo, String placa, Double capacidadeCarga, int numeroDeEixos) {
		super(marca, modelo, anoFabricacao, anoModelo, placa);
		this.capacidadeCarga = capacidadeCarga;
		this.numeroDeEixos = numeroDeEixos;
	}

	public Double getCapacidadeCarga() {
		return capacidadeCarga;
	}

	public void setCapacidadeCarga(Double capacidadeCarga) {
		this.capacidadeCarga = capacidadeCarga;
	}

	public int getNumeroDeEixos() {
		return numeroDeEixos;
	}

	public void setNumeroDeEixos(int numeroDeEixos) {
		this.numeroDeEixos = numeroDeEixos;
	}
	
	
}
