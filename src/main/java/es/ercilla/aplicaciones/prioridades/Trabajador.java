package es.ercilla.aplicaciones.prioridades;

public class Trabajador extends Thread {

	public Trabajador(String nombre) {
		super(nombre);
	}
	
	@Override
	public void run() {
		try {
			System.out.println(getName() + " - Empieza a preparar pedidos");
			for(int i = 1; i <= 5; i++) {
				Thread.sleep(1000);
				System.out.println(getName() + " - Termina el pedido " + i);
				
			}
		}catch (InterruptedException e) {
			System.out.println(getName() + " ha sido interrumpido");
			interrupt();
		}
	}
	
	
	
}
