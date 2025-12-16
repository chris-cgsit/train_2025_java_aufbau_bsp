/*
 *  Copyright © 2025 CGS IT Solutions GmbH.
 *  All rights reserved.
 *
 *  This source code is proprietary intellectual property
 *  of CGS IT Solutions GmbH and is provided solely for use
 *  within licensed training programs.
 *
 *   Any copying, redistribution, modification, or use
 *   beyond the permitted scope is strictly prohibited.
 */

package at.cgsit.train.java.threads.workqueue;

/**
 * Ergebnis eines verarbeiteten Jobs:
 * - jobId       : Referenz auf den Job
 * - workerName  : Name des Worker-Threads
 * - durationMs  : Bearbeitungsdauer in Millisekunden
 */
public record Result(String jobId, String workerName, long durationMs) {
}
