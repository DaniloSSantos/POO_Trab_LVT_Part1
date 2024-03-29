package Model;

import java.time.LocalDate;

public class Fisica extends Cliente {
	private String cpf;
	private LocalDate dataNascimento;
	
	public Fisica(String nome, String endereco, String telefone, int codigo, String cpf, LocalDate dataNascimento) {
		super(nome, endereco, telefone, codigo);
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
}
