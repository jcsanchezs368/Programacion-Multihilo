package es.ercilla.videojuego.modelos;

import java.util.ArrayList;
import java.util.Random;

public class Tablero {
	private Sala salas[][];
	
	public Tablero(int filas, int columnas) {
		int trofeoAleatorio;
		int armaAleatoria;
		
		ArrayList<Trofeo> trofeos = new ArrayList<>();
		trofeos.add(new Trofeo("Diamante", 1000, 100));
		trofeos.add(new Trofeo("Lingote de oro", 750, 75));
		trofeos.add(new Trofeo("Esmeralda", 500, 50));
		trofeos.add(new Trofeo("Lingote de plata", 250, 35));
		trofeos.add(new Trofeo("Lingote de bronce", 100, 25));
		trofeos.add(new Trofeo("Jarrón valioso", 50, 20));
		trofeos.add(new Trofeo("Mineral raro", 25, 10));
		
		ArrayList<Arma> armas = new ArrayList<>();
		armas.add(new Arma("Gran espadón", 100, 1000));
		armas.add(new Arma("Katana afilada", 75, 750));
		armas.add(new Arma("Katana", 50, 750));
		armas.add(new Arma("Espada recta afilada", 45, 500));
		armas.add(new Arma("Espada recta", 40, 500));
		armas.add(new Arma("Daga afilada", 25, 250));
		armas.add(new Arma("Daga", 20, 250));
		
		this.salas = new Sala[filas][columnas];
		for(int i = 0; i < salas.length; i++) {
			for(int j = 0; j < salas[0].length; j++) {
				trofeoAleatorio = new Random().nextInt(trofeos.size() -1);
				armaAleatoria = new Random().nextInt(armas.size() - 1);
				salas[i][j] = new Sala(i, j , trofeos.get(trofeoAleatorio));
				if(i % 2 == 0 && j % 2 == 0) {
					salas[i][j].setArma(armas.get(armaAleatoria));
				}
			}
		}
	}
	
	
	
}
