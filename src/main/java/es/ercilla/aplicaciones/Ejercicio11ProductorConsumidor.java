import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Productor extends Thread {
    private final BlockingQueue<Integer> almacen;
    private final int id;

    public Productor(int id, BlockingQueue<Integer> almacen) {
        super("Productor " + id);
        this.id = id;
        this.almacen = almacen;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                Thread.sleep(300);
                int producto = id * 100 + i;
                almacen.put(producto); // Espera si el almacén está lleno.
                System.out.println(getName() + " fabrica e introduce " + producto);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumidor extends Thread {
    private final BlockingQueue<Integer> almacen;

    public Consumidor(int id, BlockingQueue<Integer> almacen) {
        super("Consumidor " + id);
        this.almacen = almacen;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int producto = almacen.take(); // Espera si está vacío.
                if (producto == -1) {
                    break;
                }
                System.out.println(getName() + " procesa " + producto);
                Thread.sleep(600);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(getName() + " termina.");
    }
}

public class Ejercicio11ProductorConsumidor {
    public static void main(String[] args) {
        BlockingQueue<Integer> almacen = new ArrayBlockingQueue<>(5);

        Productor p1 = new Productor(1, almacen);
        Productor p2 = new Productor(2, almacen);
        Consumidor c1 = new Consumidor(1, almacen);
        Consumidor c2 = new Consumidor(2, almacen);

        c1.start();
        c2.start();
        p1.start();
        p2.start();

        try {
            p1.join();
            p2.join();

            // Dos valores especiales para indicar a los consumidores que paren.
            almacen.put(-1);
            almacen.put(-1);

            c1.join();
            c2.join();

            System.out.println("Los 20 productos han sido consumidos.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
