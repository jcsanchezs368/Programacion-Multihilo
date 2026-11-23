package es.ercilla.modelos;

import java.util.ArrayList;

public class Superheroe implements Runnable{
	private String nombre;
	private Fortaleza fortaleza;
	private ArrayList<String> mochila;
	private int visitas;
	
	public Superheroe(String nombre, Fortaleza fortaleza) {
		this.nombre = nombre;
		this.fortaleza = fortaleza;
		mochila = new ArrayList<String>();
		visitas = 0;
	}

	@Override
	public void run() {
		System.out.println(nombre + " comienza su aventura.");
		while(mochila.size() < fortaleza.getNumeroTrofeos()) {
			Sala sala = fortaleza.getSalaAleatoria();
			String trofeo = sala.getTrofeo();
			visitas++;
			if(!mochila.contains(trofeo)) {
				mochila.add(trofeo);
				System.out.println(nombre + " entra en Sala " + sala.getNumero() + " y consigue: " + trofeo
						+ "\nColección: " + mochila + "(" + mochila.size() + "/" + fortaleza.getNumeroTrofeos() + ")");
			} else {
				System.out.println(nombre + " entra en Sala " + sala.getNumero() + " y encuentra: " + trofeo
						+ ", pero ya lo tiene.");
			}
			// Simular tiempo de exploración
			// No esperar si ya ha completado la colección
			if(mochila.size() < fortaleza.getNumeroTrofeos()) {
				try {
					Thread.sleep(200);
				}catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					return;
				}
			}
		}
		System.out.println("=========================================\n" + nombre + " ha terminado su aventura.\n"
				+ "Colección: " + mochila + "\nVisitas totales: " + visitas + "\n=========================================");
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Fortaleza getFortaleza() {
		return fortaleza;
	}

	public void setFortaleza(Fortaleza fortaleza) {
		this.fortaleza = fortaleza;
	}

	public ArrayList<String> getMochila() {
		return mochila;
	}

	public void setMochila(ArrayList<String> mochila) {
		this.mochila = mochila;
	}

	public int getVisitas() {
		return visitas;
	}

	public void setVisitas(int visitas) {
		this.visitas = visitas;
	}
	
}
