package es.ercilla.modelos;

import java.util.Objects;

public class Sala {
	private int numero;
	private String trofeo;

	public Sala(int numero, String trofeo) {
		this.numero = numero;
		this.trofeo = trofeo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTrofeo() {
		return trofeo;
	}

	public void setTrofeo(String trofeo) {
		this.trofeo = trofeo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Integer.valueOf(numero), trofeo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sala other = (Sala) obj;
		return numero == other.numero && Objects.equals(trofeo, other.trofeo);
	}

	@Override
	public String toString() {
		return "Sala [numero=" + numero + ", trofeo=" + trofeo + "]";
	}
	

}
