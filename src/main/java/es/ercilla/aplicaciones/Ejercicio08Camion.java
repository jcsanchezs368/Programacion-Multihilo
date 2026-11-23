import java.util.concurrent.*;

class CargaCamion extends Thread {
    public CargaCamion() {
        super("Operario de carga");
    }

    @Override
    public void run() {
        System.out.println("Comienza la carga del camión.");
        try {
            Thread.sleep(4000);
            System.out.println("Camión cargado.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class CalcularRuta implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("Calculando la ruta...");
        Thread.sleep(3000);
        System.out.println("Ruta calculada: 250 km.");
        return 250;
    }
}

public class Ejercicio08Camion {
    public static void main(String[] args) {
        CargaCamion carga = new CargaCamion();
        ExecutorService executor = Executors.newSingleThreadExecutor();

        carga.start();
        Future<Integer> resultadoRuta = executor.submit(new CalcularRuta());

        try {
            carga.join();
            int distancia = resultadoRuta.get();

            System.out.println("El camión puede salir. Distancia: "
                    + distancia + " km.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            System.out.println("No se pudo calcular la ruta: " + e.getCause());
        } finally {
            executor.shutdown();
        }
    }
}
