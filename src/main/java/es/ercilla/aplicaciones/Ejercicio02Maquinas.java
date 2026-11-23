package es.ercilla.aplicaciones;

class Maquina implements Runnable {
    private final String nombre;

    public Maquina(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        for (int pieza = 1; pieza <= 5; pieza++) {
            try {
                Thread.sleep(500);
                System.out.println(nombre + " ha fabricado la pieza " + pieza);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println(nombre + " ha terminado.");
    }
}

public class Ejercicio02Maquinas {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Maquina("Máquina 1"));
        Thread t2 = new Thread(new Maquina("Máquina 2"));

        // Con start(), las dos máquinas trabajan concurrentemente.
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nDemostración utilizando run():");

        // run() se ejecuta en el hilo actual: primero termina una máquina
        // y después comienza la otra. No se crean hilos nuevos.
        Thread t3 = new Thread(new Maquina("Máquina 3"));
        Thread t4 = new Thread(new Maquina("Máquina 4"));
        t3.run();
        t4.run();
    }
}
