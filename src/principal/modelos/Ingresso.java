package principal.modelos;

import jakarta.persistence.*;

@Entity
public class Ingresso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Ingresso() {}
	
	public Ingresso(String nomeShow, String clienteCPF) {
		this.nomeShow = nomeShow;
		this.clienteCPF = clienteCPF;
		this.status = Status.NAO_USADO;
	}
	
	public enum Status{
		NAO_USADO,
		USADO,
		ATIVO
	}
	
	private String nomeShow;
	private Status status;
	
	@ManyToOne(targetEntity = Cliente.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "cpf_cliente", referencedColumnName = "cpf")
	private String clienteCPF;
	
	public String getNomeShow() {
		return nomeShow;
	}
	public void setNomeShow(String nomeShow) {
		this.nomeShow = nomeShow;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getClienteCPF() {
		return clienteCPF;
	}
	public void setClienteCPF(String clienteCPF) {
		this.clienteCPF = clienteCPF;
	}
	
}
