package Model;

public abstract class Cliente {
	private String nome;
	private String endereco;
	private String telefone;
	private int codigo;
	
	
	public Cliente(String nome, String endereco, String telefone, int codigo) {
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.codigo = codigo;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
}
