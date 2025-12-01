---

# 📘 **Foliensammlung: Nebenläufige Programmierung in Java 25**

---

## **Titel**

# Nebenläufige Programmierung in Java 25

### Threads, Synchronisation, Executor, Virtual Threads & Structured Concurrency

---

# **Kapitel 1 – Grundlagen**

---

## Folie 1.1 – Was ist Nebenläufigkeit?

* Mehrere Aufgaben laufen scheinbar gleichzeitig
* Ziel: bessere Ausnutzung von CPU & IO
* Besonders wichtig für:

    * Server-Anwendungen
    * Datenbank/Netzwerk-IO
    * UI-Reaktion
    * Batch-Jobs & Pipelines

**Trainer-Note:** “Nebenläufigkeit ≠ echte Parallelität. Parallelität nur bei mehreren CPU-Kernen.”

---

## Folie 1.2 – Klassische Java-Threads

```java
Thread t = new Thread(() -> {
    System.out.println("Hello from thread!");
});
t.start();
t.join();
```

* Schwergewichtig (1 Java Thread = 1 OS Thread)
* Start/Stop teuer
* Nur wenige hundert Threads sinnvoll

---

## Folie 1.3 – Thread Lebenszyklus

* NEW
* RUNNABLE
* BLOCKED
* WAITING / TIMED_WAITING
* TERMINATED

**Trainer-Note:** Wichtig zum Lesen bestehender Systeme.

---

## Folie 1.4 – Probleme klassischer Threads

* Hoher Speicherverbrauch
* Komplexes Fehler-/Lifecycle-Managing
* Deadlocks
* Race Conditions
* Threads “verhungern” leicht

→ **Deshalb seit Java 21 modernisiert.**

---

# **Kapitel 2 – Synchronisation & Thread-Safety**

---

## Folie 2.1 – Race Condition

**Definition:** Zwei Threads greifen gleichzeitig auf dieselben Daten zu → inkonsistente Ergebnisse.

**Beispiel:**

```java
counter++;  // nicht atomar!
```

---

## Folie 2.2 – `synchronized` (Methoden & Blöcke)

```java
synchronized void increment() {
    counter++;
}
```

```java
synchronized(lock) {
    sharedList.add(value);
}
```

* Garantiert: Atomicity + Visibility
* Sperrt den Monitor eines Objekts

---

## Folie 2.3 – Visibility & `volatile`

```java
volatile boolean running = true;
```

* Keine Synchronisation von Blöcken
* Garantiert Sichtbarkeit zwischen Threads
* Typisch für Abbruch-Flags

---

## Folie 2.4 – wait/notify (nur Legacy)

```java
synchronized(lock) {
    lock.wait();
}
```

* Low-level Mechanismus
* In modernen Designs ersetzt durch:

    * BlockingQueue
    * CompletableFuture
    * Structured Concurrency

---

# **Kapitel 3 – Moderne Nebenläufigkeit (Java 21–25)**

---

## Folie 3.1 – Warum neue APIs?

* Klassische Threads zu teuer
* Komplexe Verwaltung
* Moderne Server benötigen **10.000+ gleichzeitige Tasks**
  → Virtual Threads lösen das Problem.

---

## Folie 3.2 – ExecutorService

```java
var executor = Executors.newFixedThreadPool(4);
executor.submit(() -> doWork());
executor.shutdown();
```

* Arbeitet mit Tasks statt Threads
* Einfacher, sicherer, skalierbarer
* Basis für Virtual Threads

---

## Folie 3.3 – Futures & Callables

```java
Future<Integer> f = executor.submit(() -> 42);
int result = f.get();
```

* Task starten
* Ergebnis später abholen
* Mit Timeout, Cancel usw.

---

## Folie 3.4 – CompletableFuture (Async Pipelines)

```java
CompletableFuture.supplyAsync(() -> loadPrice())
                 .thenApply(p -> p * 1.2)
                 .thenAccept(System.out::println);
```

