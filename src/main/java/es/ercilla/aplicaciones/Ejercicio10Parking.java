import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.Random;

class Coche extends Thread {
    private final BlockingQueue<Integer> plazas;
    private final Random random = new Random();

    public Coche(int numero, BlockingQueue<Integer> plazas) {
        super("Coche " + numero);
        this.plazas = plazas;
    }

    @Override
    public void run() {
        Integer plaza = null;
        try {
            System.out.println(getName() + " busca una plaza.");
            plaza = plazas.take(); // Espera si no hay plazas libres.
            System.out.println(getName() + " aparca en la plaza " + plaza + ".");

            int segundos = random.nextInt(5) + 1;
            Thread.sleep(segundos * 1000L);

            System.out.println(getName() + " abandona la plaza " + plaza + ".");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            if (plaza != null) {
                plazas.offer(plaza); // Devuelve la plaza al parking.
            }
        }
    }
}

public class Ejercicio10Parking {
    public static void main(String[] args) {
        BlockingQueue<Integer> plazas = new ArrayBlockingQueue<>(3);
        plazas.add(1);
        plazas.add(2);
        plazas.add(3);

        Thread[] coches = new Thread[8];

        for (int i = 0; i < coches.length; i++) {
            coches[i] = new Coche(i + 1, plazas);
            coches[i].start();
        }

        try {
            for (Thread coche : coches) {
                coche.join();
            }
            System.out.println("Todos los coches han salido del parking.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
