package at.cgsit.train.java.threads.workqueue;

/**
 * Einfache Job-Beschreibung:
 * - id       : Job-ID
 * - payload  : beliebige Daten zur Verarbeitung
 */
public record Job(String id, String payload) {
}