* Sehr mächtig, aber manchmal unübersichtlich
* Grundlage für Pipelines & Async-APIs

---

## Folie 3.5 – Virtual Threads (Java 21+)

```java
Thread.startVirtualThread(() -> doWork());
```

oder per Executor:

```java
var exec = Executors.newVirtualThreadPerTaskExecutor();
exec.submit(() -> fetchFromDb());
```

**Vorteile:**

* Millionen Threads möglich
* günstig, leichtgewichtig
* perfekt für IO-Tasks
* Kein Pooling mehr nötig

---

## Folie 3.6 – Architektur: Platform vs. Virtual Thread

| Merkmal    | OS-Thread | Virtual Thread |
| ---------- | --------- | -------------- |
| Kosten     | hoch      | extrem niedrig |
| Anzahl     | hunderte  | Millionen      |
| Scheduling | OS Kernel | JVM            |
| geeignet   | CPU-bound | IO-bound       |

---

## Folie 3.7 – Structured Concurrency

```java
try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
    var u = scope.fork(() -> loadUser());
    var o = scope.fork(() -> loadOrders());
    scope.join();
    scope.throwIfFailed();
    return combine(u.get(), o.get());
}
```

**Damit:**

* parallele Aufgaben wie in einem Methodenblock
* sauberer Fehler-/Timeout-Handling
* kein Thread-Chaos mehr

---

# **Kapitel 4 – Praxisbeispiele (2025)**

---

## Folie 4.1 – Producer/Consumer mit Virtual Threads

**Architektur:**

InputQueue → WorkerThreads (virtual) → ResultCollector

**Warum wichtig:** Moderne Form von Job-System, ETL, Scraper, Batch.

---

## Folie 4.2 – Beispiel: Queue + Worker-Pool

```java
BlockingQueue<Job> queue = new ArrayBlockingQueue<>(100);
List<Result> results = new CopyOnWriteArrayList<>();

var exec = Executors.newVirtualThreadPerTaskExecutor();

for (int i = 0; i < 20; i++) {
    queue.put(new Job("job-" + i));
}

for (int w = 0; w < 4; w++) {
    exec.submit(() -> {
        while (!queue.isEmpty()) {
            Job job = queue.poll();
            if (job != null) {
                results.add(process(job));
            }
        }
    });
}
```

---

## Folie 4.3 – Preisvergleich / Multi-Shop Request

* 3 Shops parallel abfragen
* Timeout pro Shop
* Bester Preis wird zurückgegeben
* Umsetzung mit CompletableFuture oder Structured Concurrency

---

## Folie 4.4 – Beispiel: Structured Concurrency für Web-API

```java
try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
    var a = scope.fork(() -> apiShopA());
    var b = scope.fork(() -> apiShopB());
    scope.join();
    return Math.min(a.get(), b.get());
}
```

---

## Folie 4.5 – CPU vs. IO Tasks

| Art       | Beispiel                  | Empfehlung        |
| --------- | ------------------------- | ----------------- |
| IO-bound  | DB, HTTP, Files           | Virtual Threads   |
| CPU-bound | Hashing, Bildverarbeitung | Fixed Thread Pool |

---

## Folie 4.6 – Best Practices 2025

* **Keine** direkten Threads (`new Thread`)
* **Keine** großen thread pools mehr
* **IO-Arbeit** → Virtual Threads
* **CPU-Arbeit** → Fixed Pool
* Für parallele Teilaufgaben → **Structured Concurrency**
* Für Pipelines → **CompletableFuture**

---

## Folie 4.7 – Zusammenfassung

* Klassische Threads nur als Grundlagenwissen
* Synchronisation weiterhin wichtig
* Executor, Futures, Virtual Threads = Standard
* Structured Concurrency = moderner Ersatz für „Thread Chaos“
* Java 25 ist **stark vereinfacht und maximal skalierbar**

---
