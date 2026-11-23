package es.ercilla.modelos;

import es.ercilla.aplicaciones.Parking;

public class Coche extends Thread{
	private Parking parking;
	private int tiempoAparcado;
	private int plaza = -1;
	
	public Coche(String string, Parking parking, int tiempoAparcado) {
		setName(string);
		this.parking = parking;
		this.tiempoAparcado = tiempoAparcado; 
	}

	@Override
	public void run() {
		try {
			plaza = parking.entrar(getName());
			Thread.sleep(tiempoAparcado * 1000);
			
		} catch (Exception e) {
			Thread.currentThread().interrupt();
		} finally {
			if(plaza != -1) {
				try {
					parking.salir(getName(), plaza);
				}catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}

	public Parking getParking() {
		return parking;
	}

	public void setParking(Parking parking) {
		this.parking = parking;
	}

	public int getTiempoAparcado() {
		return tiempoAparcado;
	}

	public void setTiempoAparcado(int tiempoAparcado) {
		this.tiempoAparcado = tiempoAparcado;
	}

	public int getPlaza() {
		return plaza;
	}

	public void setPlaza(int plaza) {
		this.plaza = plaza;
	}
	
	
}
