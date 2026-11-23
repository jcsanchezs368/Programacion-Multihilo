import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

class Almacen extends Thread {
    private final TransferQueue<String> cola;

    public Almacen(TransferQueue<String> cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 6; i++) {
                String paquete = "Paquete " + i;
                System.out.println("Almacén entrega: " + paquete);
                cola.transfer(paquete); // Espera hasta que un repartidor lo reciba.
            }

            cola.transfer("FIN");
            cola.transfer("FIN");
            System.out.println("Almacén: todos los paquetes entregados.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Repartidor extends Thread {
    private final TransferQueue<String> cola;

    public Repartidor(int id, TransferQueue<String> cola) {
        super("Repartidor " + id);
        this.cola = cola;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String paquete = cola.take();
                if (paquete.equals("FIN")) {
                    break;
                }
                System.out.println(getName() + " recibe " + paquete);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(getName() + " termina.");
    }
}

public class Ejercicio12TransferQueue {
    public static void main(String[] args) {
        TransferQueue<String> cola = new LinkedTransferQueue<>();

        Repartidor r1 = new Repartidor(1, cola);
        Repartidor r2 = new Repartidor(2, cola);
        Almacen almacen = new Almacen(cola);

        r1.start();
        r2.start();
        almacen.start();

        try {
            almacen.join();
            r1.join();
            r2.join();
            System.out.println("Distribución finalizada.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
