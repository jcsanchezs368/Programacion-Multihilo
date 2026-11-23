package es.ercilla.ejemplos;

import java.util.concurrent.ConcurrentLinkedQueue;

public class EjemploConcurrentLinkedQueue {

	public static void main(String[] args) {
		ConcurrentLinkedQueue<String> cola = new ConcurrentLinkedQueue<String>();
		
		cola.add("Mensaje 1");
		cola.add("Mensaje 2");
		cola.add("Mensaje 3");
		
		System.out.println("Contenido de la cola:");
		System.out.println(cola);
		
		String mensaje = cola.poll();
		
		System.out.println("El elemento extraído: " + mensaje);
		
		System.out.println("Contenido final: ");
		System.out.println(cola);

	}

}
