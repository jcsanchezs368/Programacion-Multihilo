package es.ercilla.videojuego.modelos;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Sala {
	private int fila;
	private int columna;
	private BlockingQueue<Heroe> heroes = new ArrayBlockingQueue<>(2);
	private Trofeo trofeo;
	private Arma arma;
	private int nivelMinimo;
	
	public Sala() {
		
	}
	
	public Sala(int fila, int columna, Trofeo trofeo) {
		this.fila = fila;
		this.columna = columna;
		this.trofeo = trofeo;
		this.nivelMinimo = new Random().nextInt(9) + 1;
	}
	
	public Heroe batallar(){
		if(this.heroes.remainingCapacity() == 0) {
			Heroe heroe1 = heroes.poll();
			Heroe heroe2 = heroes.poll();
			while(heroe1.getVida() > 0 && heroe2.getVida() > 0) {
				heroe1.atacar(heroe2);
				if(heroe2.getVida() > 0) {
					heroe2.atacar(heroe1);
				}
			}
			
			if(heroe1.getVida() > 0) {
				return heroe1;
			}else {
				return heroe2;
			}
		}
		return null;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public BlockingQueue<Heroe> getHeroes() {
		return heroes;
	}

	public void setHeroes(BlockingQueue<Heroe> heroes) {
		this.heroes = heroes;
	}

	public Trofeo getTrofeo() {
		return trofeo;
	}

	public void setTrofeo(Trofeo trofeo) {
		this.trofeo = trofeo;
	}

	public Arma getArma() {
		return arma;
	}

	public void setArma(Arma arma) {
		this.arma = arma;
	}

	public int getNivelMinimo() {
		return nivelMinimo;
	}

	public void setNivelMinimo(int nivelMinimo) {
		this.nivelMinimo = nivelMinimo;
	}
	
}
