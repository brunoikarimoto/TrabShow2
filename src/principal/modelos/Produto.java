package principal.modelos;

import jakarta.persistence.*;

@Entity
public class Produto {
	
	public Produto() {}
	
	public Produto(String nome, float preco, Tipo tipo) {
		this.nome = nome;
		this.preco = preco;
		this.tipo = tipo;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public enum Tipo{
		BEBIDA,
		COMIDA
	}
	
	private String nome;
	private float preco;
	private Tipo tipo;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
}
