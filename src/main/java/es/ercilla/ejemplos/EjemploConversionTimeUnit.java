package es.ercilla.ejemplos;

import java.util.concurrent.TimeUnit;

public class EjemploConversionTimeUnit {
	public static void main(String[] args) {
		long segundos = 120;
		long minutos = TimeUnit.SECONDS.toMinutes(segundos);
		long milisegundos = TimeUnit.SECONDS.toMillis(minutos);
		System.out.println(segundos);
		System.out.println(minutos);
		System.out.println(milisegundos);
		
	}
	
}
