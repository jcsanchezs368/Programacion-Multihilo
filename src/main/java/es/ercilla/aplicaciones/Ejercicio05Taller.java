import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Reparacion implements Runnable {
    private final int coche;

    public Reparacion(int coche) {
        this.coche = coche;
    }

    @Override
    public void run() {
        System.out.println("El coche " + coche + " está siendo reparado por "
                + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
            System.out.println("Coche " + coche + " reparado.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class Ejercicio05Taller {
    public static void main(String[] args) {
        ExecutorService taller = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 8; i++) {
            taller.submit(new Reparacion(i));
        }

        taller.shutdown();

        try {
            if (taller.awaitTermination(1, TimeUnit.MINUTES)) {
                System.out.println("Todas las reparaciones han finalizado. Taller cerrado.");
            } else {
                System.out.println("El tiempo de espera ha terminado.");
                taller.shutdownNow();
            }
        } catch (InterruptedException e) {
            taller.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
