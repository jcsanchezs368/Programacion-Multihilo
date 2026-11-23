package es.ercilla.modelos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Fortaleza {
	private ArrayList<Sala> salas;
	private ArrayList<String> trofeos;
	private Random random;
	
	public Fortaleza() {
		random = new Random();
		salas = new ArrayList<Sala>();
		trofeos = new ArrayList<String>();
		trofeos.add("Escudo");
		trofeos.add("Espada");
		trofeos.add("Martillo");
		trofeos.add("Corona");
		trofeos.add("Anillo");
		trofeos.add("Armadura");
		trofeos.add("Capa");
		trofeos.add("Casco");
		trofeos.add("Lanza");
		trofeos.add("Medallón");
		
		ArrayList<String> trofeosSalas = new ArrayList<String>();
		
		// Garantizar los 10 trofeos diferentes
		for (String trofeo : trofeos) {
			trofeosSalas.add(trofeo);
		}
		// Añadir otros 10 procesos aleatorios
		for(int i = 0; i < 10; i++) {
			int posicion = random.nextInt(trofeos.size());
			trofeosSalas.add(trofeos.get(posicion));
		}
		// Mezclar los trofeos
		Collections.shuffle(trofeosSalas);
		// Crear las 20 salas
		for(int i = 0; i < 20; i++) {
			Sala sala = new Sala(i + 1, trofeosSalas.get(i));
			salas.add(sala);
		}
	}
	
	public Sala getSalaAleatoria() {
		int posicion = random.nextInt(salas.size());
		return salas.get(posicion);
	}
	
	public int getNumeroTrofeos() {
		return trofeos.size();
	}
	
	public void mostrarSalas() {
		System.out.println("===== DISTRIBUCIÓN DE LA FORTALEZA =====");
		for(Sala sala : salas) {
			System.out.println(sala);
		}
		System.out.println("========================================");
	}

	@Override
	public String toString() {
		return "Fortaleza [salas=" + salas + ", trofeos=" + trofeos + ", random=" + random + "]";
	}
	
	
	
}
