package es.ercilla.ejemplos;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class EjemploBlockingQueue {
	public static void main(String[] args) {
		BlockingQueue<String> cola = new ArrayBlockingQueue<String>(5);
		
		Thread productor = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("Productor: generando mensaje...");
					Thread.sleep(2000);
					cola.put("Hola desde el productor");
					System.out.println("Productor: mensaje enviado");
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		});
		
		Thread consumidor = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("Consumidor: esperando mensaje...");
					// Coge el primer elemento, esperando a que exista si es necesario
					String mensaje = cola.take();
					System.out.println("Consumidor recibe: " + mensaje);
				}catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		});
		
		consumidor.start();
		productor.start();
	}
}
