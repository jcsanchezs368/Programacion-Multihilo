package es.ercilla.ejemplos;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

import es.ercilla.modelos.Persona;

public class EjemploWalkieTalkie {

	public static void main(String[] args) {
		TransferQueue<String> canalAna = new LinkedTransferQueue<>();
		TransferQueue<String> canalLuis = new LinkedTransferQueue<>();
		
		Persona ana = new Persona("Ana", canalAna, canalLuis);
		Persona luis = new Persona("Luis", canalLuis, canalAna);
		
		ana.start();
		luis.start();

	}

}
