package es.ercilla.ejemplos;

public class EjemploYield extends Thread{
	
	public EjemploYield(String nombre) {
		super(nombre);
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 10; i++) {
			System.out.println(getName() + " - Iteracion " + i);
			Thread.yield();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		EjemploYield trabajador1 = new EjemploYield("trabajador1");
		EjemploYield trabajador2 = new EjemploYield("trabajador2");
		
		trabajador1.start();
		trabajador2.start();
		
		trabajador1.join();
		trabajador2.join();

	}

}
