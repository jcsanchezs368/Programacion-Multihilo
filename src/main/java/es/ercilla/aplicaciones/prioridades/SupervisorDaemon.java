package es.ercilla.aplicaciones.prioridades;

public class SupervisorDaemon extends Thread {
	public SupervisorDaemon(String nombre) {
		super(nombre);
	}
	
	@Override
	public void run() {
		try {
			while(!interrupted()) {
				System.out.println("Supervisando trabajadores");
				Thread.sleep(2000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			interrupt();
		}
	}
	
}
