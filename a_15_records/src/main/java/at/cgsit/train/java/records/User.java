package at.cgsit.train.java.records;

record User(String name, int age) {

  /**
   * kompakte implementierung der Konstruktor Methoden Implementierung
   * parameter sind fix, hier nur die implementierung
   */
  public User {
    if (age < 0) throw new IllegalArgumentException("Age must be >= 0");
  }

  // weitere Konstrukturen.
  // muss aber den Basis Konstruktur aufrufen. eg. default werte
  public User(String name) {
    this(name, 18);
  }

}
