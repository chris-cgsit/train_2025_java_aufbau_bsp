package at.cgsit.train.java.threads.synch;

// Gemeinsames Objekt == die instanz hier gibt es nur 1 mal
class SharedCounter {

  private int value = 0;
  private String name = "";

  // synchronized – nur ein Thread darf gleichzeitig diese Methode ausführen
  public void increment(String who) {
    value++;
    this.name = who;
    System.out.println(who + " -> Counter: " + value);
  }
}