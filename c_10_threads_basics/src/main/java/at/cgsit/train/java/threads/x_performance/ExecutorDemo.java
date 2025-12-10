package at.cgsit.train.java.threads.x_performance;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorDemo {

    // Holen wir uns die Anzahl der verfügbaren Prozessoren/Kerne
    private static final int NUM_CORES = Runtime.getRuntime().availableProcessors();
    // Verwenden wir die Anzahl der Kerne für den FixedThreadPool, um maximale CPU-Auslastung zu zeigen
    private static final int POOL_SIZE = NUM_CORES /2;
    
    // Gesamtanzahl der Tasks für beide Szenarien
    private static final int NUM_TASKS = NUM_CORES /2 ;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Verfügbare CPU-Kerne: " + NUM_CORES);

        //System.out.println("\n=== 1. WARMUP start ===");
        //runSingleThreaded();
        //System.out.println("\n=== 1. WARMUP end ===");


        // --- Szenario 1: Single-Thread-Ausführung ---
        System.out.println("\n=== 1. Single-Thread-Ausführung (Sequenziell) ===");
        // runSingleThreaded();

        // Füge eine Pause ein, um die Ausgabe zu trennen
        TimeUnit.SECONDS.sleep(5);

        // --- Szenario 2: Thread-Pool-Ausführung ---
        System.out.println("\n=== 2. Thread-Pool-Ausführung (Parallel) ===");
        System.out.println("Pool-Größe = " + POOL_SIZE + " Threads.");
        runThreadPool();

        System.out.println("\n=== END  ===");

    }

    /**
     * Führt alle Tasks mit einem einzigen Thread nacheinander aus.
     */
    private static void runSingleThreaded() throws InterruptedException {
        long totalStartTime = System.currentTimeMillis();
        
        // Erzeugt einen Executor mit nur einem Thread
        ExecutorService singleExecutor = Executors.newSingleThreadExecutor(); 
        
        for (int i = 1; i <= 2; i++) {
            singleExecutor.submit(new CpuIntensiveTask("SingleTask-" + i));
        }
        
        // Fährt den Executor herunter und wartet, bis alle Tasks abgeschlossen sind
        shutdownAndAwaitTermination(singleExecutor); 
        
        long totalEndTime = System.currentTimeMillis();
        System.out.println("-> Single-Thread-Gesamtzeit: " + (totalEndTime - totalStartTime) + " ms");
    }

    /**
     * Führt alle Tasks mit einem Pool von Threads parallel aus.
     */
    private static void runThreadPool() throws InterruptedException {
        long totalStartTime = System.currentTimeMillis();
        
        // Erzeugt einen Pool mit der Anzahl der verfügbaren CPU-Kerne
        ExecutorService poolExecutor = Executors.newFixedThreadPool(POOL_SIZE); 
        
        for (int i = 1; i <= NUM_TASKS; i++) {
            poolExecutor.submit(new CpuIntensiveTask("PoolTask-" + i));
        }

        // Fährt den Executor herunter und wartet, bis alle Tasks abgeschlossen sind
        shutdownAndAwaitTermination(poolExecutor); 
        
        long totalEndTime = System.currentTimeMillis();
        System.out.println("-> Thread-Pool-Gesamtzeit: " + (totalEndTime - totalStartTime) + " ms");
    }
    
    /**
     * Hilfsmethode zum Herunterfahren und Warten auf den Abschluss aller Tasks
     */
    private static void shutdownAndAwaitTermination(ExecutorService executor) throws InterruptedException {
        executor.shutdown(); // Startet das Herunterfahren: keine neuen Tasks mehr angenommen
        try {
            // Wartet maximal 60 Sekunden auf den Abschluss aller Tasks
            if (!executor.awaitTermination(20, TimeUnit.MINUTES)) {
                executor.shutdownNow(); // Erzwingt das Herunterfahren
            }
        } catch (InterruptedException ie) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}