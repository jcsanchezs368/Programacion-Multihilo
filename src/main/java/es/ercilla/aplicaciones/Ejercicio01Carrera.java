package es.ercilla.aplicaciones;

import java.util.Random;

class Corredor extends Thread {
    private final Random random = new Random();

    public Corredor(String nombre) {
        super(nombre);
    }

    @Override
    public void run() {
        for (int tramo = 1; tramo <= 5; tramo++) {
            try {
                int segundos = random.nextInt(3) + 1;
                Thread.sleep(segundos * 1000L);
                System.out.println(getName() + " ha completado el tramo " + tramo);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(getName() + " ha sido interrumpido.");
                return;
            }
        }
        System.out.println(getName() + " ha terminado la carrera.");
    }
}

public class Ejercicio01Carrera {
    public static void main(String[] args) {
        Corredor c1 = new Corredor("Corredor 1");
        Corredor c2 = new Corredor("Corredor 2");
        Corredor c3 = new Corredor("Corredor 3");

        c1.start();
        c2.start();
        c3.start();

        try {
            c1.join();
            c2.join();
            c3.join();
            System.out.println("Carrera finalizada");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("El hilo principal ha sido interrumpido.");
        }
    }
}
