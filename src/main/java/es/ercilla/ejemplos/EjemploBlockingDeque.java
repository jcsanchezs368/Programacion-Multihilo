package es.ercilla.ejemplos;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class EjemploBlockingDeque {

	public static void main(String[] args) throws InterruptedException {
		BlockingDeque<String> cola = new LinkedBlockingDeque<>();
		
		cola.putFirst("Mensaje B");
		cola.putFirst("Mensaje A");
		cola.putLast("Mensaje C");
		System.out.println(cola);
		
		String primero = cola.takeFirst();
		String ultimo = cola.takeLast();
		
		System.out.println("Extraido del principio: " + primero);
		System.out.println("Extraido del final: " + ultimo);
		System.out.println("Contenido restante: " + cola);

	}

}
