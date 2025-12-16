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

package at.cgsit.train.java.job_fw.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Ein einfacher In-Memory Queue-Adapter, der auf einer
 * ArrayBlockingQueue basiert. Ideal für lokale Entwicklung
 * und Tests.
 *
 * Kann später durch SqsQueueAdapter ersetzt werden.
 */
public class InMemoryBlockingQueue<T> implements BlockingQueueAdapter<T> {

    private final BlockingQueue<T> queue;

    public InMemoryBlockingQueue(int capacity) {
        this.queue = new ArrayBlockingQueue<>(capacity);
    }

    @Override
    public void put(T item) throws InterruptedException {
        queue.put(item); // blockiert bei voller Queue
    }

    @Override
    public T poll(long timeoutMillis) throws InterruptedException {
        return queue.poll(timeoutMillis, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
