package es.ercilla.ejemplos;

public class EjemploGetState extends Thread{
	
	@Override
	public void run() {
		try {
			System.out.println("Trabajador iniciado");
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			interrupt();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		EjemploGetState trabajador = new EjemploGetState();
		
		System.out.println("Estado inicial: " + trabajador.getState());
		trabajador.start();
		
		System.out.println("Estado después de start: " + trabajador.getState());
		
		System.out.println("Estado durante sleep: " + trabajador.getState());
		trabajador.join();
		System.out.println("Estado final: " + trabajador.getState());
	}

}
