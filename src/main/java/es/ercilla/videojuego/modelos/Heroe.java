package es.ercilla.videojuego.modelos;

import java.util.ArrayList;
import java.util.List;

public class Heroe {

	private String nombre;
	private int vidaMaxima = 100;
	private int vida = 100;
	private Arma arma = null;
	private int nivel = 1;
	private int experiencia = 0;
	private double dinero = 0;
	private List<Trofeo> mochila = new ArrayList<Trofeo>();

	public Heroe(String nombre) {
		this.nombre = nombre;
	}

	public Heroe() {

	}

	public void ganarExperiencia(int experiencia) {
		this.experiencia += experiencia;
		if (this.experiencia >= 100) {
			this.experiencia -= 100;
			this.nivel++;
			this.vidaMaxima += 10;
			System.out.println(this.nombre + " sube de nivel. Nivel: " + this.nivel + " Vida máxima: " + this.vidaMaxima);
		}
	}

	public void conseguirTrofeo(Trofeo trofeo) {
		this.mochila.add(trofeo);
		this.dinero += trofeo.getValor();
	}

	public void recibirGolpe(int danio) {
		this.vida = this.vida - danio;
		if (this.vida <= 0) {
			this.vida = 0;
		}
	}

	public void atacar(Heroe heroe) {
		if (this.arma == null) {
			heroe.recibirGolpe(10);
		} else {
			heroe.recibirGolpe(this.arma.getDanio());
		}
	}

	public void comprarArma(Arma arma) {
		if (this.dinero >= arma.getPrecio()) {
			System.out.println(this.nombre + " compra el arma: " + arma.getNombre() + " (" + arma.getPrecio() + " $)"
					+ " dinero restante: " + this.getDinero());
		} else {
			System.out.println(this.nombre + " no tiene suficiente dinero para comprar el arma:" + arma.getNombre()
					+ " (" + this.dinero + "/" + arma.getPrecio() + ")$");
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVidaMaxima() {
		return vidaMaxima;
	}

	public void setVidaMaxima(int vidaMaxima) {
		this.vidaMaxima = vidaMaxima;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	public double getDinero() {
		return dinero;
	}

	public void setDinero(double dinero) {
		this.dinero = dinero;
	}

	public Arma getArma() {
		return arma;
	}

	public void setArma(Arma arma) {
		this.arma = arma;
	}

	public List<Trofeo> getMochila() {
		return mochila;
	}

	public void setMochila(List<Trofeo> mochila) {
		this.mochila = mochila;
	}

}
