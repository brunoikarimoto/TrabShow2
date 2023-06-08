package principal.modelos;

import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "MeuShow")
public class Show {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Show() {}
	
	public Show(String nomeShow, int quantIngressos, float valorMasc, float valorFem, int ano, int mes, int dia) {
		this.nomeShow = nomeShow;
		this.quantIngressos = quantIngressos;
		this.valorMasc = valorMasc;
		this.valorFem = valorFem;
		
		this.diaEvento = dia + "/" + mes + "/" + ano;
	}
	
	private String nomeShow;
	private String diaEvento;
	private int quantIngressos;
	private float valorMasc;
	private float valorFem;
	
	@OneToMany(targetEntity = Artista.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "show_id", referencedColumnName = "id")
	private List<Artista> artistas;
	
	public String getDiaEvento() {
		return diaEvento;
	}
	public void setDiaEvento(String diaEvento) {
		this.diaEvento = diaEvento;
	}
	public int getQuantIngressos() {
		return quantIngressos;
	}
	public void setQuantIngressos(int quantIngressos) {
		this.quantIngressos = quantIngressos;
	}
	public float getValorMasc() {
		return valorMasc;
	}
	public void setValorMasc(float valorMasc) {
		this.valorMasc = valorMasc;
	}
	public float getValorFem() {
		return valorFem;
	}
	public void setValorFem(float valorFem) {
		this.valorFem = valorFem;
	}
	public List<Artista> getArtistas() {
		return artistas;
	}
	public void setArtistas(List<Artista> artistas) {
		this.artistas = artistas;
	}
	public String getNomeShow() {
		return nomeShow;
	}
	public void setNomeShow(String nomeShow) {
		this.nomeShow = nomeShow;
	}
	
}
