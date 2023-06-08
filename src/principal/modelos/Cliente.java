package principal.modelos;

import jakarta.persistence.*;

@Entity
public class Cliente extends Pessoa {
	
	@OneToOne(cascade = CascadeType.ALL)
	private Comanda comanda;
	
	public Cliente() {}
	
	public Cliente(String nome, String cpf, int idade, Sexo sexo) {
		super(nome, cpf, idade, sexo);
		this.comanda = new Comanda();
	}

	public Comanda getComanda() {
		return comanda;
	}
	public void setComanda(Comanda comanda) {
		this.comanda = comanda;
	}
	
}
