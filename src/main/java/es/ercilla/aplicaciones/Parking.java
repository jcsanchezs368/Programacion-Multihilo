package es.ercilla.aplicaciones;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Un parking dispone de 3 plazas de aparcamiento. A lo largo del día llegan 8 coches
 * que intentan aparcar.
 * Cada coche debe solicitar una plaza al parking y, si consigue aparcar, permanecerá estacionado durante un 
 * tiempo determinado.
 * 
 * El parking debe cumplir los siguientes requisitos: 1. Gestionar las plazas mediante BlockingQueue<Integer>
 * y ArrayBlockingQueue<Integer>. 2. Cada plaza estará identificada mediante un número (1, 2 y 3). 3. Cada coche
 * se ejecutará en un hilo independiente utilizando Thread y start(). 4. Cuando un coche solicita aprcar:
 * - Si hay plazas disonibles, el parking le asigna una. - Si no quedan plazas, el parking muestra el mensaje
 * "PARKING COMPLETO. Los coches deben esperar". - Los coches que 
 */

public class Parking {
	private BlockingQueue<Integer> plazasLibres;
	private int capacidad;
	
	public Parking(int capacidad) {
		this.capacidad = capacidad;
		plazasLibres = new ArrayBlockingQueue<Integer>(capacidad);
		for(int i = 1; i <= capacidad; i++) {
			plazasLibres.add(1);
		}
	}
	
	public int entrar(String coche) throws InterruptedException {
		System.out.println(coche + " solicita aparcar");
		if(plazasLibres.isEmpty()) {
			System.out.println("PARKING COMPLETO. " + coche + " debe esperar" );
		}
		int plaza = plazasLibres.take();
		System.out.println(coche + " aparca en plaza " + plaza);
		if(plazasLibres.isEmpty()) {
			System.out.println("PARKING COMPLETO");
		}
		return plaza;
	}
	
	public void salir(String coche, int plaza) throws InterruptedException {
		plazasLibres.put(plaza);
		System.out.println(coche + " abandona plaza " + plaza);
		System.out.println("PARKING: Hay plazas disponibles. Plazas Libres: " + plazasLibres.size());
	}

	public BlockingQueue<Integer> getPlazasLibres() {
		return plazasLibres;
	}

	public void setPlazasLibres(BlockingQueue<Integer> plazasLibres) {
		this.plazasLibres = plazasLibres;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	
	
}
