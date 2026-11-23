import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Ejercicio06Semaforo {
    public static void main(String[] args) {
        ScheduledExecutorService semaforo =
                Executors.newSingleThreadScheduledExecutor();

        String[] estados = {"VERDE", "AMARILLO", "ROJO"};
        AtomicInteger indice = new AtomicInteger(0);

        System.out.println("Semáforo: VERDE");

        // Cada dos segundos avanza al siguiente estado.
        semaforo.scheduleAtFixedRate(() -> {
            int siguiente = (indice.incrementAndGet()) % estados.length;
            System.out.println("Semáforo: " + estados[siguiente]);
        }, 2, 2, TimeUnit.SECONDS);

        // Detiene el planificador después de 12 segundos.
        semaforo.schedule(() -> {
            System.out.println("Semáforo apagado.");
            semaforo.shutdown();
        }, 12, TimeUnit.SECONDS);
    }
}
