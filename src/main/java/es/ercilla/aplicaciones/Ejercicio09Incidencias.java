import java.util.concurrent.ConcurrentLinkedQueue;

class Tecnico extends Thread {
    private final ConcurrentLinkedQueue<String> incidencias;

    public Tecnico(String nombre, ConcurrentLinkedQueue<String> incidencias) {
        super(nombre);
        this.incidencias = incidencias;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            String incidencia = getName() + " - Incidencia " + i;
            incidencias.add(incidencia);
            System.out.println("Registrada: " + incidencia);
        }
    }
}

public class Ejercicio09Incidencias {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> incidencias = new ConcurrentLinkedQueue<>();

        Tecnico t1 = new Tecnico("Técnico 1", incidencias);
        Tecnico t2 = new Tecnico("Técnico 2", incidencias);
        Tecnico t3 = new Tecnico("Técnico 3", incidencias);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nTodas las incidencias:");
        for (String incidencia : incidencias) {
            System.out.println(incidencia);
        }
    }
}
