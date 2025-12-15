package at.cgsit.train.java.generics.a04_extends;

public class ClubMain {

  static void main() {

    Club<Player> club =
        new Club<>(new Player("Max"));


    Club<?> clubTwo =
        new Club<>(new Player("Max"));

    clubTwo.printName();

  }

}
