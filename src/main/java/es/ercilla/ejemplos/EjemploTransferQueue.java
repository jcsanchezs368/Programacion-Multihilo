package es.ercilla.ejemplos;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class EjemploTransferQueue{

	public static void main(String[] args) {
		TransferQueue<String> cola = new LinkedTransferQueue<>();
		Thread productor = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Productor: enviando mensaje");
				try {
					cola.transfer("Mensaje importante");
				System.out.println("Productor: el consumidor ha recibido el mensaje");
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		} );
		Thread consumidor = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					System.out.println("Consumidor preparado");
					String mensaje = cola.take();
					System.out.println("Consumidor recibe: " + mensaje);
				}catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		});
		productor.start();
		consumidor.start();
	}

}
