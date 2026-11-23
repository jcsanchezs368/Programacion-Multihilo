package es.ercilla.aplicaciones;

import es.ercilla.modelos.Coche;

public class SimulaParking {

	public static void main(String[] args) throws InterruptedException {
		Parking parking = new Parking(3);
		Coche[] coches = new Coche[10];
		int numero = 0;
		
		for(int i = 0; i < coches.length; i++) {
			numero = (int) (Math.random() * 5) + 1;
			coches[i] = new Coche("Coche " + (i + 1),parking, numero);
			System.out.println(coches[i].getName() + " - " + coches[i].getTiempoAparcado());
		}
		
		for(int i = 0; i < coches.length; i++) {
			coches[i].join();
		}
		System.out.println("TODOS LOS COCHES HAN ABANDONADO EL PARKING");
	}

}
