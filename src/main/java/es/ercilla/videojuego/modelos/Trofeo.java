package es.ercilla.videojuego.modelos;

public class Trofeo {
	private String nombre;
	private double valor;
	private int experiencia;
	
	public Trofeo() {
		
	}
	
	public Trofeo(String nombre, double valor, int experiencia) {
		this.nombre = nombre;
		this.valor = valor;
		this.experiencia = experiencia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}
	
}
