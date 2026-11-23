package es.ercilla.ejemplos;

public class EjemploJoin extends Thread{

	@Override
	public void run() {
		System.out.println("Preparando pedido...");
		try {
			Thread.sleep(3000);
			System.out.println("Pedido preparado");
		} catch (InterruptedException e) {
			interrupt();
		}
	}
	public static void main(String[] args) throws InterruptedException {
		EjemploJoin trabajador = new EjemploJoin();
		trabajador.start();
		System.out.println("Esperando preparación del pedido...");
		trabajador.join();
		System.out.println("Enviando pedido...");
	}
	

}
