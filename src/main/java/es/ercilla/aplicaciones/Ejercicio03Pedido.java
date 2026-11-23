package es.ercilla.aplicaciones;

class TareaPedido extends Thread {
    private final String tarea;
    private final int segundos;

    public TareaPedido(String tarea, int segundos) {
        this.tarea = tarea;
        this.segundos = segundos;
    }

    @Override
    public void run() {
        System.out.println("Comienza: " + tarea);
        try {
            Thread.sleep(segundos * 1000L);
            System.out.println("Finaliza: " + tarea);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class Ejercicio03Pedido {
    public static void main(String[] args) {
        TareaPedido preparar = new TareaPedido("Preparar el producto", 2);
        TareaPedido factura = new TareaPedido("Imprimir la factura", 3);
        TareaPedido envio = new TareaPedido("Enviar el pedido", 1);

        preparar.start();
        factura.start();

        try {
            // El envío espera a que terminen las dos tareas anteriores.
            preparar.join();
            factura.join();

            envio.start();
            envio.join();

            System.out.println("Pedido completado y enviado.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
