package es.ercilla.modelos;

import java.util.ArrayList;

public class Juego {
	public static void main(String[] args) {
		Fortaleza fortaleza = new Fortaleza();
		fortaleza.mostrarSalas();
		String[] nombres = {"Iron Man", "Thor", "Hulk", "Batman", "Superman"};
		ArrayList<Thread> hilos = new ArrayList<Thread>();
		for(int i = 0; i < nombres.length; i++) {
			Superheroe superheroe = new Superheroe(nombres[i], fortaleza);
			Thread hilo = new Thread(superheroe);
			hilos.add(hilo);
			hilo.start();
		}
	}
}
