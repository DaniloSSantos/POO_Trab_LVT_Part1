package Model;

public class Juridica extends Cliente {
	private String cnpj;

	public Juridica(String nome, String endereco, String telefone, int codigo, String cnpj) {
		super(nome, endereco, telefone, codigo);
		this.cnpj = cnpj;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	
}
