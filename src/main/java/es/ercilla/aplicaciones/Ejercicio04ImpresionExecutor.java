package es.ercilla.aplicaciones;

import java.util.concurrent.Executor;

class Documento implements Runnable {
    private final int numero;

    public Documento(int numero) {
        this.numero = numero;
    }

    @Override
    public void run() {
        System.out.println("Imprimiendo documento " + numero +
                " en " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
            System.out.println("Documento " + numero + " impreso.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class Ejercicio04ImpresionExecutor {
    public static void main(String[] args) {
        Executor executor = java.util.concurrent.Executors.newFixedThreadPool(2);

        for (int i = 1; i <= 5; i++) {
            executor.execute(new Documento(i));
        }

        // Executor no ofrece shutdown(). Para poder cerrar el grupo de hilos
        // usamos la implementación ExecutorService.
        if (executor instanceof java.util.concurrent.ExecutorService servicio) {
            servicio.shutdown();
        }
    }
}
