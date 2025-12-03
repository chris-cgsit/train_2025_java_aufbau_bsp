package at.cgsit.train.java.threads.synch;

// Gemeinsames Objekt
class SharedCounter {

  private int value = 0;

  // synchronized – nur ein Thread darf gleichzeitig diese Methode ausführen
  public synchronized void increment(String who) {
    value++;
    System.out.println(who + " -> Counter: " + value);
  }

}