package es.ercilla.ejemplos;

public class EjemploInterrupt extends Thread{

	@Override
	public void run() {
		try {
			for (int i = 1; i <= 10; i++) {
				System.out.println("Descargando archivo... " + (i*10) + "%" );
				Thread.sleep(1000);
			}
			System.out.println("Descarga completada.");
		} catch (InterruptedException e) {
			System.err.println("La descarga ha sido cancelada.");
			e.printStackTrace();
			interrupt();
		}
	}
	public static void main(String[] args) throws InterruptedException{
		EjemploInterrupt descarga = new EjemploInterrupt();
		descarga.start();
		Thread.sleep(3000);
		descarga.interrupt();
		descarga.join();
		
	}

}
