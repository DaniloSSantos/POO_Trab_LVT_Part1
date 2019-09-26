package Model;

public class Agenda {
	private Locacao agendamento;
	private int status;
	
	
	public Agenda(Locacao agendamento, int status) {
		super();
		this.agendamento = agendamento;
		this.status = status;
	}


	public Locacao getAgendamento() {
		return agendamento;
	}


	public void setAgendamento(Locacao agendamento) {
		this.agendamento = agendamento;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}
}
