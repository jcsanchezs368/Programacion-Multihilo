import java.util.concurrent.*;

class ConsultaBanco implements Callable<Integer> {
    private final String banco;
    private final int saldo;
    private final int segundos;

    public ConsultaBanco(String banco, int saldo, int segundos) {
        this.banco = banco;
        this.saldo = saldo;
        this.segundos = segundos;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Consultando " + banco + " en "
                + Thread.currentThread().getName());
        Thread.sleep(segundos * 1000L);
        System.out.println("Consulta de " + banco + " finalizada.");
        return saldo;
    }
}

public class Ejercicio07Bancos {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        long inicio = System.currentTimeMillis();

        try {
            Future<Integer> a = executor.submit(new ConsultaBanco("Banco A", 1500, 3));
            Future<Integer> b = executor.submit(new ConsultaBanco("Banco B", 2300, 2));
            Future<Integer> c = executor.submit(new ConsultaBanco("Banco C", 800, 4));

            int total = a.get() + b.get() + c.get();

            long tiempo = System.currentTimeMillis() - inicio;
            System.out.println("Saldo total: " + total + " €");
            System.out.println("Tiempo de ejecución: " + tiempo / 1000.0 + " segundos");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            System.out.println("Error durante una consulta: " + e.getCause());
        } finally {
            executor.shutdown();
        }
    }
}
