package es.ercilla.aplicaciones.prioridades;

public class GestorTrabajadores {

	public static void main(String[] args) throws InterruptedException {

		/**
		 * Una empresa dispone de tres trabajadores que deben preparar diferentes pedidos.
		 * 
		 * El primer trabajador tiene prioridad máxima, el segundo nomal y el tercero mínima.
		 * Todos deben preparar cinco pedidos y tardan un segundo en preparar cada uno.
		 * 
		 * Además existe un hilo demonio que muestra cada dos segundos el mensaje Supervisando trabajadores.
		 * 
		 * El programa principal debe arrancar a los tres trabajadores, mostrar sus estados mientras trabajan,
		 * esperar a que todos terminen mediante join() y mostrar Producción Finalizada
		 * 
		 * AMPLICACIÓN: Cuando hayan transucrrido tres segundos, el programa principal deberá interrumpir al
		 * tercer trabajador, que debe terminar de manera controlada.
		 */
		
		
		Trabajador trabajador1 = new Trabajador("Trabajador 1");
		Trabajador trabajador2 = new Trabajador("Trabajador 2");
		Trabajador trabajador3 = new Trabajador("Trabajador 3");
		
		SupervisorDaemon supervisor = new SupervisorDaemon("Supervisor");
		
		
		
		trabajador1.setPriority(Thread.MAX_PRIORITY);
		trabajador2.setPriority(Thread.NORM_PRIORITY);
		trabajador3.setPriority(Thread.MIN_PRIORITY);
		
		supervisor.setDaemon(true);
		System.out.println("ESTADO TRABAJADORES ANTES DE EMPEZAR");
		System.out.println(trabajador1.getState());
		System.out.println(trabajador2.getState());
		System.out.println(trabajador3.getState());
		System.out.println();
		
		supervisor.start();
		trabajador1.start();
		trabajador2.start();
		trabajador3.start();
		
		System.out.println("\nESTADO TRABAJADORES DESPUÉS DE EMPEZAR");
		System.out.println(trabajador1.getState());
		System.out.println(trabajador2.getState());
		System.out.println(trabajador3.getState());
		System.out.println();
		
		Thread.sleep(3000);
		
		trabajador3.interrupt();
		trabajador3.join();
		
		System.out.println("\nESTADO TRABAJADORES DESPUÉS DE INTERRUMPIR TRABAJADOR 3");
		System.out.println(trabajador1.getState());
		System.out.println(trabajador2.getState());
		System.out.println(trabajador3.getState());
		System.out.println();
		
		trabajador1.join();
		trabajador2.join();
		
		
		System.out.println("\nESTADO FINAL TRABAJADORES");
		System.out.println(trabajador1.getState());
		System.out.println(trabajador2.getState());
		System.out.println(trabajador3.getState());
		System.out.println();
		
		System.out.println("\nProducción finalizada");
		
		
	}

}
