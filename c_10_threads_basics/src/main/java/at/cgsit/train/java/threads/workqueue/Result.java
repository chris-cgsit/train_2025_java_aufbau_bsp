package at.cgsit.train.java.threads.workqueue;

/**
 * Ergebnis eines verarbeiteten Jobs:
 * - jobId       : Referenz auf den Job
 * - workerName  : Name des Worker-Threads
 * - durationMs  : Bearbeitungsdauer in Millisekunden
 */
public record Result(String jobId, String workerName, long durationMs) {
}
