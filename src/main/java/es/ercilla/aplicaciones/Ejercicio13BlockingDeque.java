import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

class ProductorExtremo extends Thread {
    private final BlockingDeque<Integer> almacen;
    private final boolean izquierda;

    public ProductorExtremo(String nombre, BlockingDeque<Integer> almacen,
                            boolean izquierda) {
        super(nombre);
        this.almacen = almacen;
        this.izquierda = izquierda;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                int producto = (izquierda ? 100 : 200) + i;
                if (izquierda) {
                    almacen.putFirst(producto);
                } else {
                    almacen.putLast(producto);
                }
                System.out.println(getName() + " introduce " + producto);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class ConsumidorExtremo extends Thread {
    private final BlockingDeque<Integer> almacen;
    private final boolean izquierda;

    public ConsumidorExtremo(String nombre, BlockingDeque<Integer> almacen,
                             boolean izquierda) {
        super(nombre);
        this.almacen = almacen;
        this.izquierda = izquierda;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                int producto = izquierda ? almacen.takeFirst() : almacen.takeLast();
                System.out.println(getName() + " retira " + producto);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class Ejercicio13BlockingDeque {
    public static void main(String[] args) {
        BlockingDeque<Integer> almacen = new LinkedBlockingDeque<>(5);

        Thread pIzq = new ProductorExtremo("Productor izquierda", almacen, true);
        Thread pDer = new ProductorExtremo("Productor derecha", almacen, false);
        Thread cIzq = new ConsumidorExtremo("Consumidor izquierda", almacen, true);
        Thread cDer = new ConsumidorExtremo("Consumidor derecha", almacen, false);

        cIzq.start();
        cDer.start();
        pIzq.start();
        pDer.start();

        try {
            pIzq.join();
            pDer.join();
            cIzq.join();
            cDer.join();
            System.out.println("Almacén vacío. Trabajo finalizado.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
