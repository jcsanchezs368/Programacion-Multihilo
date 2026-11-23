import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class ProcesarPedido implements Callable<Integer> {
    private final int numero;
    private final int importe;

    public ProcesarPedido(int numero, int importe) {
        this.numero = numero;
        this.importe = importe;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Procesando pedido " + numero + " en "
                + Thread.currentThread().getName());
        Thread.sleep(1000);
        System.out.println("Pedido " + numero + " procesado: " + importe + " €");
        return importe;
    }
}

class RepartidorPedidos extends Thread {
    private final BlockingQueue<Integer> pedidos;

    public RepartidorPedidos(BlockingQueue<Integer> pedidos) {
        super("Repartidor");
        this.pedidos = pedidos;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int importe = pedidos.take();
                if (importe == -1) {
                    break;
                }
                System.out.println("Enviando pedido de " + importe + " €");
                Thread.sleep(500);
                System.out.println("Pedido enviado.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class Ejercicio14Pedidos {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        BlockingQueue<Integer> colaEnvios = new LinkedBlockingQueue<>();
        RepartidorPedidos repartidor = new RepartidorPedidos(colaEnvios);
        repartidor.start();

        List<Future<Integer>> resultados = new ArrayList<>();
        int total = 0;

        try {
            // Se envían diez tareas al grupo de tres trabajadores.
            for (int i = 0; i < 10; i++) {
                int importe = (i + 1) * 100;
                resultados.add(executor.submit(new ProcesarPedido(i + 1, importe)));
            }

            // get() espera a cada resultado y lo introduce en la cola de envíos.
            for (Future<Integer> resultado : resultados) {
                int importe = resultado.get();
                total += importe;
                colaEnvios.put(importe);
            }

            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);

            // Señal de fin para el repartidor.
            colaEnvios.put(-1);
            repartidor.join();

            System.out.println("Importe total de los pedidos: " + total + " €");
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            executor.shutdownNow();
            System.out.println("Error procesando un pedido: " + e.getCause());
        }
    }
}
