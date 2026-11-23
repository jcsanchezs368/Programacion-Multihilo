package es.ercilla.ejemplos;

public class EjemploDaemon extends Thread {
	
	@Override
	public void run() {
		try {
			while(true) {
				System.out.println("Vigilando el sistema...");
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			interrupt();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		EjemploDaemon vigilancia = new EjemploDaemon();
		vigilancia.setDaemon(true);
		vigilancia.start();
		System.out.println("La aplicación está trabajando...");
		Thread.sleep(3500);
		System.out.println("La aplicación principal ha terminado.");
	}
}
