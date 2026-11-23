import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;
import java.util.Random;

class Trabajador implements Runnable {
    private final int id;
    private final TransferQueue<String> cola;
    private final Random random = new Random();

    public Trabajador(int id, TransferQueue<String> cola) {
        this.id = id;
        this.cola = cola;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String mensaje = cola.take();
                if (mensaje.equals("FIN")) {
                    System.out.println("Trabajador " + id + " termina.");
                    break;
                }

                System.out.println("Trabajador " + id + " recibe: " + mensaje);
                int segundos = random.nextInt(3) + 1;
                Thread.sleep(segundos * 1000L);
                System.out.println("Trabajador " + id + " ha procesado: " + mensaje);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class Ejercicio15Mensajeria {
    public static void main(String[] args) {
        TransferQueue<String> cola = new LinkedTransferQueue<>();

        Thread t1 = new Thread(new Trabajador(1, cola));
        Thread t2 = new Thread(new Trabajador(2, cola));
        Thread t3 = new Thread(new Trabajador(3, cola));

        t1.start();
        t2.start();
        t3.start();

        try {
            // La central espera a que algún trabajador reciba cada mensaje.
            for (int i = 1; i <= 9; i++) {
                String mensaje = "Mensaje de trabajo " + i;
                System.out.println("Central envía: " + mensaje);
                cola.transfer(mensaje);
            }

            // Un mensaje de fin para cada trabajador.
            cola.transfer("FIN");
            cola.transfer("FIN");
            cola.transfer("FIN");

            t1.join();
            t2.join();
            t3.join();

            System.out.println("Central de mensajería cerrada.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
