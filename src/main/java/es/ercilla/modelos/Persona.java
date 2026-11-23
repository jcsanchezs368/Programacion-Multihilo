package es.ercilla.modelos;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

public class Persona extends Thread {
	private TransferQueue<String> canalSalida;
	private TransferQueue<String> canalEntrada;
	
	public Persona(String nombre, TransferQueue<String> canalEntrada, TransferQueue<String> canalSalida) {
		super(nombre);
		this.canalEntrada = canalEntrada;
		this.canalSalida = canalSalida;
	}
	
	@Override
	public void run() {
		try {
			if(getName().equals("Ana")) {
				System.out.println("Ana: Luis, ¿Me recibes?");
				TimeUnit.SECONDS.sleep(3);
				canalSalida.transfer("Luis, ¿Me recibes?");
				TimeUnit.SECONDS.sleep(3);
				String respuesta = canalEntrada.take();
				System.out.println("Ana recibe: " + respuesta);
				System.out.println("Ana: Perfecto. Nos vemos en la entrada. Cambio y corto.");
				canalSalida.transfer("Perfecto. Nos vemos en la entrada. Cambio y corto.");
				TimeUnit.SECONDS.sleep(3);
			}else if (getName().equals("Luis")) {
				TimeUnit.SECONDS.sleep(5);
				String mensaje = canalEntrada.take();
				System.out.println("Luis recibe: "+ mensaje);
				System.out.println("Luis: Sí, te recibo perfectamente. Cambio.");
				canalSalida.transfer("Sí, te recibo perfectamente. Cambio.");
				TimeUnit.SECONDS.sleep(5);
				String mensajeFinal = canalEntrada.take();
				System.out.println("Luis recibe: " + mensajeFinal);
			}
		}catch (InterruptedException e) {
			Thread.currentThread().interrupt();;
		}
	}
}
