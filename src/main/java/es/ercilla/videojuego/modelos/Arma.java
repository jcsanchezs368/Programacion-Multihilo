package es.ercilla.videojuego.modelos;

public class Arma {
	
	private String nombre;
	private int danio;
	private double precio;
	
	public Arma () {
		
	}
	
	public Arma (String nombre, int danio, double precio) {
		this.nombre = nombre;
		this.danio = danio;
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDanio() {
		return danio;
	}

	public void setDanio(int danio) {
		this.danio = danio;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
	
	
}
