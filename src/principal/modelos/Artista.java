package principal.modelos;

import jakarta.persistence.*;

@Entity
public class Artista extends Pessoa {
	
	public Artista() {}
	
	public Artista(String nome, String cpf, int idade, Sexo sexo, float cache) {
		super(nome, cpf, idade, sexo);
		this.cache = cache;
	}
	
	private float cache;
	
	public float getCache() {
		return cache;
	}
	public void setCache(float cache) {
		this.cache = cache;
	}

}
