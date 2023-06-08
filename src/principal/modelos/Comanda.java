package principal.modelos;

import jakarta.persistence.*;

@Entity
public class Comanda {
	
	public Comanda() {
		this.comida = 0;
		this.bebida = 0;
		this.pago = true;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comanda")
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	private float comida;
	private float bebida;
	private boolean pago;

	public float getComida() {
		return comida;
	}
	public void setComida(float comida) {
		this.comida = comida;
	}
	public float getBebida() {
		return bebida;
	}
	public void setBebida(float bebida) {
		this.bebida = bebida;
	}
	public boolean getPago() {
		return pago;
	}
	public void setPago(boolean pago) {
		this.pago = pago;
	}
	
}
