package at.cgsit.train.java.threads.x_performance;

import java.util.concurrent.TimeUnit;

/**
 * CPU task
 */
class CpuIntensiveTask implements Runnable {
  private final String name;
  private final long iterations = 1_000_000_000L; // 1 Milliarde Schleifendurchläufe

  public CpuIntensiveTask(String name) {
    this.name = name;
  }

  @Override
  public void run() {
    long startTime = System.currentTimeMillis();
    long result = 0;

    // Äussere Schleife
    for (int j = 0; j < 15; j++) {
      // Führe eine einfache, aber zeitaufwändige Berechnung durch
      for (long i = 0; i < iterations; i++) {
        result += i % 7; // Rechenoperation
      }
    }


    long endTime = System.currentTimeMillis();
    long duration = endTime - startTime;

    System.out.println(name + " | Abgeschlossen in: " + duration + " ms. (Result: " + result + ")");
  }
}