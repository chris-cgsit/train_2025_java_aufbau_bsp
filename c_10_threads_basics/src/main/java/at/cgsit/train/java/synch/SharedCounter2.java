package at.cgsit.train.java.synch;

// Gemeinsames Objekt == die instanz hier gibt es nur 1 mal
class SharedCounter2 {

  private int value = 0;
  private String name = "";

  // synchronized – nur ein Thread darf gleichzeitig diese Methode ausführen
  public synchronized void increment(String who) {
    int a = 1; // diese variable wäre exclusive am stack für jeden thread
    // value und name sind aber als shared objekt jetzt nur 1 mal vorhanden und werden gleichzeit
    // von beiden threads möglicheise verändert..
    // value ++ ist keine Atomare Funktion .. es wird er ALTE wert gelesen
    // dann wird + 1 gemacht hier in der methode .. und dann wird
    // der wert in meinem objekt geschrieben

      synchronized (this) {
          this.value = this.value + 1;
          this.name = who;
      }
    System.out.println(who + " -> Counter: " + value);
  }
}