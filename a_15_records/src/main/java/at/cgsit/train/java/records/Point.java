package at.cgsit.train.java.records;

record Point(int x, int y) {

  public int radius() {
    return (int)Math.sqrt(x*x + y*y);
  }

}


