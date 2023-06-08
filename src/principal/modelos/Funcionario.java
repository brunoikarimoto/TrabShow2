package principal.modelos;

import jakarta.persistence.*;

@Entity
public class Funcionario extends Pessoa {
	public enum Funcoes{
		Bar,
		Faxineiro,
		Segurança,
		Recepcionista
	}
	private float salario;
	private Funcoes funcao;
	
	public Funcionario() {}
	
	public Funcionario(String nome, String cpf, int idade, Sexo sexo, float salario, Funcoes funcao) {
		super(nome, cpf, idade, sexo);
		this.salario = salario;
		this.funcao = funcao;
	}

	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
	public Funcoes getFuncao() {
		return funcao;
	}
	public void setFuncao(Funcoes funcao) {
		this.funcao = funcao;
	}
}
