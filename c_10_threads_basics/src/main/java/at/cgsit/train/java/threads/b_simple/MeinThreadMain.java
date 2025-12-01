package at.cgsit.train.java.threads.b_simple;

/**
 * Ein einfaches Beispiel für eine eigene Thread-Klasse.
 * Der Thread läuft ca. 2–3 Minuten und gibt Fortschrittsmeldungen aus.
 */
public class MeinThreadMain extends Thread {

    private final int laufzeitSekunden;

    public MeinThreadMain(String name, int laufzeitSekunden) {
        super(name); // Thread bekommt Namen
        this.laufzeitSekunden = laufzeitSekunden;
    }

    @Override
    public void run() {
        System.out.printf("[%s] Starte Arbeit (%d Sekunden)...%n",
                getName(), laufzeitSekunden);

        long start = System.currentTimeMillis();

        for (int s = 1; s <= laufzeitSekunden; s++) {
            try {
                Thread.sleep(1000);  // wartet 1 Sekunde
            } catch (InterruptedException e) {
                System.out.printf("[%s] Thread wurde unterbrochen!%n", getName());
                return;
            }

            System.out.printf("[%s] Fortschritt: %d / %d Sekunden%n",
                    getName(), s, laufzeitSekunden);
        }

        long dauer = System.currentTimeMillis() - start;
        System.out.printf("[%s] Fertig in %.2f Sekunden.%n",
                getName(), dauer / 1000.0);
    }

    // Direkt startbar:
    public static void main(String[] args) {
        // 2–3 Minuten = 120–180 Sekunden → wir nehmen 150 Sekunden
        MeinThreadMain t = new MeinThreadMain("MeinEinfacherThread", 150);

        System.out.println("Main: Starte Thread...");
        t.start();

        try {
            t.join(); // auf Ende warten
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Main: Thread wurde vollständig beendet.");
    }
}
